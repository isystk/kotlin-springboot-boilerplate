package com.isystk.sample.web.admin.controller.html.post.regist

import com.isystk.sample.common.validator.AbstractValidator
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

/**
 * 投稿 入力チェック
 */
@Component
class PostRigstFormValidator : AbstractValidator<PostRegistForm?>() {
    override fun doValidate(form: PostRegistForm?, errors: Errors?) {
    }
}