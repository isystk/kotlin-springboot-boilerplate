package com.isystk.sample.web.front.controller.html.password.config

import java.io.Serializable
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class PasswordResetConfigForm : Serializable {
    /**
     * パスワード
     */
    var password: @NotBlank @Size(max = 100) String? = null

    /**
     * パスワード(確認用)
     */
    var passwordConfirmation: @NotBlank @Size(max = 100) String? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}