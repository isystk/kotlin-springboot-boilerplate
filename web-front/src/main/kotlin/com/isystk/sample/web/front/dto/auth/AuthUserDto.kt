package com.isystk.sample.web.front.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty
import com.isystk.sample.common.dto.Dto
import java.math.BigInteger
import java.time.LocalDateTime

class AuthUserDto : Dto {
    /** ユーザID  */
    var id: BigInteger? = null

    /** プロバイダID  */
    @set:JsonProperty("provider_id")
    @JsonProperty("provider_id")
    var providerId: String? = null

    /** プロバイダ名  */
    @set:JsonProperty("provider_name")
    @JsonProperty("provider_name")
    var providerName: String? = null

    /** ユーザ名  */
    var name: String? = null

    /** メールアドレス  */
    var email: String? = null

    /** メール検証日時  */
    @set:JsonProperty("email_verified_at")
    @JsonProperty("email_verified_at")
    var emailVerifiedAt: LocalDateTime? = null

    /** 登録日時  */
    @set:JsonProperty("created_at")
    @JsonProperty("created_at")
    var createdAt: LocalDateTime? = null

    /** 更新日時  */
    @set:JsonProperty("updated_at")
    @JsonProperty("updated_at")
    var updatedAt: LocalDateTime? = null

    /**
     * セッションID
     */
    @set:JsonProperty("session_id")
    @JsonProperty("session_id")
    var sessionId: String? = null
}