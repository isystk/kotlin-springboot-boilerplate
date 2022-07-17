package com.isystk.sample.common.exception

/**
 * データ不存在エラー
 */
class NoDataFoundException : RuntimeException {
    /**
     * コンストラクタ
     */
    constructor(message: String?) : super(message) {}

    /**
     * コンストラクタ
     */
    constructor(e: Exception?) : super(e) {}

    companion object {
        private const val serialVersionUID = -3553226048751584224L
    }
}