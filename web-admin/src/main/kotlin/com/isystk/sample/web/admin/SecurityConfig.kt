package com.isystk.sample.web.admin

import com.isystk.sample.common.Const
import com.isystk.sample.web.base.security.BaseSecurityConfig
import com.isystk.sample.web.base.security.DefaultAccessDeniedHandler
import com.isystk.sample.web.base.security.DefaultAuthenticationEntryPoint
import com.isystk.sample.web.base.util.RequestUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.servlet.http.HttpServletRequest

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // アノテーションで役割、権限チェックを行うために定義する
@Configuration
class SecurityConfig : BaseSecurityConfig() {
    @Autowired
    var userDetailsService: UserDetailsService? = null
    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        // 静的ファイルへのアクセスは認証をかけない
        web.ignoring() //
                .antMatchers(Const.WEBJARS_URL, Const.STATIC_RESOURCES_URL)
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService) //
                .passwordEncoder(passwordEncoder())
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        // CookieにCSRFトークンを保存する
        http.csrf() //
                .csrfTokenRepository(CookieCsrfTokenRepository())
        val permittedUrls = arrayOf(Const.LOGIN_TIMEOUT_URL, Const.FORBIDDEN_URL, Const.ERROR_URL, Const.RESET_PASSWORD_URL,
                Const.CHANGE_PASSWORD_URL)

        // 認証除外設定
        http.authorizeRequests() // エラー画面は認証をかけない
                .antMatchers(*permittedUrls).permitAll() // エラー画面以外は、認証をかける
                .anyRequest().authenticated().and().exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())

        // ログイン処理
        http.formLogin() // ログイン画面のURL
                .loginPage(Const.LOGIN_URL) // 認可を処理するURL
                .loginProcessingUrl(Const.LOGIN_PROCESSING_URL) // ログイン成功時の遷移先
                .successForwardUrl(Const.LOGIN_SUCCESS_URL) // ログイン失敗時の遷移先
                .failureUrl(Const.LOGIN_FAILURE_URL) // ログインIDのパラメータ名
                .usernameParameter("loginId") // パスワードのパラメータ名
                .passwordParameter("password").permitAll()

        // ログアウト処理
        http.logout().logoutRequestMatcher(AntPathRequestMatcher(Const.LOGOUT_URL)) // Cookieを破棄する
                .deleteCookies("SESSION", "JSESSIONID") // ログアウト画面のURL
                .logoutUrl(Const.LOGOUT_URL) // ログアウト後の遷移先
                .logoutSuccessUrl(Const.LOGOUT_SUCCESS_URL) // ajaxの場合は、HTTPステータスを返す
                .defaultLogoutSuccessHandlerFor(HttpStatusReturningLogoutSuccessHandler()) { request: HttpServletRequest -> RequestUtils.isAjaxRequest(request) } // セッションを破棄する
                .invalidateHttpSession(true).permitAll()
    }

    @Bean
    open fun accessDeniedHandler(): AccessDeniedHandler {
        return DefaultAccessDeniedHandler()
    }

    @Bean
    open fun authenticationEntryPoint(): AuthenticationEntryPoint {
        return DefaultAuthenticationEntryPoint(Const.LOGIN_URL, Const.LOGIN_TIMEOUT_URL)
    }
}