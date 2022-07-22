package com.isystk.sample.web.admin.controller.html.stock.edit

import com.isystk.sample.web.base.controller.html.BaseForm
import org.springframework.format.annotation.NumberFormat
import java.math.BigInteger
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class StockEditForm : BaseForm() {

    @NotNull
    var stockId: Long? = null

    @NotBlank
    var name: String? = null

    @NotBlank
    var detail: String? = null

    @NotNull
    @NumberFormat
    var price: String? = null

    @NotNull
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