package com.isystk.sample.web.admin.controller.html.post

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true) // 定義されていないプロパティを無視してマッピングする
@JsonPropertyOrder("投稿ID", "ユーザーID", "タイトル") // CSVのヘッダ順
class PostCsv : Serializable {
    @JsonProperty("投稿ID")
    var postId: String? = null

    @JsonProperty("ユーザーID")
    var userId: String? = null

    @JsonProperty("タイトル")
    var title: String? = null

    companion object {
        private const val serialVersionUID = -1883999589975469540L
    }
}