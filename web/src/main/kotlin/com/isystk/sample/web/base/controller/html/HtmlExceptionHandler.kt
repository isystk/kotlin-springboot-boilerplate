package com.isystk.sample.web.base.controller.html

import com.isystk.sample.common.Const
import com.isystk.sample.common.exception.DoubleSubmitErrorException
import com.isystk.sample.common.exception.FileNotFoundException
import com.isystk.sample.common.exception.NoDataFoundException
import com.isystk.sample.common.util.MessageUtils
import org.slf4j.LoggerFactory
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.support.RequestContextUtils
import org.springframework.web.servlet.view.RedirectView
import java.io.PrintWriter
import java.io.StringWriter
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 画面機能用の例外ハンドラー
 */
@ControllerAdvice(assignableTypes = [AbstractHtmlController::class]) // RestControllerでは動作させない
class HtmlExceptionHandler {
    /**
     * ファイル、データ不存在時の例外をハンドリングする
     *
     * @param e
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(FileNotFoundException::class, NoDataFoundException::class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    fun handleNotFoundException(e: Exception, request: HttpServletRequest?,
                                response: HttpServletResponse?): String {
        if (log.isDebugEnabled) {
            log.debug("not found.", e)
        }
        val stackTrace = getStackTraceAsString(e)
        outputFlashMap(request, response, VIEW_ATTR_STACKTRACE, stackTrace)
        return Const.NOTFOUND_VIEW
    }

    /**
     * 権限不足エラーの例外をハンドリングする
     *
     * @param e
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(AccessDeniedException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun handleAccessDeniedException(e: Exception, request: HttpServletRequest?,
                                    response: HttpServletResponse?): String {
        if (log.isDebugEnabled) {
            log.debug("forbidden.", e)
        }
        val stackTrace = getStackTraceAsString(e)
        outputFlashMap(request, response, VIEW_ATTR_STACKTRACE, stackTrace)
        return Const.FORBIDDEN_VIEW
    }

    /**
     * 楽観的排他制御により発生する例外をハンドリングする
     *
     * @param e
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(OptimisticLockingFailureException::class)
    fun handleOptimisticLockingFailureException(e: Exception?,
                                                request: HttpServletRequest,
                                                response: HttpServletResponse?): RedirectView {
        if (log.isDebugEnabled) {
            log.debug("optimistic locking failure.", e)
        }

        // 共通メッセージを取得する
        val locale = RequestContextUtils.getLocale(request)
        val messageCode = Const.OPTIMISTIC_LOCKING_FAILURE_ERROR
        return getRedirectView(request, response, locale, messageCode)
    }

    /**
     * 二重送信防止チェックにより発生する例外をハンドリングする
     *
     * @param e
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(DoubleSubmitErrorException::class)
    fun handleDoubleSubmitErrorException(e: Exception?, request: HttpServletRequest,
                                         response: HttpServletResponse?): RedirectView {
        if (log.isDebugEnabled) {
            log.debug("double submit error.")
        }

        // 共通メッセージを取得する
        val locale = RequestContextUtils.getLocale(request)
        val messageCode = Const.DOUBLE_SUBMIT_ERROR
        return getRedirectView(request, response, locale, messageCode)
    }

    /**
     * 予期せぬ例外をハンドリングする
     *
     * @param e
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(e: Exception, request: HttpServletRequest?,
                        response: HttpServletResponse?): String {
        // TODO
        // ハンドルする例外がある場合は、条件分岐する
        log.error("unhandled runtime exception.", e)
        val stackTrace = getStackTraceAsString(e)
        outputFlashMap(request, response, VIEW_ATTR_STACKTRACE, stackTrace)
        return Const.ERROR_VIEW
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
    protected fun getRedirectView(request: HttpServletRequest, response: HttpServletResponse?,
                                  locale: Locale?,
                                  messageCode: String?): RedirectView {
        // メッセージを遷移先に表示する
        val message = MessageUtils.getMessage(messageCode, locale)
        outputFlashMap(request, response, Const.GLOBAL_DANGER_MESSAGE, message)
        val requestURI = request.requestURI
        return RedirectView(requestURI)
    }

    /**
     * スタックトレースを文字列で返します。
     *
     * @param e
     * @return
     */
    protected fun getStackTraceAsString(e: Exception): String {
        val stringWriter = StringWriter()
        val printWriter = PrintWriter(stringWriter)
        e.printStackTrace(printWriter)
        return stringWriter.toString()
    }

    /**
     * FlashMapに値を書き出します。
     *
     * @param request
     * @param response
     * @param attr
     * @param value
     */
    protected fun outputFlashMap(request: HttpServletRequest?, response: HttpServletResponse?,
                                 attr: String, value: String) {
        val flashMap = RequestContextUtils.getOutputFlashMap(request!!)
        flashMap[attr] = value

        // flashMapを書き込む
        val flashManager = RequestContextUtils.getFlashMapManager(request)
        flashManager!!.saveOutputFlashMap(flashMap, request, response!!)
    }

    companion object {
        private const val VIEW_ATTR_STACKTRACE = "trace"
        private val log = LoggerFactory.getLogger(HtmlExceptionHandler::class.java)
    }
}