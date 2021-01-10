package com.isystk.sample.batch.jobs

import com.isystk.sample.batch.context.BatchContext
import com.isystk.sample.batch.context.BatchContextHolder
import com.isystk.sample.batch.item.ItemPosition
import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.annotation.OnProcessError
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import java.io.IOException

/**
 * 基底タスクレット
 */
abstract class BaseTasklet<I : ItemPosition?> : Tasklet {
    /**
     * メインメソッド
     *
     * @param contribution
     * @param chunkContext
     * @return
     * @throws Exception
     */
    @Throws(IOException::class)
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        val context = BatchContextHolder.context

        // 実処理
        doProcess(context)
        return RepeatStatus.FINISHED
    }

    /**
     * 実処理を実施します。
     *
     * @param context
     * @param item
     * @return
     */
    protected abstract fun doProcess(context: BatchContext?)

    /**
     * 例外発生時のデフォルト実装
     *
     * @param item
     * @param e
     */
    @OnProcessError
    protected fun onProcessError(item: I, e: Exception?) {
        log.error("failed to process item.", e)
        throw IllegalStateException(e)
    }

    companion object {
        private val log = LoggerFactory.getLogger(BaseTasklet::class.java)
    }
}