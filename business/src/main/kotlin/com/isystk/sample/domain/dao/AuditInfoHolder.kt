package com.isystk.sample.domain.dao

import java.time.LocalDateTime

/**
 * 監査情報ホルダー
 */
object AuditInfoHolder {
    /**
     * ログインユーザー
     */
    private val AUDIT_USER = ThreadLocal<String>()

    /**
     * ログイン日時
     */
    private val AUDIT_DATE_TIME = ThreadLocal<LocalDateTime>()

    /**
     * 監査情報を保存します。
     *
     * @param username
     */
    operator fun set(username: String, localDateTime: LocalDateTime) {
        AUDIT_USER.set(username)
        AUDIT_DATE_TIME.set(localDateTime)
    }

    /**
     * 監査ユーザーを返します。
     *
     * @return
     */
    val auditUser: String
        get() = AUDIT_USER.get()

    /**
     * 監査時刻を返します。
     *
     * @return
     */
    val auditDateTime: LocalDateTime
        get() = AUDIT_DATE_TIME.get()

    /**
     * 監査情報をクリアします。
     */
    fun clear() {
        AUDIT_USER.remove()
        AUDIT_DATE_TIME.remove()
    }
}