package com.isystk.sample.web.front.controller.api.v1.contact

import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.web.base.controller.html.BaseSearchForm
import org.springframework.format.annotation.NumberFormat
import javax.validation.constraints.NotBlank

class ContactForm : BaseSearchForm(), Pageable {

    @NotBlank
    var yourName: String? = null

    @NotBlank
    var email: String? = null

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

    var url: String? = null

    @NotBlank
    var contactImageName: String? = null

    @NotBlank
    var contactImageData: String? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }

}