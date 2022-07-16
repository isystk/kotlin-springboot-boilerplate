package com.isystk.sample.web.admin.controller.html.photo

import com.isystk.sample.common.validator.AbstractValidator
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

@Component
class PhotoListFormValidator : AbstractValidator<PhotoListForm?>() {
    override fun doValidate(form: PhotoListForm?, errors: Errors?) {}
}