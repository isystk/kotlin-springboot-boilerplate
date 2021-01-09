package com.isystk.sample.batch.jobs.importMst;

import com.isystk.sample.batch.item.ItemPosition;
import javax.validation.constraints.NotEmpty;

public class ImportMstPostDto implements ItemPosition {

  // タグID
  @NotEmpty
  String postTagId;

  // タグ名称
  @NotEmpty
  String name;

  // 取り込み元ファイル
  String sourceName;

  int position;

  public @NotEmpty String getPostTagId() {
    return this.postTagId;
  }

  public @NotEmpty String getName() {
    return this.name;
  }

  public String getSourceName() {
    return this.sourceName;
  }

  public int getPosition() {
    return this.position;
  }

  public void setPostTagId(@NotEmpty String postTagId) {
    this.postTagId = postTagId;
  }

  public void setName(@NotEmpty String name) {
    this.name = name;
  }

  public void setSourceName(String sourceName) {
    this.sourceName = sourceName;
  }

  public void setPosition(int position) {
    this.position = position;
  }
}
