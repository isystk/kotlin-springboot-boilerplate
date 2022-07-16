package com.isystk.sample.web.admin.controller.html.contact.edit

import com.isystk.sample.common.validator.AbstractValidator
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

@Component
class ContactEditFormValidator : AbstractValidator<ContactEditForm?>() {
    override fun doValidate(form: ContactEditForm?, errors: Errors?) {}
}