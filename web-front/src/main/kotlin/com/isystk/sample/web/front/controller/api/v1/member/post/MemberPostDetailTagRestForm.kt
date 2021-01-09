package com.isystk.sample.web.front.controller.api.v1.member.post

import lombok.Getter
import lombok.Setter
import java.io.Serializable
import javax.validation.constraints.Digits

@Setter
@Getter
class MemberPostDetailTagRestForm : Serializable {
    var tagId: @Digits(integer = 9, fraction = 0) Int? = null
}