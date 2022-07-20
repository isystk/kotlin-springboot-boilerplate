package com.isystk.sample.web.base.aop

import com.isystk.sample.domain.dao.AuditInfoHolder
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.servlet.ModelAndView
import java.time.LocalDateTime
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * ログインユーザーを監査情報ホルダーに設定する
 */
class SetAuditInfoInterceptor : BaseHandlerInterceptor() {
    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        // コントローラーの動作前
        val now = LocalDateTime.now()

        // 未ログインの場合は、ゲスト扱いにする
        AuditInfoHolder.set("GUEST", now)

        // ログインユーザーが存在する場合
        if(loginUser!==null) {
            // 監査情報を設定する
            AuditInfoHolder.set(loginUser!!.username, now)
        }
        return true
    }

    @Throws(Exception::class)
    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any,
                            modelAndView: ModelAndView?) {
        // コントローラーの動作後

        // 監査情報をクリアする
        AuditInfoHolder.clear()
    }

    /**
     * ログインユーザを取得する
     *
     * @return
     */
    protected val loginUser: UserDetails?
        get() {
            val auth = SecurityContextHolder.getContext().authentication
            if (auth != null) {
                val principal = auth.principal
                if (principal is UserDetails) {
                    return principal
                }
            }
            return null
        }

    companion object {
        private val log = LoggerFactory
                .getLogger(SetAuditInfoInterceptor::class.java)
    }
}