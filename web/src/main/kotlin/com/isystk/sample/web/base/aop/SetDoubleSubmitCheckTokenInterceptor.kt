package com.isystk.sample.web.base.aop

import com.isystk.sample.domain.dao.DoubleSubmitCheckTokenHolder
import com.isystk.sample.web.base.security.DoubleSubmitCheckToken
import com.isystk.sample.web.base.security.DoubleSubmitCheckToken.renewToken
import org.apache.commons.lang3.StringUtils
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 二重送信防止チェックのトークンをセッションに設定する
 */
class SetDoubleSubmitCheckTokenInterceptor : BaseHandlerInterceptor() {
    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        // コントローラーの動作前
        val expected = DoubleSubmitCheckToken.getExpectedToken(request)
        val actual = DoubleSubmitCheckToken.getActualToken(request)
        DoubleSubmitCheckTokenHolder.set(expected, actual)
        return true
    }

    @Throws(Exception::class)
    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any,
                            modelAndView: ModelAndView?) {
        // コントローラーの動作後
        if (StringUtils.equalsIgnoreCase(request.method, "POST")) {
            // POSTされたときにトークンが一致していれば新たなトークンを発行する
            val expected = DoubleSubmitCheckToken.getExpectedToken(request)
            val actual = DoubleSubmitCheckToken.getActualToken(request)
            if (expected != null && actual != null && expected == actual) {
                renewToken(request)
            }
        }
    }

    @Throws(Exception::class)
    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse,
                                 handler: Any, ex: Exception?) {
        // 処理完了後
        DoubleSubmitCheckTokenHolder.clear()
    }

}