package com.isystk.sample.common.util

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.MessageSourceResolvable
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class MessageUtils {
    @Autowired
    fun setMessageSource(messageSource: MessageSource?) {
        Companion.messageSource = messageSource
    }

    companion object {
        private var messageSource: MessageSource? = null

        /**
         * メッセージを取得します。
         *
         * @param key
         * @param args
         * @return
         */
        fun getMessage(key: String?, vararg args: Any?): String {
            val locale = LocaleContextHolder.getLocale()
            return messageSource!!.getMessage(key!!, args, locale)
        }

        /**
         * ロケールを指定してメッセージを取得します。
         *
         * @param key
         * @param locale
         * @param args
         * @return
         */
        fun getMessage(key: String?, locale: Locale?, vararg args: Any?): String {
            return messageSource!!.getMessage(key!!, args, locale!!)
        }

        /**
         * メッセージを取得します。
         *
         * @param resolvable
         * @return
         */
        fun getMessage(resolvable: MessageSourceResolvable?): String {
            val locale = LocaleContextHolder.getLocale()
            return messageSource!!.getMessage(resolvable!!, locale)
        }
    }
}