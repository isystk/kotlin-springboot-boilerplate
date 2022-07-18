package com.isystk.sample.web.admin.controller.html.stock.regist

import com.isystk.sample.web.base.controller.html.BaseForm
import org.springframework.format.annotation.NumberFormat
import javax.validation.constraints.NotBlank

class StockRegistForm : BaseForm() {

    @NotBlank
    var name: String? = null

    @NotBlank
    var detail: String? = null

    @NotBlank
    @NumberFormat
    var price: String? = null

    @NotBlank
    @NumberFormat
    var quantity: String? = null

    @NotBlank
    var stockImageName: String? = null

    @NotBlank
    var stockImageData: String? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}