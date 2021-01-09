package com.isystk.sample.web.front.controller.api.v1.entry.remind

import java.io.Serializable
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class EntryRemindConfigRestForm : Serializable {
    /**
     * パスワード
     */
    var password: @NotBlank @Size(max = 100) String? = null

    /**
     * パスワード(確認用)
     */
    var passwordConf: @NotBlank @Size(max = 100) String? = null
    var onetimeKey: @NotBlank String? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}