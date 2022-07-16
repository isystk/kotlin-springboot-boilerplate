package com.isystk.sample.web.admin.controller.html.order

import com.isystk.sample.common.validator.AbstractValidator
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

@Component
class OrderListFormValidator : AbstractValidator<OrderListForm?>() {
    override fun doValidate(form: OrderListForm?, errors: Errors?) {}
}