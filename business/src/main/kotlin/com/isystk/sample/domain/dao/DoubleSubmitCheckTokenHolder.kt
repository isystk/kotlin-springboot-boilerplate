package com.isystk.sample.domain.dao

/**
 * 二重送信防止チェックトークンホルダー
 */
object DoubleSubmitCheckTokenHolder {
    private val EXPECTED_TOKEN = ThreadLocal<String>()
    private val ACTUAL_TOKEN = ThreadLocal<String>()

    /**
     * トークンを保存します。
     *
     * @param expected
     * @param actual
     */
    operator fun set(expected: String?, actual: String?) {
        EXPECTED_TOKEN.set(expected)
        ACTUAL_TOKEN.set(actual)
    }

    /**
     * セッションに保存されていたトークンを返します。
     *
     * @return
     */
    val expectedToken: String?
        get() = EXPECTED_TOKEN.get()

    /**
     * 画面から渡ってきたトークンを返します。
     *
     * @return
     */
    val actualToken: String?
        get() = ACTUAL_TOKEN.get()

    /**
     * 監査情報をクリアします。
     */
    fun clear() {
        EXPECTED_TOKEN.remove()
        ACTUAL_TOKEN.remove()
    }
}