package com.isystk.sample.web.front.controller.html.password

import java.io.Serializable
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class PasswordResetForm : Serializable {
    /**
     * メールアドレス
     */
    var email: @NotBlank @Email @Size(max = 100) String? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}