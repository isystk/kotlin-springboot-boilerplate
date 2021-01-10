package com.isystk.sample.batch.controller.html.login

import java.io.Serializable
import javax.validation.constraints.NotEmpty

class LoginForm : Serializable {
    var loginId: @NotEmpty String? = null
    var password: @NotEmpty String? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}