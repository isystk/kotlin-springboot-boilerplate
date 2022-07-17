package com.isystk.sample.common.exception

/**
 * 二重送信エラー
 */
class DoubleSubmitErrorException : RuntimeException {

    /**
     * コンストラクタ
     */
    constructor() : super() {}

    companion object {
        private const val serialVersionUID = -6212475941372852475L
    }

}