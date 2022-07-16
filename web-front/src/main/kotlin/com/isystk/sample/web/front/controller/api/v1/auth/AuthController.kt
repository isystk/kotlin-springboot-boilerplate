package com.isystk.sample.web.front.controller.api.v1.auth

import com.isystk.sample.common.FrontUrl
import com.isystk.sample.common.helper.UserHelper
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.domain.dao.AuditInfoHolder
import com.isystk.sample.web.base.controller.api.AbstractRestController
import com.isystk.sample.web.base.controller.api.resource.Resource
import com.isystk.sample.web.front.dto.auth.AuthUserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.HttpSession

@RestController
class AuthController : AbstractRestController() {
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
    @PostMapping(FrontUrl.API_V1_SESSION)
    fun session(session: HttpSession): Resource {
        val userId = AuditInfoHolder.getAuditUser()
        val resource = resourceFactory.create()
        if (Optional.of(userId).isEmpty || "GUEST" == userId) {
            val dto = AuthUserDto()
            dto.sessionId = session.id
            resource.data = Arrays.asList(dto)
            resource.message = "未ログイン状態です。"
            return resource
        }
        val user = userHelper!!.user
        val dto = ObjectMapperUtils.map(user, AuthUserDto::class.java)
        dto.sessionId = session.id
        resource.data = Arrays.asList(dto)
        resource.message = "ログイン状態です。"
        resource.result = true
        return resource
    } //
    //  /**
    //   * ログイン成功
    //   *
    //   * @return
    //   */
    //  @PostMapping(LOGIN_SUCCESS_URL)
    //  public Resource loginSuccess(HttpSession session) {
    //
    //    // 最終ログイン日時を更新します。
    //    userHelper.updateLastLogin();
    //
    //    Resource resource = resourceFactory.create();
    //    User user = userHelper.getUser();
    //    AuthUserDto dto = ObjectMapperUtils.map(user, AuthUserDto.class);
    //    dto.setSessionId(session.getId());
    //    resource.setData(Arrays.asList(dto));
    //    resource.setMessage(getMessage("login.success"));
    //    resource.setResult(true);
    //
    //    return resource;
    //  }
    //
    //  /**
    //   * ログイン失敗
    //   *
    //   * @return
    //   */
    //  @GetMapping(LOGIN_FAILURE_URL)
    //  @ResponseStatus(HttpStatus.UNAUTHORIZED)
    //  public Resource loginFailure(HttpServletResponse response) {
    //    Resource resource = resourceFactory.create();
    //    resource.setMessage(getMessage("login.failed"));
    //    resource.setResult(false);
    //    return resource;
    //  }
    //
    //  /**
    //   * ログアウト
    //   *
    //   * @return
    //   */
    //  @GetMapping(LOGOUT_SUCCESS_URL)
    //  public Resource logoutSuccess() {
    //    Resource resource = resourceFactory.create();
    //    resource.setMessage(getMessage("login.success"));
    //    resource.setResult(true);
    //    return resource;
    //  }
}