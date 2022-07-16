package com.isystk.sample.web.base.security

import org.springframework.security.web.servlet.support.csrf.CsrfRequestDataValueProcessor
import org.springframework.web.servlet.support.RequestDataValueProcessor
import javax.servlet.http.HttpServletRequest

/**
 * 二重送信防止チェックのトークンを埋める
 */
class DoubleSubmitCheckingRequestDataValueProcessor : RequestDataValueProcessor {
    override fun processAction(request: HttpServletRequest, action: String, httpMethod: String): String {
        ACTION_HOLDER.set(action)
        return PROCESSOR.processAction(request, action, httpMethod)
    }

    override fun processFormFieldValue(request: HttpServletRequest, name: String?, value: String,
                                       type: String): String {
        return PROCESSOR.processFormFieldValue(request, name, value, type)
    }

    override fun getExtraHiddenFields(request: HttpServletRequest): Map<String, String>? {
        val map = PROCESSOR.getExtraHiddenFields(request)
        if (!map!!.isEmpty()) {
            val action = ACTION_HOLDER.get()
            var token = DoubleSubmitCheckToken.getExpectedToken(request, action)
            if (token == null) {
                token = DoubleSubmitCheckToken.renewToken(request, action)
            }
            map[DoubleSubmitCheckToken.DOUBLE_SUBMIT_CHECK_PARAMETER] = token
            ACTION_HOLDER.remove()
        }
        return map
    }

    override fun processUrl(request: HttpServletRequest, url: String): String {
        return PROCESSOR.processUrl(request, url)
    }

    companion object {
        private val PROCESSOR = CsrfRequestDataValueProcessor()
        private val ACTION_HOLDER = ThreadLocal<String>()
    }
}