package com.isystk.sample.web.base;

import static com.isystk.sample.common.Const.*;

import java.util.Arrays;

import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.isystk.sample.common.dto.DefaultPageFactoryImpl;
import com.isystk.sample.common.dto.PageFactory;
import com.isystk.sample.web.base.aop.LoggingFunctionNameInterceptor;
import com.isystk.sample.web.base.aop.RequestTrackingInterceptor;
import com.isystk.sample.web.base.aop.SetAuditInfoInterceptor;
import com.isystk.sample.web.base.aop.SetDoubleSubmitCheckTokenInterceptor;
import com.isystk.sample.web.base.aop.SetModelAndViewInterceptor;
import com.isystk.sample.web.base.controller.api.resource.DefaultResourceFactoryImpl;
import com.isystk.sample.web.base.controller.api.resource.ResourceFactory;
import com.isystk.sample.web.base.filter.LoginUserTrackingFilter;

import nz.net.ultraq.thymeleaf.LayoutDialect;

public abstract class BaseApplicationConfig
    implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>, WebMvcConfigurer {

  @Override
  public void customize(ConfigurableServletWebServerFactory container) {
  }

  @Bean
  public FilterRegistrationBean<LoginUserTrackingFilter> loginUserTrackingFilterBean() {
    LoginUserTrackingFilter filter = new LoginUserTrackingFilter();
    filter.setExcludeUrlPatterns(Arrays.asList(WEBJARS_URL, STATIC_RESOURCES_URL));

    FilterRegistrationBean bean = new FilterRegistrationBean<LoginUserTrackingFilter>(filter);
    bean.setOrder(Ordered.LOWEST_PRECEDENCE);
    return bean;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // webjarsをResourceHandlerに登録する
    registry.addResourceHandler(WEBJARS_URL)
        // JARの中身をリソースロケーションにする
        .addResourceLocations("classpath:/META-INF/resources/webjars/")
        // webjars-locatorを使うためにリソースチェイン内のキャッシュを無効化する
        .resourceChain(false);
  }

  @Bean
  public RequestTrackingInterceptor requestTrackingInterceptor() {
    // MDCにIDを設定してリクエストをトラッキングする
    return new RequestTrackingInterceptor();
  }

  @Bean
  public LoggingFunctionNameInterceptor loggingFunctionNameInterceptor() {
    // MDCに機能名を設定してログに出力する
    return new LoggingFunctionNameInterceptor();
  }

  @Bean
  public SetAuditInfoInterceptor setAuditInfoInterceptor() {
    // システム制御項目を保存してDB保存時に利用する
    return new SetAuditInfoInterceptor();
  }

  @Bean
  public SetDoubleSubmitCheckTokenInterceptor setDoubleSubmitCheckTokenInterceptor() {
    // 二重送信をチェックする
    return new SetDoubleSubmitCheckTokenInterceptor();
  }

  @Bean
  public SetModelAndViewInterceptor setModelAndViewInterceptor() {
    // 共通的な定数定義などを画面変数に設定する
    return new SetModelAndViewInterceptor();
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

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
    registry.addInterceptor(requestTrackingInterceptor());
    registry.addInterceptor(loggingFunctionNameInterceptor());
    registry.addInterceptor(setAuditInfoInterceptor());
    registry.addInterceptor(setDoubleSubmitCheckTokenInterceptor());
    registry.addInterceptor(setModelAndViewInterceptor());
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
  public PageFactory pageFactory() {
    return new DefaultPageFactoryImpl();
  }

  @Bean
  public ResourceFactory resourceFactory() {
    return new DefaultResourceFactoryImpl();
  }

  /**
   * [] を含むURLをGETしようとするとTomcatが400を返す 問題に対応
   *
   * @return
   */
  @Bean
  public WebServerFactoryCustomizer<TomcatServletWebServerFactory> containerCustomizer() {
    return new EmbeddedTomcatCustomizer();
  }

  private static class EmbeddedTomcatCustomizer implements
      WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
      factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
        connector.setAttribute("relaxedPathChars", "<>[\\]^`{|}");
        connector.setAttribute("relaxedQueryChars", "<>[\\]^`{|}");
      });
    }
  }
}