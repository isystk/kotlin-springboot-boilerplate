package com.isystk.sample.web.front.controller.html.password

import com.isystk.sample.common.FrontUrl
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import com.isystk.sample.web.front.service.PasswordResetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

/**
 * パスワードリセット
 */
@Controller
@RequestMapping(path = [FrontUrl.PASSWORD_RESET])
class PasswordResetController : AbstractHtmlController() {
    @Autowired
    var passwordResetService: PasswordResetService? = null

    @Autowired
    var passwordResetFormValidator: PasswordResetFormValidator? = null
    @ModelAttribute("form")
    fun initForm(): PasswordResetForm {
        return PasswordResetForm()
    }

    @InitBinder("form")
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(passwordResetFormValidator)
    }

    override fun getFunctionName(): String {
        return "F_PASSWORD_RESET"
    }

    /**
     * パスワード変更メール送信処理
     *
     * @param passwordResetForm
     * @param br
     * @return
     */
    @PostMapping
    fun registOnetimePass(@Validated @ModelAttribute("form") passwordResetForm: PasswordResetForm,
                          br: BindingResult, attributes: RedirectAttributes): String {

        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            setFlashAttributeErrors(attributes, br)
            return "modules/index"
        }

        // パスワード変更ワンタイムパス登録
        passwordResetService!!.registOnetimePass(passwordResetForm.email)
        return "redirect:/password/reset/sendMail"
    }

}