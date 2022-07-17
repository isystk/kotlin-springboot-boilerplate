package com.isystk.sample.common.dto

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Stripe用の支払情報DTO
 */
class StripePaymentDto : Dto {
    @set:JsonProperty("client_secret")
    @JsonProperty("client_secret")
    var clientSecret: String? = null
}