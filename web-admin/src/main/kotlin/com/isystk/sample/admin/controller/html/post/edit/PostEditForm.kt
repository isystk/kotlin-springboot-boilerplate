package com.isystk.sample.batch.controller.html.post.edit

import com.isystk.sample.web.base.controller.html.BaseForm
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class PostEditForm : BaseForm() {
    var userId: @NotNull Int? = null
    var postId: @NotNull Int? = null
    var title: @NotBlank String? = null
    var text: @NotBlank String? = null
    var postImageId: @NotEmpty MutableList<Int>? = null
    var postTagId: List<Int>? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}