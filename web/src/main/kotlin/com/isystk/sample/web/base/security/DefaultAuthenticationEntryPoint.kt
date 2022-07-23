package com.isystk.sample.web.base.security

import com.isystk.sample.common.logger
import com.isystk.sample.web.base.util.RequestUtils
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * ログイン画面を表示する際に、有効ではないセッションIDが渡ってきた場合は、 <br></br> タイムアウトした場合のURLにリダイレクトする。 <br></br>
 * ただし、AJAX通信の場合は、ステータスコードのみを返す。
 */
class DefaultAuthenticationEntryPoint
/**
 * @param loginUrl
 * @param loginTimeoutUrl
 */(loginUrl: String?, private val loginTimeoutUrl: String) : LoginUrlAuthenticationEntryPoint(loginUrl) {
    @Throws(IOException::class, ServletException::class)
    override fun commence(request: HttpServletRequest, response: HttpServletResponse,
                          authException: AuthenticationException) {
        if (RequestUtils.isAjaxRequest(request)) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            return
        }
        super.commence(request, response, authException)
    }

    override fun determineUrlToUseForThisRequest(request: HttpServletRequest,
                                                 response: HttpServletResponse,
                                                 exception: AuthenticationException): String {
        val url = super.determineUrlToUseForThisRequest(request, response, exception)
        if (request.requestedSessionId != null && !request.isRequestedSessionIdValid) {
            if (logger.isDebugEnabled) {
                logger.debug("セッションがタイムアウトしました。")
            }
            return loginTimeoutUrl
        }
        return url
    }

}