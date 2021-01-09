package com.isystk.sample.web.admin.controller.html.post.tag

import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.web.base.controller.html.BaseSearchForm

class PostTagSearchForm : BaseSearchForm(), Pageable {
    var postTagName: String? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}