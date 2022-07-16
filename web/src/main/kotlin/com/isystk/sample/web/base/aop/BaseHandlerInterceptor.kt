package com.isystk.sample.web.base.aop

import com.isystk.sample.web.base.controller.api.AbstractRestController
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 基底インターセプター
 */
abstract class BaseHandlerInterceptor : HandlerInterceptorAdapter() {
    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        // コントローラーの動作前
        return true
    }

    @Throws(Exception::class)
    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any,
                            modelAndView: ModelAndView?) {
        // コントローラーの動作後
    }

    @Throws(Exception::class)
    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse,
                                 handler: Any, ex: Exception?) {
        // 処理完了後
    }

    /**
     * RestControllerであるかどうかを示す値を返します。
     *
     * @param handler
     * @return
     */
    protected fun isRestController(handler: Any?): Boolean {
        val bean = getBean(handler, AbstractRestController::class.java)
        return bean is AbstractRestController
    }

    /**
     * 引数のオブジェクトが指定のクラスであるかどうかを示すを返します。
     *
     * @param obj
     * @param clazz
     * @return
     */
    protected fun isInstanceOf(obj: Any, clazz: Class<*>): Boolean {
        return if (clazz.isAssignableFrom(obj.javaClass)) {
            true
        } else false
    }

    /**
     * HandlerのBeanを返します。
     *
     * @param handler
     * @return
     */
    protected fun <T> getBean(handler: Any?, clazz: Class<T>): T? {
        if (handler != null && handler is HandlerMethod) {
            val hm = handler.bean
            return if (clazz.isAssignableFrom(hm.javaClass)) {
                hm as T
            } else null
        }
        return null
    }
}