package com.isystk.sample.web.base.util

import org.springframework.web.util.WebUtils
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

object SessionUtils {
    /**
     * 指定した属性名で値を取得します。
     *
     * @param request
     * @param attributeName
     * @param <T>
     * @return
    </T> */
    fun <T> getAttribute(request: HttpServletRequest, attributeName: String?): T? {
        var ret: T? = null
        val session = getSession(request)
        val mutex = getMutex(request)
        if (mutex != null) {
            synchronized(mutex) {
                if (session != null) {
                    ret = session.getAttribute(attributeName) as T
                }
            }
        }
        return ret
    }

    /**
     * 指定した属性名で値を設定します。
     *
     * @param request
     * @param attributeName
     * @param value
     * @return
     */
    fun setAttribute(request: HttpServletRequest, attributeName: String?, value: Any?) {
        val session = getSession(request)
        val mutex = getMutex(request)
        if (mutex != null) {
            synchronized(mutex) {
                if (session != null) {
                    session.setAttribute(attributeName, value)
                }
            }
        }
    }

    /**
     * mutexを取得します。
     *
     * @param request
     * @return
     */
    fun getMutex(request: HttpServletRequest): Any? {
        val session = getSession(request)
        return if (session != null) {
            WebUtils.getSessionMutex(session)
        } else null
    }

    /**
     * 存在するセッションを取得します。
     *
     * @param request
     * @return
     */
    fun getSession(request: HttpServletRequest): HttpSession? {
        return request.getSession(false)
    }
}