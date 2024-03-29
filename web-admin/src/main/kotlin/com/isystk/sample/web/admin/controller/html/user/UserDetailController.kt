package com.isystk.sample.web.admin.controller.html.user

import com.isystk.sample.common.AdminUrl
import com.isystk.sample.web.admin.service.UserService
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
@Controller
@RequestMapping(AdminUrl.USERS)
class UserDetailController : AbstractHtmlController() {
    @Autowired
    var userService: UserService? = null
    override fun getFunctionName(): String {
        return "A_USER_DETAIL"
    }

    /**
     * 詳細画面表示
     *
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("{userId}")
    fun show(@PathVariable userId: Long, model: Model): String {
        val user = userService!!.findById(userId)
        model.addAttribute("user", user)
        return "modules/user/detail"
    }

}