package com.isystk.sample.web.base.controller

import com.isystk.sample.common.util.MessageUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import java.util.*

open class BaseController {
    /**
     * コンテキストを返します。
     *
     * @return
     */
    @Autowired
    protected var applicationContext: ApplicationContext? = null

    /**
     * 入力エラーの共通メッセージを返します。
     *
     * @return
     */
    protected val validationErrorMessage: String
        protected get() = getMessage(VALIDATION_ERROR)

    /**
     * メッセージを取得します。
     *
     * @param key
     * @param args
     * @return
     */
    protected fun getMessage(key: String?, vararg args: Any?): String {
        return MessageUtils.getMessage(key, *args)
    }

    /**
     * ロケールを指定してメッセージを取得します。
     *
     * @param key
     * @param args
     * @param locale
     * @return
     */
    protected fun getMessage(key: String?, args: Array<Any?>?, locale: Locale?): String {
        return MessageUtils.getMessage(key, args, locale)
    }

    companion object {
        const val VALIDATION_ERROR = "ValidationError"
    }
}