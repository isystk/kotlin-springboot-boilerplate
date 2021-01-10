package com.isystk.sample.batch.controller.html.home

import com.isystk.sample.common.AdminUrl
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(AdminUrl.HOME)
class HomeController : AbstractHtmlController() {
    override fun getFunctionName(): String {
        return "A_HOME"
    }

    @GetMapping
    fun index(model: Model?): String {
        return "modules/home/index"
    }

    companion object {
        private val log = LoggerFactory.getLogger(HomeController::class.java)
    }
}