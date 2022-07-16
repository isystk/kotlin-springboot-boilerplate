package com.isystk.sample.batch.context

import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

/**
 * バッチ処理コンテキスト
 */
class BatchContext {
    var batchId: String? = null
    var batchName: String? = null
    var startDateTime: LocalDateTime? = null
    val processCount = AtomicLong(0)
    val errorCount = AtomicLong(0)
    val totalCount = AtomicLong(0)

    // 追加情報（任意のオブジェクトを伝搬させたい場合のため）
    fun additionalInfo(): MutableMap<String, Any> {
        return ConcurrentHashMap();
    }

    /**
     * バッチIDとバッチ名を設定します。
     *
     * @param batchId
     * @param batchName
     * @param localDateTime
     */
    operator fun set(batchId: String?, batchName: String?, localDateTime: LocalDateTime?) {
        synchronized(lock) {
            this.batchId = batchId
            this.batchName = batchName
            startDateTime = localDateTime
        }
    }

    /**
     * 処理件数を加算します。
     */
    fun increaseProcessCount() {
        processCount.incrementAndGet()
    }

    /**
     * 処理件数を返します。
     *
     * @return
     */
    fun getProcessCount(): Long {
        return processCount.toInt().toLong()
    }

    /**
     * エラー件数を加算します。
     */
    fun increaseErrorCount() {
        errorCount.incrementAndGet()
    }

    /**
     * エラー件数を返します。
     *
     * @return
     */
    fun getErrorCount(): Long {
        return errorCount.toInt().toLong()
    }

    /**
     * 対象件数を加算します。
     */
    fun increaseTotalCount() {
        totalCount.incrementAndGet()
    }

    /**
     * 対象件数を返します。
     *
     * @return
     */
    fun getTotalCount(): Long {
        return totalCount.toInt().toLong()
    }

    /**
     * 保持している情報をクリアします。
     */
    fun clear() {
        synchronized(lock) {
            batchId = null
            batchName = null
            startDateTime = null
            processCount.set(0)
            errorCount.set(0)
            totalCount.set(0)
            additionalInfo().clear()
        }
    }

    fun getAdditionalInfo(): Map<String, Any> {
        return additionalInfo()
    }

    companion object {
        private val lock = Any()
    }
}