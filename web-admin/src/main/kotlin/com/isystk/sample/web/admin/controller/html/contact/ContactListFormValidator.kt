package com.isystk.sample.web.admin.controller.html.contact

import com.isystk.sample.common.validator.AbstractValidator
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

@Component
class ContactListFormValidator : AbstractValidator<ContactListForm?>() {
    override fun doValidate(form: ContactListForm?, errors: Errors?) {}
}