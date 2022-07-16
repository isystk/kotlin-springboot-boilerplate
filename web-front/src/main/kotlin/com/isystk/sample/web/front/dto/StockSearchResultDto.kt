package com.isystk.sample.web.front.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.isystk.sample.common.dto.Dto
import java.math.BigInteger
import java.time.LocalDateTime

/**
 * 商品フロント表示用DTO
 */
class StockSearchResultDto : Dto {
    @set:JsonProperty("id")
    @JsonProperty("id")
    var stockId: BigInteger? = null
    var name: String? = null
    var detail: String? = null
    var price: Int? = null
    var imgpath: String? = null

    @set:JsonProperty("img_url")
    @JsonProperty("img_url")
    var imgUrl: String? = null
    var quantity: Int? = null
    var createdAt: LocalDateTime? = null
    var createdAtYYYYMMDD: String? = null
    var createdAtMMDD: String? = null
}