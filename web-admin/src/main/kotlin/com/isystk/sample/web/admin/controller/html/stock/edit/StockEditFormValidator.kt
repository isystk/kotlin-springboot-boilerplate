package com.isystk.sample.web.admin.controller.html.stock.edit

import com.isystk.sample.common.validator.AbstractValidator
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

@Component
class StockEditFormValidator : AbstractValidator<StockEditForm?>() {
    override fun doValidate(form: StockEditForm?, errors: Errors?) {}
}