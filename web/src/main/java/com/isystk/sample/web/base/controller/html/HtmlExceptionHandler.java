package com.isystk.sample.web.base.controller.html;

import static com.isystk.sample.common.Const.DOUBLE_SUBMIT_ERROR;
import static com.isystk.sample.common.Const.ERROR_VIEW;
import static com.isystk.sample.common.Const.FORBIDDEN_VIEW;
import static com.isystk.sample.common.Const.GLOBAL_DANGER_MESSAGE;
import static com.isystk.sample.common.Const.NOTFOUND_VIEW;
import static com.isystk.sample.common.Const.OPTIMISTIC_LOCKING_FAILURE_ERROR;

import com.isystk.sample.common.exception.DoubleSubmitErrorException;
import com.isystk.sample.common.exception.FileNotFoundException;
import com.isystk.sample.common.exception.NoDataFoundException;
import com.isystk.sample.common.util.MessageUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 画面機能用の例外ハンドラー
 */
@ControllerAdvice(assignableTypes = {AbstractHtmlController.class}) // RestControllerでは動作させない
public class HtmlExceptionHandler {

  private static final String VIEW_ATTR_STACKTRACE = "trace";
  private static final Logger log = org.slf4j.LoggerFactory.getLogger(HtmlExceptionHandler.class);

  /**
   * ファイル、データ不存在時の例外をハンドリングする
   *
   * @param e
   * @param request
   * @param response
   * @return
   */
  @ExceptionHandler({FileNotFoundException.class, NoDataFoundException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public String handleNotFoundException(Exception e, HttpServletRequest request,
      HttpServletResponse response) {
    if (log.isDebugEnabled()) {
      log.debug("not found.", e);
    }

    String stackTrace = getStackTraceAsString(e);
    outputFlashMap(request, response, VIEW_ATTR_STACKTRACE, stackTrace);

    return NOTFOUND_VIEW;
  }

  /**
   * 権限不足エラーの例外をハンドリングする
   *
   * @param e
   * @param request
   * @param response
   * @return
   */
  @ExceptionHandler({AccessDeniedException.class})
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public String handleAccessDeniedException(Exception e, HttpServletRequest request,
      HttpServletResponse response) {
    if (log.isDebugEnabled()) {
      log.debug("forbidden.", e);
    }

    String stackTrace = getStackTraceAsString(e);
    outputFlashMap(request, response, VIEW_ATTR_STACKTRACE, stackTrace);

    return FORBIDDEN_VIEW;
  }

  /**
   * 楽観的排他制御により発生する例外をハンドリングする
   *
   * @param e
   * @param request
   * @param response
   * @return
   */
  @ExceptionHandler({OptimisticLockingFailureException.class})
  public RedirectView handleOptimisticLockingFailureException(Exception e,
      HttpServletRequest request,
      HttpServletResponse response) {
    if (log.isDebugEnabled()) {
      log.debug("optimistic locking failure.", e);
    }

    // 共通メッセージを取得する
    Locale locale = RequestContextUtils.getLocale(request);
    String messageCode = OPTIMISTIC_LOCKING_FAILURE_ERROR;
    RedirectView view = getRedirectView(request, response, locale, messageCode);

    return view;
  }

  /**
   * 二重送信防止チェックにより発生する例外をハンドリングする
   *
   * @param e
   * @param request
   * @param response
   * @return
   */
  @ExceptionHandler({DoubleSubmitErrorException.class})
  public RedirectView handleDoubleSubmitErrorException(Exception e, HttpServletRequest request,
      HttpServletResponse response) {
    if (log.isDebugEnabled()) {
      log.debug("double submit error.");
    }

    // 共通メッセージを取得する
    Locale locale = RequestContextUtils.getLocale(request);
    String messageCode = DOUBLE_SUBMIT_ERROR;
    RedirectView view = getRedirectView(request, response, locale, messageCode);

    return view;
  }

  /**
   * 予期せぬ例外をハンドリングする
   *
   * @param e
   * @param request
   * @param response
   * @return
   */
  @ExceptionHandler({Exception.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public String handleException(Exception e, HttpServletRequest request,
      HttpServletResponse response) {
    // TODO
    // ハンドルする例外がある場合は、条件分岐する
    log.error("unhandled runtime exception.", e);

    String stackTrace = getStackTraceAsString(e);
    outputFlashMap(request, response, VIEW_ATTR_STACKTRACE, stackTrace);

    return ERROR_VIEW;
  }

  /**
   * リダイレクト先でグローバルメッセージを表示する
   *
   * @param request
   * @param response
   * @param locale
   * @param messageCode
   * @return
   */
  protected RedirectView getRedirectView(HttpServletRequest request, HttpServletResponse response,
      Locale locale,
      String messageCode) {
    // メッセージを遷移先に表示する
    String message = MessageUtils.getMessage(messageCode, locale);
    outputFlashMap(request, response, GLOBAL_DANGER_MESSAGE, message);

    String requestURI = request.getRequestURI();
    RedirectView view = new RedirectView(requestURI);

    return view;
  }

  /**
   * スタックトレースを文字列で返します。
   *
   * @param e
   * @return
   */
  protected String getStackTraceAsString(Exception e) {
    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    e.printStackTrace(printWriter);
    return stringWriter.toString();
  }

  /**
   * FlashMapに値を書き出します。
   *
   * @param request
   * @param response
   * @param attr
   * @param value
   */
  protected void outputFlashMap(HttpServletRequest request, HttpServletResponse response,
      String attr, String value) {
    FlashMap flashMap = RequestContextUtils.getOutputFlashMap(request);
    flashMap.put(attr, value);

    // flashMapを書き込む
    FlashMapManager flashManager = RequestContextUtils.getFlashMapManager(request);
    flashManager.saveOutputFlashMap(flashMap, request, response);
  }
}
