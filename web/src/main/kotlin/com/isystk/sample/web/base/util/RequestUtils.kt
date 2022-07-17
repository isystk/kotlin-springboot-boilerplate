package com.isystk.sample.web.base.util

import org.apache.commons.lang3.StringUtils
import org.springframework.http.HttpHeaders
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

object RequestUtils {
    const val X_REQUESTED_WITH = "X-Requested-With"
    const val XMLHTTP_REQUEST = "XMLHttpRequest"

    /**
     * User-Agentを取得します。
     *
     * @param request
     * @return
     */
    fun getUserAgent(request: HttpServletRequest): String {
        return StringUtils.trimToEmpty(request.getHeader(HttpHeaders.USER_AGENT))
    }

    /**
     * Ajax通信であるかを示す値を返します。
     *
     * @param request
     * @return
     */
    fun isAjaxRequest(request: HttpServletRequest): Boolean {
        val header = request.getHeader(X_REQUESTED_WITH)
        return StringUtils.equalsIgnoreCase(XMLHTTP_REQUEST, header)
    }

    /**
     * HttpServletRequestを返します。
     *
     * @return
     */
    val request: HttpServletRequest?
        get() {
            val attributes = RequestContextHolder.getRequestAttributes()
            return (attributes as ServletRequestAttributes?)!!.request
        }

    /**
     * HttpServletRequestを返します。
     *
     * @return
     */
    val response: HttpServletResponse?
        get() {
            val attributes = RequestContextHolder.getRequestAttributes()
            return (attributes as ServletRequestAttributes?)!!.response
        }

    /**
     * サイトURLを返します。
     *
     * @return
     */
    val siteUrl: String?
        get() {
            val servletRequest = request
            val scheme = servletRequest!!.scheme
            val host = servletRequest.remoteHost
            val port = servletRequest.serverPort
            val path = servletRequest.contextPath
            var siteUrl: String? = null
            siteUrl = if (port == 80 || port == 443) {
                StringUtils.join(scheme, "://", host, path)
            } else {
                StringUtils.join(scheme, "://", host, ":", port.toString(), path)
            }
            return siteUrl
        }
}