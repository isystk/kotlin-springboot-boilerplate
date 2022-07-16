package com.isystk.sample.web.admin.controller.html.user

import com.isystk.sample.common.validator.AbstractValidator
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

@Component
class UserListFormValidator : AbstractValidator<UserListForm?>() {
    override fun doValidate(form: UserListForm?, errors: Errors?) {}
}