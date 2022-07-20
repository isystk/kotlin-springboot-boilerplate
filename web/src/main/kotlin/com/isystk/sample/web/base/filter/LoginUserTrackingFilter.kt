package com.isystk.sample.web.base.filter

import com.isystk.sample.common.Const
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * ログインIDをMDCに設定する
 */
class LoginUserTrackingFilter : OncePerRequestFilter() {
    var excludeUrlPatterns: List<String>? = null
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse,
                                  filterChain: FilterChain) {
        try {
            if (!shouldNotFilter(request)) {
                MDC.put(Const.MDC_LOGIN_USER_ID, "GUEST")
                userId.ifPresent { userId: String? -> MDC.put(Const.MDC_LOGIN_USER_ID, userId) }
            }
        } finally {
            filterChain.doFilter(request, response)
        }
    }

    protected val userId: Optional<String>
        protected get() {
            val authentication = SecurityContextHolder.getContext().authentication
            if (authentication != null) {
                val principal = authentication.principal
                if (principal is UserIdAware) {
                    val loginId = principal.userId + ""
                    return Optional.of(loginId)
                }
            }
            return Optional.empty()
        }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val exclude = excludeUrlPatterns!!
                .any { p: String? -> pathMatcher.match(p!!, request.servletPath) }
        return if (exclude) {
            true
        } else false
    }

    companion object {
        private val log = LoggerFactory
                .getLogger(LoginUserTrackingFilter::class.java)
        private val pathMatcher = AntPathMatcher()
    }
}