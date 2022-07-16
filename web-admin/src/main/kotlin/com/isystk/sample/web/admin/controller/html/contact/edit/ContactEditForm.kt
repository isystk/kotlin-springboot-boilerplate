package com.isystk.sample.web.admin.controller.html.contact.edit

import com.isystk.sample.web.base.controller.html.BaseForm
import org.springframework.format.annotation.NumberFormat
import java.math.BigInteger
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class ContactEditForm : BaseForm() {
    var contactId: @NotNull BigInteger? = null
    var yourName: @NotBlank String? = null
    var email: @NotBlank String? = null

    @NumberFormat
    var gender: @NotBlank String? = null

    @NumberFormat
    var age: @NotBlank String? = null
    var title: @NotBlank String? = null
    var contact: @NotBlank String? = null
    var url: @NotBlank String? = null
    var contactImageName: @NotBlank String? = null
    var contactImageData: @NotBlank String? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}