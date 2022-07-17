package com.isystk.sample.web.base.security

import com.isystk.sample.common.Const
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.RedirectStrategy
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.csrf.InvalidCsrfTokenException
import org.springframework.security.web.csrf.MissingCsrfTokenException
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class DefaultAccessDeniedHandler : AccessDeniedHandler {
    private val redirectStrategy: RedirectStrategy = DefaultRedirectStrategy()

    @Autowired
    var authenticationEntryPoint: AuthenticationEntryPoint? = null
    @Throws(IOException::class, ServletException::class)
    override fun handle(request: HttpServletRequest, response: HttpServletResponse,
                        accessDeniedException: AccessDeniedException) {
        if (response.isCommitted) {
            return
        }
        if (accessDeniedException is MissingCsrfTokenException
                || accessDeniedException is InvalidCsrfTokenException) {
            authenticationEntryPoint!!.commence(request, response, null)
        } else {
            redirectStrategy.sendRedirect(request, response, Const.FORBIDDEN_URL)
        }
    }
}