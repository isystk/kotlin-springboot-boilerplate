package com.isystk.sample.web.front

import com.isystk.sample.common.Const
import com.isystk.sample.common.FrontUrl
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
    fun passwordEncoder(): PasswordEncoder {
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
        val permittedUrls = arrayOf("*")
        val authenticatedUrls = arrayOf(FrontUrl.API_V1_MYCARTS + "/**")

        // 認証除外設定
        http.authorizeRequests() // 認証をかけない
                .antMatchers(*permittedUrls).permitAll() // マイページ配下は、認証をかける
                .antMatchers(*authenticatedUrls).authenticated() // それ以外は認証をかけない
                .anyRequest().permitAll().and().exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())

        // ログイン処理
        http.formLogin() // ログイン画面のURL
                .loginPage(Const.LOGIN_URL) // 認可を処理するURL
                .loginProcessingUrl(Const.LOGIN_PROCESSING_URL) // ログイン成功時の遷移先
                .successForwardUrl(Const.LOGIN_SUCCESS_URL) // ログイン失敗時の遷移先
                .failureUrl(Const.LOGIN_FAILURE_URL) // ログインIDのパラメータ名
                .usernameParameter("loginId") // パスワードのパラメータ名
                .passwordParameter("password").permitAll() // OAuth 2.0 Login機能を有効化
                .and()
                .oauth2Login()

        // ログアウト処理
        http.logout().logoutRequestMatcher(AntPathRequestMatcher(Const.LOGOUT_URL)) // Cookieを破棄する
                .deleteCookies("SESSION", "JSESSIONID") // ログアウト画面のURL
                .logoutUrl(Const.LOGOUT_URL) // ログアウト後の遷移先
                .logoutSuccessUrl(Const.LOGOUT_SUCCESS_URL) // ajaxの場合は、HTTPステータスを返す
                .defaultLogoutSuccessHandlerFor(HttpStatusReturningLogoutSuccessHandler()) { request: HttpServletRequest? -> RequestUtils.isAjaxRequest(request) } // セッションを破棄する
                .invalidateHttpSession(true).permitAll()
    }

    @Bean
    fun accessDeniedHandler(): AccessDeniedHandler {
        return DefaultAccessDeniedHandler()
    }

    @Bean
    fun authenticationEntryPoint(): AuthenticationEntryPoint {
        return DefaultAuthenticationEntryPoint(Const.LOGIN_URL, Const.LOGIN_TIMEOUT_URL)
    }
}