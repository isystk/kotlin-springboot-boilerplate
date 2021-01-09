package com.isystk.sample.web.front.controller.api.v1.member.post

import java.io.Serializable
import javax.validation.constraints.Digits

class MemberPostDetailTagRestForm : Serializable {
    var tagId: @Digits(integer = 9, fraction = 0) Int? = null

}