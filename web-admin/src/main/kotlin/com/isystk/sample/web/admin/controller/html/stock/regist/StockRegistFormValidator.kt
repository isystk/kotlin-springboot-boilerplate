package com.isystk.sample.web.admin.controller.html.stock.regist

import com.isystk.sample.common.validator.AbstractValidator
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

@Component
class StockRegistFormValidator : AbstractValidator<StockRegistForm?>() {
    override fun doValidate(form: StockRegistForm?, errors: Errors?) {}
}