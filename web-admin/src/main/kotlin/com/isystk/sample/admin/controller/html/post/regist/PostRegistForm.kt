package com.isystk.sample.batch.controller.html.post.regist

import com.isystk.sample.web.base.controller.html.BaseForm
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class PostRegistForm : BaseForm() {
    var userId: @NotNull Int? = null
    var title: @NotEmpty String? = null
    var text: @NotEmpty String? = null
    var postImageId: @NotEmpty MutableList<Int>? = null
    var postTagId: List<Int>? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}