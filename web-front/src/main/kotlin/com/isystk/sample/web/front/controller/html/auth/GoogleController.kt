package com.isystk.sample.web.front.controller.html.auth

import com.isystk.sample.common.FrontUrl
import com.isystk.sample.common.helper.UserHelper
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(FrontUrl.AUTH_GOOGLE_CALLBACK)
class GoogleController : AbstractHtmlController() {
    @Autowired
    var userHelper: UserHelper? = null
    override fun getFunctionName(): String {
        return "F_AUTH_GOOGLE"
    }

    @GetMapping
    fun index(authentication: OAuth2AuthenticationToken?,
              @AuthenticationPrincipal oauth2User: OAuth2User?,
              model: Model?): String {
        return "redirect:/home"
    }

    companion object {
        private val log = LoggerFactory.getLogger(GoogleController::class.java)
    }
}