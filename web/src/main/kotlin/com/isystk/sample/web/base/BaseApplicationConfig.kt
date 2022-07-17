package com.isystk.sample.web.base

import com.isystk.sample.common.Const
import com.isystk.sample.common.dto.DefaultPageFactoryImpl
import com.isystk.sample.common.dto.PageFactory
import com.isystk.sample.web.base.aop.*
import com.isystk.sample.web.base.controller.api.resource.DefaultResourceFactoryImpl
import com.isystk.sample.web.base.controller.api.resource.ResourceFactory
import com.isystk.sample.web.base.filter.LoginUserTrackingFilter
import org.apache.catalina.connector.Connector
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.core.Ordered
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.util.*

abstract class BaseApplicationConfig : WebServerFactoryCustomizer<ConfigurableServletWebServerFactory?>, WebMvcConfigurer {
    override fun customize(container: ConfigurableServletWebServerFactory?) {}

    //    @Override
    //    public void addViewControllers(ViewControllerRegistry registry) {
    //        // コントローラーを追加する
    //        registry.addViewController(FORBIDDEN_URL).setViewName(FORBIDDEN_VIEW);
    //        registry.addViewController(ERROR_URL).setViewName(ERROR_VIEW);
    //        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    //    }
    //
    //    @Override
    //    public void addFormatters(FormatterRegistry registry) {
    //        registry.addConverter(new LocalDateConverter(LOCALDATE_FORMAT));
    //        registry.addConverter(new LocalDateTimeConverter(LOCALDATETIME_FORMAT));
    //    }
    //
    //    @Bean
    //    public ForwardedHeaderFilter forwardedHeaderFilter() {
    //        // X-Forwarded-XXXの値を使ってリクエスト情報を上書きする
    //        return new ForwardedHeaderFilter();
    //    }
    //
    //    @Bean
    //    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
    //        // hiddenパラメータで指定されたHTTPメソッドに変換する
    //        return new HiddenHttpMethodFilter();
    //    }
    //
    //    @Bean
    //    public ShallowEtagHeaderFilter shallowEtagHeaderFilter() {
    //        // ETagの制御を行う
    //        return new ShallowEtagHeaderFilter();
    //    }
    //
    //    @Bean
    //    public FilterRegistrationBean<CorsFilter> corsFilter() {
    //        val config = new CorsConfiguration();
    //        config.setAllowCredentials(allowCredentials);
    //        config.setAllowedHeaders(allowedHeaders);
    //        config.setAllowedOrigins(corsAllowedOrigins);
    //        config.setAllowedMethods(allowedMethods);
    //        config.setMaxAge(maxAge);
    //
    //        val source = new UrlBasedCorsConfigurationSource();
    //        source.registerCorsConfiguration("/**", config);
    //
    //        val bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
    //        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    //        return bean;
    //    }
    @Bean
    open fun loginUserTrackingFilterBean(): FilterRegistrationBean<LoginUserTrackingFilter> {
        val filter = LoginUserTrackingFilter()
        filter.excludeUrlPatterns = Arrays.asList(Const.WEBJARS_URL, Const.STATIC_RESOURCES_URL)
        val bean: FilterRegistrationBean<LoginUserTrackingFilter> = FilterRegistrationBean(filter)
        bean.order = Ordered.LOWEST_PRECEDENCE
        return bean
    }

    //    @Bean
    //    public FilterRegistrationBean<ClearMDCFilter> clearMDCFilterBean() {
    //        val filter = new ClearMDCFilter();
    //        val bean = new FilterRegistrationBean<ClearMDCFilter>(filter);
    //        bean.setOrder(Ordered.LOWEST_PRECEDENCE);
    //        return bean;
    //    }
    //
    //    @Bean
    //    public FilterRegistrationBean<CheckOverflowFilter> checkOverflowFilterBean() {
    //        val filter = new CheckOverflowFilter();
    //        val bean = new FilterRegistrationBean<CheckOverflowFilter>(filter);
    //        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
    //        return bean;
    //    }
    //    @Bean
    //    public LocaleResolver localeResolver() {
    //        // Cookieに言語を保存する
    //        val resolver = new CookieLocaleResolver();
    //        resolver.setCookieName("lang");
    //        return resolver;
    //    }
    //
    //    @Bean
    //    public LocaleChangeInterceptor localeChangeInterceptor() {
    //        // langパラメータでロケールを切り替える
    //        val interceptor = new LocaleChangeInterceptor();
    //        interceptor.setParamName("lang");
    //        return interceptor;
    //    }
    //    @Bean
    //    public LocalValidatorFactoryBean beanValidator(MessageSource messageSource) {
    //        val bean = new LocalValidatorFactoryBean();
    //        bean.setValidationMessageSource(messageSource);
    //        return bean;
    //    }
    //
    //    @Bean
    //    public LayoutDialect layoutDialect() {
    //        return new LayoutDialect();
    //    }
    //    @Bean
    //    public CacheManager cacheManager() {
    //        val manager = new EhCacheCacheManager();
    //        manager.setCacheManager(ehcache().getObject());
    //        return manager;
    //    }
    //
    //    @Bean
    //    public EhCacheManagerFactoryBean ehcache() {
    //        val ehcache = new EhCacheManagerFactoryBean();
    //        ehcache.setConfigLocation(new ClassPathResource("ehcache.xml"));
    //        return ehcache;
    //    }
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        // webjarsをResourceHandlerに登録する
        registry.addResourceHandler(Const.WEBJARS_URL) // JARの中身をリソースロケーションにする
                .addResourceLocations("classpath:/META-INF/resources/webjars/") // webjars-locatorを使うためにリソースチェイン内のキャッシュを無効化する
                .resourceChain(false)
    }

    @Bean
    open fun requestTrackingInterceptor(): RequestTrackingInterceptor {
        // MDCにIDを設定してリクエストをトラッキングする
        return RequestTrackingInterceptor()
    }

    @Bean
    open fun loggingFunctionNameInterceptor(): LoggingFunctionNameInterceptor {
        // MDCに機能名を設定してログに出力する
        return LoggingFunctionNameInterceptor()
    }

    @Bean
    open fun setAuditInfoInterceptor(): SetAuditInfoInterceptor {
        // システム制御項目を保存してDB保存時に利用する
        return SetAuditInfoInterceptor()
    }

    @Bean
    open fun setDoubleSubmitCheckTokenInterceptor(): SetDoubleSubmitCheckTokenInterceptor {
        // 二重送信をチェックする
        return SetDoubleSubmitCheckTokenInterceptor()
    }

    @Bean
    open fun setModelAndViewInterceptor(): SetModelAndViewInterceptor {
        // 共通的な定数定義などを画面変数に設定する
        return SetModelAndViewInterceptor()
    }

    //    @Bean
    //    public PermissionKeyResolver permissionKeyResolver() {
    //        // コントローラー・メソッド名から権限キーを解決する
    //        return new DefaultPermissionKeyResolver();
    //    }
    //
    //    @Bean
    //    public AuthorizationInterceptor authorizationInterceptor() {
    //        // ログインユーザーの操作を認可する
    //        return new AuthorizationInterceptor();
    //    }
    override fun addInterceptors(registry: InterceptorRegistry) {
//        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(requestTrackingInterceptor())
        registry.addInterceptor(loggingFunctionNameInterceptor())
        registry.addInterceptor(setAuditInfoInterceptor())
        registry.addInterceptor(setDoubleSubmitCheckTokenInterceptor())
        registry.addInterceptor(setModelAndViewInterceptor())
        //        registry.addInterceptor(authorizationInterceptor());
    }

    //
    //    @Bean
    //    public SnakeToLowerCamelCaseModelAttributeMethodProcessor attributeMethodProcessor() {
    //        // login_id パラメータを loginId にマッピングする
    //        return new SnakeToLowerCamelCaseModelAttributeMethodProcessor(true);
    //    }
    //
    //    @Override
    //    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    //        argumentResolvers.add(attributeMethodProcessor());
    //    }
    @Bean
    open fun pageFactory(): PageFactory {
        return DefaultPageFactoryImpl()
    }

    @Bean
    open fun resourceFactory(): ResourceFactory {
        return DefaultResourceFactoryImpl()
    }

    /**
     * [] を含むURLをGETしようとするとTomcatが400を返す 問題に対応
     *
     * @return
     */
    @Bean
    open fun containerCustomizer(): WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
        return EmbeddedTomcatCustomizer()
    }

    private class EmbeddedTomcatCustomizer : WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
        override fun customize(factory: TomcatServletWebServerFactory) {
            factory.addConnectorCustomizers(TomcatConnectorCustomizer { connector: Connector ->
                connector.setAttribute("relaxedPathChars", "<>[\\]^`{|}")
                connector.setAttribute("relaxedQueryChars", "<>[\\]^`{|}")
            })
        }
    }
}