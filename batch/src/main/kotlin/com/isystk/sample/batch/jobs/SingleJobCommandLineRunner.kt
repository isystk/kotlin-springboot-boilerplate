package com.isystk.sample.batch.jobs

import com.google.common.collect.Lists
import com.isystk.sample.common.util.FileUtils
import org.slf4j.LoggerFactory
import org.springframework.batch.core.*
import org.springframework.batch.core.converter.JobParametersConverter
import org.springframework.batch.core.explore.JobExplorer
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.launch.JobParametersNotFoundException
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException
import org.springframework.batch.core.repository.JobRestartException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.batch.JobExecutionEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationEventPublisherAware
import org.springframework.util.StringUtils
import java.io.IOException
import java.nio.file.FileAlreadyExistsException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

/**
 * 単一のジョブのみを処理するCommandLineJobRunner
 */
class SingleJobCommandLineRunner : CommandLineRunner, ApplicationEventPublisherAware {
    @Value("\${application.processFileLocation:#{systemProperties['java.io.tmpdir']}}")
    private val processFileLocation: String? = null

    @Autowired
    var converter: JobParametersConverter? = null

    @Autowired
    var jobLauncher: JobLauncher? = null

    @Autowired
    var jobExplorer: JobExplorer? = null

    @Autowired
    var jobs: Collection<Job> = emptySet()
    var publisher: ApplicationEventPublisher? = null
    override fun setApplicationEventPublisher(publisher: ApplicationEventPublisher) {
        this.publisher = publisher
    }

    override fun run(vararg args: String) {
        var margs: Array<String>? = null
        margs = arrayOf(*args)
        val jobParameters = getJobParameters(margs)
                ?: throw IllegalArgumentException("引数が指定されていません。")

        // 引数のjobNameでJobインスタンスを取り出す
        val targetJobName = jobParameters.getString(JOB_PARAMETER_JOB_NAME)
        val jobOpt = jobs.stream().filter { s: Job -> s.name.equals(targetJobName, ignoreCase = true) }
                .findFirst()
        val job = jobOpt
                .orElseThrow { IllegalArgumentException("指定されたジョブが見つかりません。[jobName=$targetJobName]") }

        // 二重起動防止ファイルを作成する
        createProcessFile(processFileLocation, targetJobName)

        // ジョブを実行する
        val status = execute(job, jobParameters)

        // 二重起動防止ファイルを削除する
        deleteProcessFile(status, processFileLocation, targetJobName)
    }

    /**
     * パラメータを解析してJobParameters型に変換する。
     *
     * @param args
     * @return
     */
    protected fun getJobParameters(args: Array<String>?): JobParameters {
        val props = StringUtils.splitArrayElementsIntoProperties(args!!, "=")
        if (log.isDebugEnabled && props != null) {
            props.entries.stream()
                    .forEach { e: Map.Entry<Any?, Any> -> log.debug("args: key={}, value={}", e.key, e.value.toString()) }
        }
        return converter!!.getJobParameters(props)
    }

    /**
     * 二重起動防止ファイルを作成する。
     *
     * @param processFileLocation
     * @param jobName
     */
    @Throws(IOException::class)
    protected fun createProcessFile(processFileLocation: String?, jobName: String?) {
        // 二重起動防止ファイルの保存先を作成する
        val location = Paths.get(processFileLocation)
        FileUtils.createDirectory(location)

        // 二重起動防止ファイルを作成する
        val path = location.resolve(jobName)
        try {
            Files.createFile(path)
        } catch (e: FileAlreadyExistsException) {
            log.error("二重起動防止ファイルが存在するため処理を中断します。[path={}]", path.toAbsolutePath().toString())
            throw e
        }
    }

    /**
     * 二重起動防止ファイルを削除する。
     *
     * @param status
     * @param processFileLocation
     * @param jobName
     */
    @Throws(IOException::class)
    protected fun deleteProcessFile(status: BatchStatus, processFileLocation: String?, jobName: String?) {
        if (status == BatchStatus.COMPLETED) {
            val location = Paths.get(processFileLocation)
            val path = location.resolve(jobName)
            Files.deleteIfExists(path)
        }
    }

    @Throws(JobExecutionAlreadyRunningException::class, JobRestartException::class, JobInstanceAlreadyCompleteException::class, JobParametersInvalidException::class, JobParametersNotFoundException::class)
    protected fun execute(job: Job, jobParameters: JobParameters): BatchStatus {
        var status = BatchStatus.UNKNOWN
        val nextParameters = getNextJobParameters(job, jobParameters)
        if (nextParameters !== null) {
            val execution = jobLauncher!!.run(job, nextParameters)
            if (publisher != null) {
                publisher!!.publishEvent(JobExecutionEvent(execution))
            }
            status = execution.status
        }
        return status
    }

    protected fun getNextJobParameters(job: Job, parameters: JobParameters): JobParameters {
        val name = job.name
        var mergeParameters = JobParameters()
        val lastInstances = jobExplorer!!.getJobInstances(name, 0, 1)
        val incrementer = job.jobParametersIncrementer
        val additional = parameters.parameters
        if (lastInstances.isEmpty()) {
            // Start from a completely clean sheet
            if (incrementer != null) {
                mergeParameters = incrementer.getNext(JobParameters())
            }
        } else {
            val previousExecutions = jobExplorer!!.getJobExecutions(lastInstances[0])
            val previousExecution = previousExecutions[0]
            if (previousExecution === null) {
                // Normally this will not happen - an instance exists with no executions
                if (incrementer != null) {
                    mergeParameters = incrementer.getNext(JobParameters())
                }
            } else if (isStoppedOrFailed(previousExecution) && job.isRestartable) {
                // Retry a failed or stopped execution
                mergeParameters = previousExecution.jobParameters
                // Non-identifying additional parameters can be removed to a retry
                removeNonIdentifying(additional)
            } else if (incrementer != null) {
                // New instance so increment the parameters if we can
                mergeParameters = incrementer.getNext(previousExecution.jobParameters)
            }
        }
        return merge(mergeParameters, additional)
    }

    protected fun isStoppedOrFailed(execution: JobExecution): Boolean {
        val status = execution.status
        return status == BatchStatus.STOPPED || status == BatchStatus.FAILED
    }

    protected fun removeNonIdentifying(parameters: MutableMap<String?, JobParameter>) {
        val copy: Map<String?, JobParameter> = HashMap(parameters)
        for ((key, value) in copy) {
            if (!value.isIdentifying) {
                parameters.remove(key)
            }
        }
    }

    protected fun merge(merged: JobParameters, parameters: Map<String?, JobParameter>?): JobParameters {
        var merged = merged
        val temp: MutableMap<String?, JobParameter> = HashMap()
        temp.putAll(merged.parameters)
        temp.putAll(parameters!!)
        merged = JobParameters(temp)
        return merged
    }

    companion object {
        private val log = LoggerFactory
                .getLogger(SingleJobCommandLineRunner::class.java)
        const val JOB_PARAMETER_JOB_NAME = "--job"
    }

}