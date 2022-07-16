package com.isystk.sample.web.admin.controller.html.stock

import com.isystk.sample.common.validator.AbstractValidator
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

@Component
class StockListFormValidator : AbstractValidator<StockListForm?>() {
    override fun doValidate(form: StockListForm?, errors: Errors?) {}
}