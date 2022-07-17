package com.isystk.sample.common.util

import org.slf4j.MDC

object MDCUtils {
    /**
     * MDCの値を設定します。
     *
     * @param key
     * @param value
     */
    fun put(key: String?, value: String?) {
        MDC.put(key, value)
    }

    /**
     * 未設定の場合のみMDCの値を設定します。
     *
     * @param key
     * @param value
     */
    fun putIfAbsent(key: String?, value: String?) {
        if (MDC.get(key) == null) {
            MDC.put(key, value)
        }
    }

    /**
     * MDCの値を削除します。
     *
     * @param key
     */
    fun remove(key: String?) {
        MDC.remove(key)
    }
}