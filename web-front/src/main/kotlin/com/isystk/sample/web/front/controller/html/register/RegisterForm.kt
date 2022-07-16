package com.isystk.sample.web.front.controller.html.register

import java.io.Serializable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class RegisterForm : Serializable {
    var name: @NotEmpty String? = null
    var email: @NotEmpty @Email String? = null
    var password: @NotEmpty String? = null
    var passwordConfirmation: @NotEmpty String? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}