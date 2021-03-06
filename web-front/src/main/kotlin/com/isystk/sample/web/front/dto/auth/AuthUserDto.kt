package com.isystk.sample.web.front.dto.auth

import com.isystk.sample.common.dto.Dto

class AuthUserDto : Dto {
    /**
     * 会員ID
     */
    var userId: Int? = null

    /**
     * 姓
     */
    var familyName: String? = null

    /**
     * 名
     */
    var name: String? = null

    /**
     * セッションID
     */
    var sessionId: String? = null

}