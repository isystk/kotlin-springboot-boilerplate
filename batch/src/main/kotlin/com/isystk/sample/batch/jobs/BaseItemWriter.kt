package com.isystk.sample.batch.jobs

import com.isystk.sample.batch.context.BatchContext
import com.isystk.sample.batch.context.BatchContextHolder
import org.springframework.batch.item.ItemWriter

abstract class BaseItemWriter<T> : ItemWriter<T> {
    @Throws(Exception::class)
    override fun write(items: List<T>) {
        // コンテキストを取り出す
        val context = BatchContextHolder.context

        // 書き込む
        doWrite(context, items)
    }

    /**
     * 引数に渡されたアイテムを書き込みます。
     *
     * @param context
     * @param items
     */
    protected abstract fun doWrite(context: BatchContext?, items: List<T>?)
}