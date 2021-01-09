package com.isystk.sample.web.front.controller.api.v1.auth

import com.isystk.sample.common.FrontUrl
import com.isystk.sample.common.helper.UserHelper
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.domain.dao.AuditInfoHolder
import com.isystk.sample.web.base.controller.api.AbstractRestController
import com.isystk.sample.web.base.controller.api.resource.Resource
import com.isystk.sample.web.front.dto.auth.AuthUserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

@RestController
class AuthRestController : AbstractRestController() {
    override fun getFunctionName(): String {
        return "API_AUTH"
    }

    @Autowired
    var userHelper: UserHelper? = null

    /**
     * ログインチェック
     *
     * @return
     */
    @PostMapping(FrontUrl.API_V1_LOGIN_CHECK_URL)
    fun loginCheck(session: HttpSession): Resource? {
        val userId = AuditInfoHolder.getAuditUser()
        val resource = resourceFactory.create()
        if (Optional.of(userId).isEmpty) {
            // 未ログイン状態です
            return null
        }
        val tUser = userHelper!!.user
        val dto = ObjectMapperUtils.map(tUser, AuthUserDto::class.java)
        dto.sessionId = session.id
        resource.data = Arrays.asList(dto)
        resource.message = "ログイン状態です。"
        return resource
    }

    /**
     * ログイン成功
     *
     * @return
     */
    @PostMapping(FrontUrl.API_V1_LOGIN_SUCCESS_URL)
    fun loginSuccess(session: HttpSession): Resource {

        // 最終ログイン日時を更新します。
        userHelper!!.updateLastLogin()
        val resource = resourceFactory.create()
        val tUser = userHelper!!.user
        val dto = ObjectMapperUtils.map(tUser, AuthUserDto::class.java)
        dto.sessionId = session.id
        resource.data = Arrays.asList(dto)
        resource.message = getMessage("login.success")
        return resource
    }

    /**
     * ログイン失敗
     *
     * @return
     */
    @GetMapping(FrontUrl.API_V1_LOGIN_FAILURE_URL)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun loginFailure(response: HttpServletResponse?): Resource {
        val resource = resourceFactory.create()
        resource.message = getMessage("login.failed")
        return resource
    }

    /**
     * ログアウト
     *
     * @return
     */
    @GetMapping(FrontUrl.API_V1_LOGOUT_SUCCESS_URL)
    fun logoutSuccess(): Resource {
        val resource = resourceFactory.create()
        resource.message = getMessage("login.success")
        return resource
    }
}