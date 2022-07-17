package com.isystk.sample.common.exception

/**
 * ファイル不存在エラー
 */
class FileNotFoundException : RuntimeException {
    /**
     * コンストラクタ
     */
    constructor(message: String?) : super(message) {}

    /**
     * コンストラクタ
     */
    constructor(e: Exception?) : super(e) {}

    companion object {
        private const val serialVersionUID = -6212475941372852475L
    }
}