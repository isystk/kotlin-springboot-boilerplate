package com.isystk.sample.batch.jobs

import com.isystk.sample.batch.context.BatchContext
import com.isystk.sample.batch.context.BatchContextHolder
import com.isystk.sample.common.logger
import org.springframework.batch.core.annotation.OnProcessError
import org.springframework.batch.item.ItemProcessor
import org.springframework.validation.BindingResult
import org.springframework.validation.DataBinder
import org.springframework.validation.Validator

/**
 * 基底プロセッサー
 *
 * @param <I>
 * @param <O>
</O></I> */
abstract class BaseItemProcessor<I, O> : ItemProcessor<I, O?> {
    override fun process(item: I): O? {
        val validator = validator
        val context = BatchContextHolder.context

        // 対象件数を加算する
        context!!.increaseTotalCount()
        if (validator != null) {
            val binder = DataBinder(item)
            binder.validator = validator
            binder.validate()
            val result = binder.bindingResult
            if (result.hasErrors()) {
                // バリデーションエラーがある場合
                onValidationError(context, result, item)

                // エラー件数をカウントする
                increaseErrorCount(context, result, item)

                // nullを返すとItemWriterに渡されない
                return null
            }
        }

        // 実処理
        val output = doProcess(context, item)

        // 処理件数をカウントする
        increaseProcessCount(context, item)
        return output
    }

    /**
     * エラー件数を加算します。
     *
     * @param context
     * @param result
     * @param item
     */
    protected fun increaseErrorCount(context: BatchContext?, result: BindingResult?, item: I) {
        context!!.increaseErrorCount()
    }

    /**
     * 処理件数を加算します。
     *
     * @param context
     * @param item
     */
    protected fun increaseProcessCount(context: BatchContext?, item: I) {
        context!!.increaseProcessCount()
    }

    /**
     * 対象件数を加算します。
     *
     * @param context
     * @param item
     */
    protected fun increaseTotalCount(context: BatchContext, item: I) {
        context.increaseProcessCount()
    }

    /**
     * バリデーションエラーが発生した場合に処理します。
     *
     * @param context
     * @param result
     * @param item
     */
    protected abstract fun onValidationError(context: BatchContext?, result: BindingResult?, item: I)

    /**
     * 実処理を実施します。
     *
     * @param context
     * @param item
     * @return
     */
    protected abstract fun doProcess(context: BatchContext?, item: I): O

    /**
     * バリデーターを取得します。
     *
     * @return
     */
    protected abstract val validator: Validator?

    /**
     * 例外発生時のデフォルト実装
     *
     * @param item
     * @param e
     */
    @OnProcessError
    protected fun onProcessError(item: I, e: Exception?) {
        logger.error("failed to process item.", e)
        throw IllegalStateException(e)
    }

}