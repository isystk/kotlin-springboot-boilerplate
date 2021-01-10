package com.isystk.sample.web.admin.controller.html.post

import com.isystk.sample.common.validator.AbstractValidator
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

/**
 * 投稿 入力チェック
 */
@Component
class PostFormValidator : AbstractValidator<PostSearchForm?>() {
    override fun doValidate(form: PostSearchForm?, errors: Errors?) {
    }
}