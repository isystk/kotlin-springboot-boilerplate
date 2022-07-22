package com.isystk.sample.web.admin.controller.html.contact.edit

import com.isystk.sample.web.base.controller.html.BaseForm
import org.springframework.format.annotation.NumberFormat
import java.math.BigInteger
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class ContactEditForm : BaseForm() {

    @NotNull
    var contactId: Long? = null

    @NotBlank
    var yourName: String? = null

    @NotBlank
    var email:String? = null

    @NotBlank
    @NumberFormat
    var gender: String? = null

    @NotBlank
    @NumberFormat
    var age: String? = null

    @NotBlank
    var title: String? = null

    @NotBlank
    var contact: String? = null

    @NotBlank
    var url: String? = null

    @NotBlank
    var contactImageName: String? = null

    @NotBlank
    var contactImageData: String? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}