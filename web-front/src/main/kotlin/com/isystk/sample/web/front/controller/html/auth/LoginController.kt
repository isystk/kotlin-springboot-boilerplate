package com.isystk.sample.web.front.controller.html.auth

import com.isystk.sample.common.Const
import com.isystk.sample.common.helper.UserHelper
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

/**
 * ログイン
 */
@Controller
class LoginController : AbstractHtmlController() {
    @Autowired
    var userHelper: UserHelper? = null
    override fun getFunctionName(): String {
        return "F_LOGIN"
    }

    /**
     * ログイン成功
     *
     * @param attributes
     * @return
     */
    @PostMapping(Const.LOGIN_SUCCESS_URL)
    fun loginSuccess(attributes: RedirectAttributes): String {

        // 最終ログイン日時を更新します。
        userHelper!!.updateLastLogin()
        attributes.addFlashAttribute(Const.GLOBAL_SUCCESS_MESSAGE, getMessage("login.success"))
        return "redirect:/home"
    }

    /**
     * ログイン失敗
     *
     * @param model
     * @return
     */
    @GetMapping(Const.LOGIN_FAILURE_URL)
    fun loginFailure(model: Model): String {
        model.addAttribute(Const.GLOBAL_DANGER_MESSAGE, getMessage("login.failed"))
        return "modules/index"
    }

    /**
     * タイムアウトした時
     *
     * @param model
     * @return
     */
    @GetMapping(Const.LOGIN_TIMEOUT_URL)
    fun loginTimeout(model: Model): String {
        model.addAttribute(Const.GLOBAL_DANGER_MESSAGE, getMessage("login.timeout"))
        return "redirect:/login"
    }

    /**
     * ログアウト
     *
     * @return
     */
    @GetMapping(Const.LOGOUT_SUCCESS_URL)
    fun logoutSuccess(attributes: RedirectAttributes): String {
        attributes.addFlashAttribute(Const.GLOBAL_SUCCESS_MESSAGE, getMessage("logout.success"))
        return "redirect:/login"
    }

    companion object {
        private val log = LoggerFactory.getLogger(LoginController::class.java)
    }
}