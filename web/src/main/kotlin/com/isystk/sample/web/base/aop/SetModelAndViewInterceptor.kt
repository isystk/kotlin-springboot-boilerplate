package com.isystk.sample.web.base.aop

import com.isystk.sample.common.Const
import com.isystk.sample.common.util.MessageUtils
import com.isystk.sample.web.base.filter.UserIdAware
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.BindingResult
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SetModelAndViewInterceptor : BaseHandlerInterceptor() {
    @Value("\${application.imageUploadLocation:#{systemProperties['java.io.tmpdir']}}")
    var imageUploadLocation: // 設定ファイルに定義されたアップロード先を取得する
            String? = null

    @Throws(Exception::class)
    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any,
                            modelAndView: ModelAndView?) {
        // コントローラーの動作後
        if (isRestController(handler)) {
            // APIの場合はスキップする
            return
        }
        if (modelAndView == null) {
            return
        }
        val locale = LocaleContextHolder.getLocale()
        val pulldownOption = MessageUtils.getMessage(Const.MAV_PULLDOWN_OPTION, locale)

        // ログインユーザーID
        var user: UserIdAware? = null
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication.principal is UserIdAware) {
            user = UserIdAware::class.java.cast(authentication.principal)
        }

        // 定数定義を画面に渡す
        val constants: MutableMap<String, Any> = HashMap()
        constants[Const.MAV_PULLDOWN_OPTION] = pulldownOption
        modelAndView.addObject(Const.MAV_CONST, constants)
        modelAndView.addObject(Const.MAV_LOGIN_USER, user)

        // 入力エラーを画面オブジェクトに設定する
        retainValidateErrors(modelAndView)
    }

    /**
     * 入力エラーを画面オブジェクトに設定する
     *
     * @param modelAndView
     */
    protected fun retainValidateErrors(modelAndView: ModelAndView) {
        val model = modelAndView.modelMap
        if (model != null && model.containsAttribute(Const.MAV_ERRORS)) {
            val errors = model[Const.MAV_ERRORS]
            if (errors != null && errors is BeanPropertyBindingResult) {
                val br = errors
                if (br.hasErrors()) {
                    val formName = br.objectName
                    val key = BindingResult.MODEL_KEY_PREFIX + formName
                    model.addAttribute(key, errors)
                    model.addAttribute(Const.GLOBAL_DANGER_MESSAGE, MessageUtils.getMessage(Const.VALIDATION_ERROR))
                }
            }
        }
    }

}