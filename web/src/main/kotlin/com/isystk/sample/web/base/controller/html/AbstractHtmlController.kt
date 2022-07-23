package com.isystk.sample.web.base.controller.html

import com.isystk.sample.common.Const
import com.isystk.sample.common.FunctionNameAware
import com.isystk.sample.web.base.controller.BaseController
import org.springframework.ui.Model
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.BindingResult
import org.springframework.web.servlet.mvc.support.RedirectAttributes

/**
 * 基底HTMLコントローラー
 */
abstract class AbstractHtmlController : BaseController(), FunctionNameAware {
    /**
     * 入力チェックエラーがある場合はtrueを返します。
     *
     * @param model
     * @return
     */
    fun hasErrors(model: Model): Boolean {
        val errors = model.asMap()[Const.MAV_ERRORS]
        if (errors != null && errors is BeanPropertyBindingResult) {
            if (errors.hasErrors()) {
                return true
            }
        }
        return false
    }

    /**
     * リダイレクト先に入力エラーを渡します。
     *
     * @param attributes
     * @param result
     */
    fun setFlashAttributeErrors(attributes: RedirectAttributes, result: BindingResult?) {
        attributes.addFlashAttribute(Const.MAV_ERRORS, result)
    }

}