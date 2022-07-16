package com.isystk.sample.web.front.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.isystk.sample.common.dto.Dto
import com.isystk.sample.domain.dto.CartRepositoryDto

/**
 * マイカート表示用DTO
 */
class CartSearchResultDto : Dto {
    @set:JsonProperty("data")
    @JsonProperty("data")
    var cartList: List<CartRepositoryDto>? = null
    var username: String? = null
    var count: Int? = null
    var sum: Int? = null
}