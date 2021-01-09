package com.isystk.sample.web.front.controller.api.v1.post

import lombok.Getter
import lombok.Setter
import java.io.Serializable

@Setter
@Getter
class PostRestForm : Serializable {
    var title: String? = null
    var text: String? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}