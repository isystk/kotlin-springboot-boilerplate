package com.isystk.sample.web.admin.controller.html.auth

import java.io.Serializable
import javax.validation.constraints.NotEmpty

class LoginForm : Serializable {

    @NotEmpty
    var loginId: String? = null

    @NotEmpty
    var password: String? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}