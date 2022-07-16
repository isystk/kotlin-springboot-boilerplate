package com.isystk.sample.web.admin.controller.html.stock.edit

import com.isystk.sample.web.base.controller.html.BaseForm
import org.springframework.format.annotation.NumberFormat
import java.math.BigInteger
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class StockEditForm : BaseForm() {
    var stockId: @NotNull BigInteger? = null
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