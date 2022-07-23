package com.isystk.sample.web.front.controller.html.register

import com.isystk.sample.common.FrontUrl
import com.isystk.sample.common.exception.NoDataFoundException
import com.isystk.sample.common.helper.UserHelper
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.domain.entity.User
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import com.isystk.sample.web.front.service.RegisterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.util.*

/**
 * 会員登録
 */
@Controller
@RequestMapping(path = [FrontUrl.REGISTER])
class RegisterController : AbstractHtmlController() {
    @Autowired
    var registerService: RegisterService? = null

    @Autowired
    var passwordEncoder: PasswordEncoder? = null

    @Autowired
    var registerFormValidator: RegisterFormValidator? = null

    @Autowired
    var userHelper: UserHelper? = null
    @ModelAttribute("form")
    fun initForm(): RegisterForm {
        return RegisterForm()
    }

    @InitBinder("form")
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(registerFormValidator)
    }

    override fun getFunctionName(): String {
        return "F_REGISTER"
    }

    /**
     * 仮会員登録処理
     *
     * @param registerForm
     * @param br
     * @return
     */
    @PostMapping
    fun index(@Validated @ModelAttribute("form") registerForm: RegisterForm, br: BindingResult, attributes: RedirectAttributes): String {

        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            setFlashAttributeErrors(attributes, br)
            return "modules/index"
        }

        // 入力値からDTOを作成する
        val inputUser = ObjectMapperUtils.map(registerForm, User::class.java)
        val password = registerForm.password

        // パスワードをハッシュ化する
        inputUser.password = passwordEncoder!!.encode(password)

        // 仮会員登録
        registerService!!.registTemporary(inputUser)
        return "redirect:/register/sendMail"
    }

    /**
     * 仮会員登録メール再送信
     *
     * @return
     */
    @PostMapping("/resend")
    fun resend(): String {
        val userId = userHelper!!.loginUserId

        // 仮会員登録メール再送信
        registerService!!.sendMail(userId)
        return "redirect:/register/sendMail"
    }

    /**
     * 本会員登録処理
     *
     * @param onetimeKey
     * @return
     */
    @GetMapping("/valified/{onetimeKey}")
    fun valified(@PathVariable onetimeKey: String, model: Model?): String {

        // 本会員登録
        registerService!!.registComplete(onetimeKey)
        return "redirect:/register/complete"
    }

}