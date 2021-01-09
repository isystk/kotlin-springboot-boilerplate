package com.isystk.sample.web.admin.controller.html.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true) // 定義されていないプロパティを無視してマッピングする
@JsonPropertyOrder({"投稿ID", "ユーザーID", "タイトル"}) // CSVのヘッダ順
public class PostCsv implements Serializable {

  private static final long serialVersionUID = -1883999589975469540L;

  @JsonProperty("投稿ID")
  String postId;

  @JsonProperty("ユーザーID")
  String userId;

  @JsonProperty("タイトル")
  String title;

  public String getPostId() {
    return this.postId;
  }

  public String getUserId() {
    return this.userId;
  }

  public String getTitle() {
    return this.title;
  }

  public void setPostId(String postId) {
    this.postId = postId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
