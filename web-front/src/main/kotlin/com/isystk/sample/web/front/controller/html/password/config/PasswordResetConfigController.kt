package com.isystk.sample.web.front.controller.html.password.config

import com.isystk.sample.common.FrontUrl
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import com.isystk.sample.web.front.service.PasswordResetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

/**
 * 会員パスワード変更
 */
@Controller
@RequestMapping(path = [FrontUrl.PASSWORD_RESET])
class PasswordResetConfigController : AbstractHtmlController() {
    @Autowired
    var passwordResetService: PasswordResetService? = null

    @Autowired
    var passwordEncoder: PasswordEncoder? = null

    @Autowired
    var passwordResetConfigFormValidator: PasswordResetConfigFormValidator? = null
    @ModelAttribute("form")
    fun initForm(): PasswordResetConfigForm {
        return PasswordResetConfigForm()
    }

    @InitBinder("form")
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(passwordResetConfigFormValidator)
    }

    override fun getFunctionName(): String {
        return "F_PASSWORD_RESET_CONFIG"
    }

    /**
     * パスワード変更処理
     *
     * @return
     */
    @PostMapping(value = ["/{onetimeKey}"])
    fun changePassword(@PathVariable onetimeKey: String, @Validated @ModelAttribute("form") passwordResetConfigForm: PasswordResetConfigForm,
                       br: BindingResult, attributes: RedirectAttributes): String {

        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            setFlashAttributeErrors(attributes, br)
            return "modules/index"
        }

        // パスワードをハッシュ化する
        val password = passwordEncoder!!.encode(passwordResetConfigForm.password)

        // パスワード変更ワンタイムパス登録
        passwordResetService!!.changePassword(onetimeKey, password)
        return "redirect:/password/reset/complete"
    }

}