package com.isystk.sample.web.base.controller.api

import com.isystk.sample.common.Const
import com.isystk.sample.common.logger as myLogger
import com.isystk.sample.common.exception.ErrorMessagesException
import com.isystk.sample.common.exception.NoDataFoundException
import com.isystk.sample.common.exception.ValidationErrorException
import com.isystk.sample.common.util.MessageUtils
import com.isystk.sample.web.base.controller.api.resource.ErrorResourceImpl
import com.isystk.sample.web.base.controller.api.resource.FieldErrorResource
import org.slf4j.MDC
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.function.Consumer

/**
 * API用の例外ハンドラー
 */
@RestControllerAdvice(annotations = [RestController::class]) // HTMLコントローラーの例外を除外する
class ApiExceptionHandler : ResponseEntityExceptionHandler() {
    /**
     * 入力チェックエラーのハンドリング
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ValidationErrorException::class)
    fun handleValidationErrorException(ex: Exception?, request: WebRequest): ResponseEntity<Any> {
        val headers = HttpHeaders()
        val status = HttpStatus.BAD_REQUEST
        val fieldErrorContexts: MutableList<FieldErrorResource> = ArrayList()
        if (ex is ValidationErrorException) {
            if (ex.errors !== null) {
                val fieldErrors = ex.errors!!.fieldErrors
                if (fieldErrors != null) {
                    fieldErrors.forEach(Consumer { fieldError: FieldError ->
                        val fieldErrorResource = FieldErrorResource()
                        fieldErrorResource.fieldName = fieldError.field
                        fieldErrorResource.errorType = fieldError.code
                        fieldErrorResource.errorMessage = fieldError.defaultMessage
                        fieldErrorContexts.add(fieldErrorResource)
                    })
                }
            }
        }
        val locale = request.locale
        val message = MessageUtils.getMessage(Const.VALIDATION_ERROR, null, "validation error", locale)
        val errorContext = ErrorResourceImpl()
        errorContext.message = message
        errorContext.fieldErrors = fieldErrorContexts
        return ResponseEntity(errorContext, headers, status)
    }

    /**
     * 論理チェックエラーのハンドリング
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ErrorMessagesException::class)
    fun handleErrorMessagesException(ex: Exception, request: WebRequest?): ResponseEntity<Any> {
        val headers = HttpHeaders()
        val status = HttpStatus.BAD_REQUEST
        val fieldErrorContexts: List<FieldErrorResource> = ArrayList()
        val errorContext = ErrorResourceImpl()
        errorContext.message = ex.message
        errorContext.fieldErrors = fieldErrorContexts
        return ResponseEntity(errorContext, headers, status)
    }

    /**
     * データ不存在エラーのハンドリング
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(NoDataFoundException::class)
    fun handleNoDataFoundException(ex: Exception?, request: WebRequest): ResponseEntity<Any> {
        val headers = HttpHeaders()
        val status = HttpStatus.OK
        val parameterDump = dumpParameterMap(request.parameterMap)
        myLogger.info("no data found. dump: {}", parameterDump)
        val message = MessageUtils
                .getMessage(Const.NO_DATA_FOUND_ERROR, null, "no data found", request.locale)
        val errorResource = ErrorResourceImpl()
        errorResource.requestId = MDC.get("X-Track-Id").toString()
        errorResource.message = message
        errorResource.fieldErrors = ArrayList()
        return ResponseEntity(errorResource, headers, status)
    }

    /**
     * 予期せぬ例外のハンドリング
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception::class)
    fun handleUnexpectedException(
            ex: MethodArgumentNotValidException,
            headers: HttpHeaders,
            status: HttpStatus,
            request: WebRequest
    ): ResponseEntity<Any> {
        val body = ex.bindingResult.allErrors.associateBy({ it.let { it as FieldError }.field }, { it.defaultMessage })
        return handleExceptionInternal(ex, body, headers, HttpStatus.INTERNAL_SERVER_ERROR, request)
    }

    override fun handleExceptionInternal(ex: Exception, body: Any?,
                                         headers: HttpHeaders,
                                         status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val parameterDump = dumpParameterMap(request.parameterMap)
        myLogger.error(String.format("unexpected error has occurred. dump: %s", parameterDump), ex)
        val locale = request.locale
        val message = MessageUtils.getMessage(Const.UNEXPECTED_ERROR, null, "unexpected error", locale)
        val errorResource = ErrorResourceImpl()
        errorResource.requestId = MDC.get("X-Track-Id").toString()
        errorResource.message = message
        if (errorResource.fieldErrors == null) {
            errorResource.fieldErrors = ArrayList()
        }
        return ResponseEntity(errorResource, headers, status)
    }

    /**
     * パラメータをダンプする。
     *
     * @param parameterMap
     * @return
     */
    protected fun dumpParameterMap(parameterMap: Map<String?, Array<String?>>): String {
        val sb = StringBuilder(256)
        parameterMap.forEach { (key: String?, values: Array<String?>) ->
            sb.append(key).append("=").append("[")
            for (value in values) {
                sb.append(value).append(",")
            }
            sb.delete(sb.length - 1, sb.length).append("], ")
        }
        val length = sb.length
        if (2 <= length) {
            sb.delete(length - 2, length)
        }
        return sb.toString()
    }

}