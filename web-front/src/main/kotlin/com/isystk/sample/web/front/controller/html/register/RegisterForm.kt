package com.isystk.sample.web.front.controller.html.register

import java.io.Serializable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class RegisterForm : Serializable {

    @NotEmpty
    var name: String? = null

    @NotEmpty
    @Email
    var email: String? = null

    @NotEmpty
    var password: String? = null

    @NotEmpty
    var passwordConfirmation: String? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}