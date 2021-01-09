package com.isystk.sample.web.front.controller.api.v1.member.post

import com.isystk.sample.web.base.controller.html.BaseForm
import javax.validation.Valid
import javax.validation.constraints.NotEmpty

class MemberPostRegistRestForm : BaseForm() {
    var title: @NotEmpty String? = null
    var text: @NotEmpty String? = null
    var imageList: @Valid MutableList<MemberPostDetailImageRestForm>? = null
    var tagList: @Valid MutableList<MemberPostDetailTagRestForm>? = null

    companion object {
        private const val serialVersionUID = 1L
    }
}