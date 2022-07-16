package com.isystk.sample.web.front.controller.api.v1.contact

import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.web.base.controller.html.BaseSearchForm
import org.springframework.format.annotation.NumberFormat
import javax.validation.constraints.NotBlank

class ContactForm : BaseSearchForm(), Pageable {
    var yourName: @NotBlank String? = null
    var email: @NotBlank String? = null

    @NumberFormat
    var gender: @NotBlank String? = null

    @NumberFormat
    var age: @NotBlank String? = null
    var title: @NotBlank String? = null
    var contact: @NotBlank String? = null
    var url: String? = null
    var contactImageName: @NotBlank String? = null
    var contactImageData: @NotBlank String? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}