package com.isystk.sample.web.front.controller.api.v1.member.post

import com.isystk.sample.common.Const
import com.isystk.sample.common.FrontUrl
import com.isystk.sample.common.helper.UserHelper
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.domain.dto.TPostCriteria
import com.isystk.sample.web.base.controller.api.AbstractRestController
import com.isystk.sample.web.base.controller.api.resource.PageableResource
import com.isystk.sample.web.base.controller.api.resource.PageableResourceImpl
import com.isystk.sample.web.front.service.PostService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(FrontUrl.API_V1_MEMBER_POSTS)
class MemberPostRestController : AbstractRestController() {
    @Autowired
    var postService: PostService? = null

    @Autowired
    var userHelper: UserHelper? = null
    override fun getFunctionName(): String {
        return "API_MEMBER_POSTS"
    }

    /**
     * 会員表示
     *
     * @param query
     * @param model
     * @return
     */
    @GetMapping
    fun index(query: MemberPostRestForm?, model: Model?): PageableResource {
        // 入力値を詰め替える
        val criteria = TPostCriteria()
        criteria.userIdEq = userHelper!!.loginUserId
        criteria.isDeleteFlgFalse = true
        criteria.orderBy = "order by update_time desc"

        // 10件区切りで取得する
        val posts = postService!!.findAll(criteria, query)
        val resource: PageableResource = ObjectMapperUtils.map(posts, PageableResourceImpl::class.java)
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        return resource
    }

    companion object {
        private val log = LoggerFactory
                .getLogger(MemberPostRestController::class.java)
    }
}