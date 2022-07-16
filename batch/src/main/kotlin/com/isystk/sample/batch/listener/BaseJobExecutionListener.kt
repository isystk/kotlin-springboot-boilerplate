package com.isystk.sample.batch.listener

import com.isystk.sample.batch.BatchConst
import com.isystk.sample.batch.context.BatchContext
import com.isystk.sample.batch.context.BatchContextHolder
import com.isystk.sample.common.util.DateUtils
import com.isystk.sample.common.util.MDCUtils
import com.isystk.sample.domain.dao.AuditInfoHolder
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.listener.JobExecutionListenerSupport
import java.time.format.DateTimeFormatter
import java.util.function.Consumer

abstract class BaseJobExecutionListener : JobExecutionListenerSupport() {
    override fun beforeJob(jobExecution: JobExecution) {
        val batchId = batchId
        val batchName = batchName
        val startTime = jobExecution.startTime
        val startDateTime = DateUtils.toLocalDateTime(startTime)
        MDCUtils.putIfAbsent(BatchConst.MDC_BATCH_ID, batchId)
        log.info("*********************************************")
        log.info("* バッチID : {}", batchId)
        log.info("* バッチ名 : {}", batchName)
        log.info("* 開始時刻 : {}", DateUtils.format(startTime, YYYY_MM_DD_HHmmss))
        log.info("*********************************************")

        // 監査情報を設定する
        AuditInfoHolder.set(batchId, startDateTime)

        // コンテキストを設定する
        val context = BatchContextHolder.context
        context!![batchId, batchName] = startDateTime

        // 機能別の初期化処理を呼び出す
        before(jobExecution, context)
    }

    override fun afterJob(jobExecution: JobExecution) {
        // コンテキストを取り出す
        val context = BatchContextHolder.context

        // 機能別の終了処理を呼び出す
        try {
            after(jobExecution, context)
        } catch (t: Throwable) {
            log.error("exception occurred. ", t)
            throw IllegalStateException(t)
        } finally {
            // 共通の終了処理
            try {
                val batchId = context.batchId
                val batchName = context.batchName
                val jobStatus = jobExecution.status
                val endTime = jobExecution.endTime
                if (log.isDebugEnabled) {
                    val jobId = jobExecution.jobId
                    val jobInstance = jobExecution.jobInstance
                    val jobInstanceId = jobInstance.instanceId
                    log.debug("job executed. [job={}(JobInstanceId:{} status:{})] in {}ms", jobId,
                            jobInstanceId,
                            jobStatus, took(jobExecution))
                    jobExecution.stepExecutions
                            .forEach(
                                    Consumer { s: StepExecution -> log.debug("step executed. [step={}] in {}ms", s.stepName, took(s)) })
                }
                if (!jobStatus.isRunning) {
                    log.info("*********************************************")
                    log.info("* バッチID   : {}", batchId)
                    log.info("* バッチ名   : {}", batchName)
                    log.info("* ステータス : {}", jobStatus.batchStatus.toString())
                    log.info("* 対象件数   : {}", context!!.totalCount)
                    log.info("* 処理件数   : {}", context!!.processCount)
                    log.info("* エラー件数 : {}", context!!.errorCount)
                    log.info("* 終了時刻   : {}", DateUtils.format(endTime, YYYY_MM_DD_HHmmss))
                    log.info("*********************************************")
                }
            } finally {
                MDC.remove(BatchConst.MDC_BATCH_ID)

                // 監査情報をクリアする
                AuditInfoHolder.clear()

                // ジョブコンテキストをクリアする
                context!!.clear()
            }
        }
    }

    protected fun took(jobExecution: JobExecution): Long {
        return jobExecution.endTime.time - jobExecution.startTime.time
    }

    protected fun took(stepExecution: StepExecution): Long {
        return stepExecution.endTime!!.time - stepExecution.startTime.time
    }

    /**
     * バッチIDを返します。
     *
     * @return
     */
    protected abstract val batchId: String

    /**
     * バッチ名を返します。
     *
     * @return
     */
    protected abstract val batchName: String

    /**
     * 機能別の初期化処理を呼び出す
     *
     * @param jobExecution
     * @param context
     */
    protected fun before(jobExecution: JobExecution?, context: BatchContext?) {}

    /**
     * 機能別の終了処理を呼び出す
     *
     * @param jobExecution
     * @param context
     */
    protected fun after(jobExecution: JobExecution?, context: BatchContext?) {}

    companion object {
        // yyyy/MM/dd HH:mm:ss
        private val YYYY_MM_DD_HHmmss = DateTimeFormatter
                .ofPattern("yyyy/MM/dd HH:mm:ss")
        private val log = LoggerFactory.getLogger(
                BaseJobExecutionListener::class.java)
    }
}