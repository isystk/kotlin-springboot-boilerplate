package com.isystk.sample.web.base.aop

import com.isystk.sample.common.Const
import com.isystk.sample.common.FunctionNameAware
import org.slf4j.MDC
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 機能名をログに出力する
 */
class LoggingFunctionNameInterceptor : BaseHandlerInterceptor() {
    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        // コントローラーの動作前
        val fna = getBean(handler, FunctionNameAware::class.java)
        if (fna != null) {
            val functionName = fna.getFunctionName()
            MDC.put(MDC_FUNCTION_NAME, functionName)
        }
        return true
    }

    companion object {
        private const val MDC_FUNCTION_NAME = Const.MDC_FUNCTION_NAME
    }
}