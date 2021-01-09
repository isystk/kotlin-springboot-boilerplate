package com.isystk.sample.web.front.controller.api.v1.member.post

import com.isystk.sample.web.base.controller.html.BaseForm
import lombok.Getter
import lombok.Setter
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Setter
@Getter
class MemberPostEditRestForm : BaseForm() {
    var postId: @NotNull Int? = null
    var title: @NotEmpty String? = null
    var text: @NotEmpty String? = null
    var imageList: @Valid MutableList<MemberPostDetailImageRestForm>? = null
    var tagList: @Valid MutableList<MemberPostDetailTagRestForm>? = null

    companion object {
        private const val serialVersionUID = 1L
    }
}