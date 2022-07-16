package com.isystk.sample.web.base.security;

import com.isystk.sample.web.base.util.RequestUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * ログイン画面を表示する際に、有効ではないセッションIDが渡ってきた場合は、 <br> タイムアウトした場合のURLにリダイレクトする。 <br>
 * ただし、AJAX通信の場合は、ステータスコードのみを返す。
 */
public class DefaultAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(
      DefaultAuthenticationEntryPoint.class);
  private final String loginTimeoutUrl;

  /**
   * @param loginUrl
   * @param loginTimeoutUrl
   */
  public DefaultAuthenticationEntryPoint(String loginUrl, String loginTimeoutUrl) {
    super(loginUrl);
    this.loginTimeoutUrl = loginTimeoutUrl;
  }

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {

    if (RequestUtils.isAjaxRequest(request)) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }

    super.commence(request, response, authException);
  }

  @Override
  protected String determineUrlToUseForThisRequest(HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException exception) {
    String url = super.determineUrlToUseForThisRequest(request, response, exception);

    if (request.getRequestedSessionId() != null && !request.isRequestedSessionIdValid()) {
      if (log.isDebugEnabled()) {
        log.debug("セッションがタイムアウトしました。");
      }

      return this.loginTimeoutUrl;
    }

    return url;
  }
}
