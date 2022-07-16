package com.isystk.sample.web.admin.controller.html.stock.regist

import com.isystk.sample.web.base.controller.html.BaseForm
import org.springframework.format.annotation.NumberFormat
import javax.validation.constraints.NotBlank

class StockRegistForm : BaseForm() {
    var name: @NotBlank String? = null
    var detail: @NotBlank String? = null

    @NumberFormat
    var price: @NotBlank String? = null

    @NumberFormat
    var quantity: @NotBlank String? = null
    var stockImageName: @NotBlank String? = null
    var stockImageData: @NotBlank String? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}