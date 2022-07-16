package com.isystk.sample.batch.jobs

import com.isystk.sample.batch.BatchConst
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.job.flow.FlowExecutionStatus
import org.springframework.batch.core.job.flow.JobExecutionDecider
import org.springframework.batch.item.ExecutionContext

/**
 * 基底ジョブデサイダー
 */
abstract class BaseJobDecider : JobExecutionDecider {
    override fun decide(jobExecution: JobExecution, stepExecution: StepExecution?): FlowExecutionStatus {
        val context = jobExecution.executionContext
        return if (!decideToProceed(context)) {
            FlowExecutionStatus(BatchConst.EXECUTION_STATUS_SKIP)
        } else FlowExecutionStatus.COMPLETED
    }

    /**
     * Falseを返した場合は処理をスキップします。
     *
     * @param context
     * @return
     */
    protected abstract fun decideToProceed(context: ExecutionContext?): Boolean
}