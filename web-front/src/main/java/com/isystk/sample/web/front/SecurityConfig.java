package com.isystk.sample.web.front;

import static com.isystk.sample.common.Const.*;
import static com.isystk.sample.common.FrontUrl.*;

import com.isystk.sample.web.base.security.BaseSecurityConfig;
import com.isystk.sample.web.base.security.DefaultAccessDeniedHandler;
import com.isystk.sample.web.base.security.DefaultAuthenticationEntryPoint;
import com.isystk.sample.web.base.util.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
// アノテーションで役割、権限チェックを行うために定義する
@Configuration
public class SecurityConfig extends BaseSecurityConfig {

  @Autowired
  UserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    // 静的ファイルへのアクセスは認証をかけない
    web.ignoring()//
        .antMatchers(WEBJARS_URL, STATIC_RESOURCES_URL);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService)//
        .passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
//		// CookieにCSRFトークンを保存する
//		http.csrf()//
//				.csrfTokenRepository(new CookieCsrfTokenRepository());
    http.csrf().disable();

    String[] permittedUrls = {"*"};

    String[] authenticatedUrls = {API_V1_MEMBER + "/**"};

    // 認証除外設定
    http.authorizeRequests()
        // 認証をかけない
        .antMatchers(permittedUrls).permitAll()
        // マイページ配下は、認証をかける
        .antMatchers(authenticatedUrls).authenticated()
        // それ以外は認証をかけない
        .anyRequest().permitAll().and().exceptionHandling()
        .authenticationEntryPoint(authenticationEntryPoint())
        .accessDeniedHandler(accessDeniedHandler());

    // ログイン処理
    http.formLogin()
        // ログイン画面のURL
        .loginPage(LOGIN_URL)
        // 認可を処理するURL
        .loginProcessingUrl(API_V1_AUTH)
        // ログイン成功時の遷移先
        .successForwardUrl(API_V1_LOGIN_SUCCESS_URL)
        // ログイン失敗時の遷移先
        .failureUrl(API_V1_LOGIN_FAILURE_URL)
        // ログインIDのパラメータ名
        .usernameParameter("loginId")
        // パスワードのパラメータ名
        .passwordParameter("password").permitAll();

    // ログアウト処理
    http.logout().logoutRequestMatcher(new AntPathRequestMatcher(API_V1_LOGOUT_URL))
        // Cookieを破棄する
        .deleteCookies("SESSION", "JSESSIONID")
        // ログアウト画面のURL
        .logoutUrl(API_V1_LOGOUT_URL)
        // ログアウト後の遷移先
        .logoutSuccessUrl(API_V1_LOGOUT_SUCCESS_URL)
        // ajaxの場合は、HTTPステータスを返す
        .defaultLogoutSuccessHandlerFor(new HttpStatusReturningLogoutSuccessHandler(),
            RequestUtils::isAjaxRequest)
        // セッションを破棄する
        .invalidateHttpSession(true).permitAll();

  }

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new DefaultAccessDeniedHandler();
  }

  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return new DefaultAuthenticationEntryPoint(LOGIN_URL, LOGIN_TIMEOUT_URL);
  }
}