package com.isystk.sample.batch.listener

import com.isystk.sample.batch.BatchConst
import com.isystk.sample.batch.context.BatchContext
import com.isystk.sample.batch.context.BatchContextHolder
import com.isystk.sample.common.util.MDCUtils
import com.isystk.sample.domain.dao.AuditInfoHolder
import org.slf4j.LoggerFactory
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.listener.StepExecutionListenerSupport

abstract class BaseStepExecutionListener : StepExecutionListenerSupport() {
    override fun beforeStep(stepExecution: StepExecution) {
        val context = BatchContextHolder.context

        // MDCを設定する
        setMDCIfEmpty(context, stepExecution)

        // 監査情報を設定する
        setAuditInfoIfEmpty(context)

        // 機能別の初期化処理を呼び出す
        before(context, stepExecution)

        // ログ出力
        logBeforeStep(context, stepExecution)
    }

    override fun afterStep(stepExecution: StepExecution): ExitStatus? {
        val context = BatchContextHolder.context

        // 機能別の終了処理を呼び出す
        try {
            after(context, stepExecution)
        } catch (e: Exception) {
            log.error("exception occurred. ", e)
            throw IllegalStateException(e)
        }

        // ログ出力
        logAfterStep(context, stepExecution)
        return stepExecution.exitStatus
    }

    /**
     * MDCのThreadLocal変数が取り出せないときは必要に応じて設定しなおす。
     *
     * @param context
     * @param stepExecution
     */
    protected fun setMDCIfEmpty(context: BatchContext?, stepExecution: StepExecution?) {
        val batchId = context!!.batchId
        MDCUtils.putIfAbsent(BatchConst.MDC_BATCH_ID, batchId)
    }

    /**
     * スレッドプールを使用しているとThreadLocalから値が取り出せないことがあるため設定しなおす。
     *
     * @param context
     */
    protected fun setAuditInfoIfEmpty(context: BatchContext?) {
        val batchId = context!!.batchId
        val startDateTime = context.startDateTime
        AuditInfoHolder.set(batchId, startDateTime)
    }

    /**
     * ステップ開始時にログを出力します。
     *
     * @param context
     * @param stepExecution
     */
    protected fun logBeforeStep(context: BatchContext?, stepExecution: StepExecution) {
        val stepName = stepExecution.stepName
        log.info("Step:{} ---- START ----", stepName)
    }

    /**
     * ステップ終了時にログを出力します。
     *
     * @param context
     * @param stepExecution
     */
    protected fun logAfterStep(context: BatchContext?, stepExecution: StepExecution) {
        val stepName = stepExecution.stepName
        log.info("Step:{} ---- END ----", stepName)
    }

    /**
     * 機能別の初期化処理を呼び出します。
     *
     * @param context
     * @param stepExecution
     */
    protected fun before(context: BatchContext?, stepExecution: StepExecution?) {}

    /**
     * 機能別の終了処理を呼び出します。
     *
     * @param context
     * @param stepExecution
     */
    protected fun after(context: BatchContext?, stepExecution: StepExecution?) {}

    companion object {
        private val log = LoggerFactory
                .getLogger(BaseStepExecutionListener::class.java)
    }
}