package com.isystk.sample.batch.controller.html.post.edit

import com.isystk.sample.common.helper.UserHelper
import com.isystk.sample.common.validator.AbstractValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

/**
 * 投稿 入力チェック
 */
@Component
class PostEditFormValidator : AbstractValidator<PostEditForm?>() {
    @Autowired
    var userHelper: UserHelper? = null
    override fun doValidate(form: PostEditForm?, errors: Errors?) {
    }
}