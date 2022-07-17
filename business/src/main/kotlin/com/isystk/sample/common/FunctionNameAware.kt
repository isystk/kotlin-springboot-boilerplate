package com.isystk.sample.common

/**
 * 機能名のマーカーインターフェース
 */
interface FunctionNameAware {
    /**
     * 機能名を返します。
     *
     * @return
     */
    abstract fun getFunctionName(): String
}