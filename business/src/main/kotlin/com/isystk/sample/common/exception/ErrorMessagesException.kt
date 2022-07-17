package com.isystk.sample.common.exception

/**
 * エラーメッセージ Exception
 */
class ErrorMessagesException : RuntimeException {
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