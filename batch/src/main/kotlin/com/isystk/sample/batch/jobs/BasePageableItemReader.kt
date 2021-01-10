package com.isystk.sample.batch.jobs

import com.isystk.sample.batch.BatchConst
import com.isystk.sample.batch.context.BatchContextHolder
import com.isystk.sample.common.util.MDCUtils
import com.isystk.sample.domain.dao.AuditInfoHolder
import org.seasar.doma.jdbc.SelectOptions
import org.springframework.batch.item.database.AbstractPagingItemReader
import java.util.concurrent.CopyOnWriteArrayList

/**
 * ページングに対応したItemReaderの基底クラス
 *
 * @param <T>
</T> */
abstract class BasePageableItemReader<T> : AbstractPagingItemReader<T?>() {
    @Throws(Exception::class)
    override fun doRead(): T? {
        val context = BatchContextHolder.context
        val batchId = context.batchId

        // ThreadPoolを使用している場合は再設定する
        MDCUtils.putIfAbsent(BatchConst.MDC_BATCH_ID, batchId)
        val startDateTime = context.startDateTime
        AuditInfoHolder.set(batchId, startDateTime)
        return super.doRead()
    }

    override fun doReadPage() {
        if (results == null) {
            results = CopyOnWriteArrayList<T>()
        } else {
            results.clear()
        }
        results.addAll(list!!)
    }

    override fun doJumpToPage(itemIndex: Int) {}// 1ページは0になる

    /**
     * 検索オプションを返します。
     *
     * @return
     */
    protected val selectOptions: SelectOptions
        protected get() {
            val page = page // 1ページは0になる
            val perpage = pageSize
            val offset = page * perpage
            return SelectOptions.get().offset(offset).limit(perpage)
        }

    /**
     * ページング処理したリストを返します。
     *
     * @return
     */
    protected abstract val list: List<T>?
}