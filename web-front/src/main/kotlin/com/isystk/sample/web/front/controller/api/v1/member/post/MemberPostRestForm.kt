package com.isystk.sample.web.front.controller.api.v1.member.post

import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.web.base.controller.html.BaseSearchForm

class MemberPostRestForm : BaseSearchForm(), Pageable {
    var postId: Int? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}