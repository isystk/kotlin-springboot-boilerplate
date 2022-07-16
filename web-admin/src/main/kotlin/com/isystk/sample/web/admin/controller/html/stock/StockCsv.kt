package com.isystk.sample.web.admin.controller.html.stock

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true) // 定義されていないプロパティを無視してマッピングする
@JsonPropertyOrder("ID", "商品名", "価格") // CSVのヘッダ順
class StockCsv : Serializable {
    @JsonProperty("ID")
    var id: String? = null

    @JsonProperty("商品名")
    var name: String? = null

    @JsonProperty("価格")
    var price: String? = null
}