package com.isystk.sample.web.front.controller.html

import com.isystk.sample.common.FrontUrl
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(FrontUrl.TOP)
class IndexController : AbstractHtmlController() {
    override fun getFunctionName(): String {
        return "F_TOP"
    }

    @GetMapping(value = ["/", "{path:(?!^static|oauth|swagger-ui$).*}/**"])
    fun index(model: Model?): String {
        return "modules/index"
    }

}