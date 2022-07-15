package com.isystk.sample.web.base.controller.api;

import static com.isystk.sample.common.Const.NO_DATA_FOUND_ERROR;
import static com.isystk.sample.common.Const.UNEXPECTED_ERROR;
import static com.isystk.sample.common.Const.VALIDATION_ERROR;

import com.isystk.sample.common.exception.ErrorMessagesException;
import com.isystk.sample.common.exception.NoDataFoundException;
import com.isystk.sample.common.exception.ValidationErrorException;
import com.isystk.sample.common.util.MessageUtils;
import com.isystk.sample.web.base.controller.api.resource.ErrorResourceImpl;
import com.isystk.sample.web.base.controller.api.resource.FieldErrorResource;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * API用の例外ハンドラー
 */
@RestControllerAdvice(annotations = RestController.class) // HTMLコントローラーの例外を除外する
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(ApiExceptionHandler.class);

  /**
   * 入力チェックエラーのハンドリング
   *
   * @param ex
   * @param request
   * @return
   */
  @ExceptionHandler(ValidationErrorException.class)
  public ResponseEntity<Object> handleValidationErrorException(Exception ex, WebRequest request) {
    HttpHeaders headers = new HttpHeaders();
    HttpStatus status = HttpStatus.BAD_REQUEST;
    List fieldErrorContexts = new ArrayList<FieldErrorResource>();

    if (ex instanceof ValidationErrorException) {
      ValidationErrorException vee = (ValidationErrorException) ex;

      vee.getErrors().ifPresent(errors -> {
        List<FieldError> fieldErrors = errors.getFieldErrors();

        if (fieldErrors != null) {
          fieldErrors.forEach(fieldError -> {
            FieldErrorResource fieldErrorResource = new FieldErrorResource();
            fieldErrorResource.setFieldName(fieldError.getField());
            fieldErrorResource.setErrorType(fieldError.getCode());
            fieldErrorResource.setErrorMessage(fieldError.getDefaultMessage());
            fieldErrorContexts.add(fieldErrorResource);
          });
        }
      });
    }

    Locale locale = request.getLocale();
    String message = MessageUtils.getMessage(VALIDATION_ERROR, null, "validation error", locale);
    ErrorResourceImpl errorContext = new ErrorResourceImpl();
    errorContext.setMessage(message);
    errorContext.setFieldErrors(fieldErrorContexts);

    return new ResponseEntity<>(errorContext, headers, status);
  }

  /**
   * 論理チェックエラーのハンドリング
   *
   * @param ex
   * @param request
   * @return
   */
  @ExceptionHandler(ErrorMessagesException.class)
  public ResponseEntity<Object> handleErrorMessagesException(Exception ex, WebRequest request) {
    HttpHeaders headers = new HttpHeaders();
    HttpStatus status = HttpStatus.BAD_REQUEST;
    List fieldErrorContexts = new ArrayList<FieldErrorResource>();

    ErrorResourceImpl errorContext = new ErrorResourceImpl();
    errorContext.setMessage(ex.getMessage());
    errorContext.setFieldErrors(fieldErrorContexts);

    return new ResponseEntity<>(errorContext, headers, status);
  }

  /**
   * データ不存在エラーのハンドリング
   *
   * @param ex
   * @param request
   * @return
   */
  @ExceptionHandler(NoDataFoundException.class)
  public ResponseEntity<Object> handleNoDataFoundException(Exception ex, WebRequest request) {
    HttpHeaders headers = new HttpHeaders();
    HttpStatus status = HttpStatus.OK;

    String parameterDump = this.dumpParameterMap(request.getParameterMap());
    log.info("no data found. dump: {}", parameterDump);

    String message = MessageUtils
        .getMessage(NO_DATA_FOUND_ERROR, null, "no data found", request.getLocale());
    ErrorResourceImpl errorResource = new ErrorResourceImpl();
    errorResource.setRequestId(String.valueOf(MDC.get("X-Track-Id")));
    errorResource.setMessage(message);
    errorResource.setFieldErrors(new ArrayList<>());

    return new ResponseEntity<>(errorResource, headers, status);
  }

  /**
   * 予期せぬ例外のハンドリング
   *
   * @param ex
   * @param request
   * @return
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleUnexpectedException(Exception ex, WebRequest request) {
    return handleExceptionInternal(ex, null, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
      HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    String parameterDump = this.dumpParameterMap(request.getParameterMap());
    log.error(String.format("unexpected error has occurred. dump: %s", parameterDump), ex);

    Locale locale = request.getLocale();
    String message = MessageUtils.getMessage(UNEXPECTED_ERROR, null, "unexpected error", locale);
    ErrorResourceImpl errorResource = new ErrorResourceImpl();
    errorResource.setRequestId(String.valueOf(MDC.get("X-Track-Id")));
    errorResource.setMessage(message);

    if (errorResource.getFieldErrors() == null) {
      errorResource.setFieldErrors(new ArrayList<>());
    }

    return new ResponseEntity<>(errorResource, headers, status);
  }

  /**
   * パラメータをダンプする。
   *
   * @param parameterMap
   * @return
   */
  protected String dumpParameterMap(Map<String, String[]> parameterMap) {
    StringBuilder sb = new StringBuilder(256);
    parameterMap.forEach((key, values) -> {
      sb.append(key).append("=").append("[");
      for (String value : values) {
        sb.append(value).append(",");
      }
      sb.delete(sb.length() - 1, sb.length()).append("], ");
    });
    int length = sb.length();
    if (2 <= length) {
      sb.delete(length - 2, length);
    }

    return sb.toString();
  }
}
