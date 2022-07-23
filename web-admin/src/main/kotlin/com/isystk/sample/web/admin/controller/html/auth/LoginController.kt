package com.isystk.sample.web.admin.controller.html.auth

import com.isystk.sample.common.Const
import com.isystk.sample.common.helper.StaffHelper
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

/**
 * 管理側ログイン
 */
@Controller
class LoginController : AbstractHtmlController() {
    @Autowired
    var staffHelper: StaffHelper? = null
    override fun getFunctionName(): String {
        return "A_LOGIN"
    }

    /**
     * 初期表示
     *
     * @param form
     * @param model
     * @return
     */
    @GetMapping(Const.LOGIN_URL)
    fun index(@ModelAttribute form: LoginForm?, model: Model?): String {
        return "modules/login/login"
    }

    /**
     * ログイン成功
     *
     * @param form
     * @param attributes
     * @return
     */
    @PostMapping(Const.LOGIN_SUCCESS_URL)
    fun loginSuccess(@ModelAttribute form: LoginForm?, attributes: RedirectAttributes): String {

        // 最終ログイン日時を更新します。
        staffHelper!!.updateLastLogin()
        attributes.addFlashAttribute(Const.GLOBAL_SUCCESS_MESSAGE, getMessage("login.success"))
        return "redirect:/"
    }

    /**
     * ログイン失敗
     *
     * @param form
     * @param model
     * @return
     */
    @GetMapping(Const.LOGIN_FAILURE_URL)
    fun loginFailure(@ModelAttribute form: LoginForm?, model: Model): String {
        model.addAttribute(Const.GLOBAL_DANGER_MESSAGE, getMessage("login.failed"))
        return "modules/login/login"
    }

    /**
     * タイムアウトした時
     *
     * @param form
     * @param model
     * @return
     */
    @GetMapping(Const.LOGIN_TIMEOUT_URL)
    fun loginTimeout(@ModelAttribute form: LoginForm?, model: Model): String {
        model.addAttribute(Const.GLOBAL_DANGER_MESSAGE, getMessage("login.timeout"))
        return "modules/login/login"
    }

    /**
     * ログアウト
     *
     * @return
     */
    @GetMapping(Const.LOGOUT_SUCCESS_URL)
    fun logoutSuccess(@ModelAttribute form: LoginForm?, attributes: RedirectAttributes): String {
        attributes.addFlashAttribute(Const.GLOBAL_SUCCESS_MESSAGE, getMessage("logout.success"))
        return "redirect:/login"
    }

}