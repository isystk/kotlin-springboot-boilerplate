package com.isystk.sample.batch.jobs.solrRegist;

import com.isystk.sample.batch.item.ItemPosition;
import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SolrRegistPostDto implements ItemPosition {

  @NotNull
  Integer postId;

  @NotNull
  Integer userId;

  @NotEmpty
  String title;

  @NotEmpty
  String text;

  @NotEmpty
  LocalDateTime registTime;

  // 取り込み元ファイル
  String sourceName;

  int position;

  public @NotNull Integer getPostId() {
    return this.postId;
  }

  public @NotNull Integer getUserId() {
    return this.userId;
  }

  public @NotEmpty String getTitle() {
    return this.title;
  }

  public @NotEmpty String getText() {
    return this.text;
  }

  public @NotEmpty LocalDateTime getRegistTime() {
    return this.registTime;
  }

  public String getSourceName() {
    return this.sourceName;
  }

  public int getPosition() {
    return this.position;
  }

  public void setPostId(@NotNull Integer postId) {
    this.postId = postId;
  }

  public void setUserId(@NotNull Integer userId) {
    this.userId = userId;
  }

  public void setTitle(@NotEmpty String title) {
    this.title = title;
  }

  public void setText(@NotEmpty String text) {
    this.text = text;
  }

  public void setRegistTime(@NotEmpty LocalDateTime registTime) {
    this.registTime = registTime;
  }

  public void setSourceName(String sourceName) {
    this.sourceName = sourceName;
  }

  public void setPosition(int position) {
    this.position = position;
  }
}
