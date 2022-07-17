package com.isystk.sample.common.exception

/**
 * アプリケーション内部で発生した例外を catch & throw するための実行時例外です。
 */
class SystemException : RuntimeException {
    /**
     * @param message この例外に関する詳細な情報.
     * @param cause   原因となった例外
     */
    constructor(message: String?, cause: Throwable?) : super(message, cause) {
        require(!((message == null || message.trim { it <= ' ' }.length == 0) && cause == null)) { "Either of message or cause must be not null." }
    }

    /**
     * メッセージを指定するコンストラクタ。
     *
     * @param message メッセージ
     */
    constructor(message: String?) : super(message) {}

    /**
     * 根源の例外を指定するコンストラクタ。
     *
     * @param cause 根源の例外
     */
    constructor(cause: Throwable?) : super(cause) {}

    /*
   * (non-Javadoc)
   *
   * @see java.lang.Throwable#toString()
   */
    override fun toString(): String {
        val sb = StringBuilder(javaClass.name)
        if (cause != null) {
            sb.append(": ").append(cause.javaClass.name)
        }
        val message = localizedMessage
        if (message != null) {
            sb.append(": ").append(message)
        }
        return sb.toString()
    }

    companion object {
        private const val serialVersionUID = 8462188377363105093L
    }
}