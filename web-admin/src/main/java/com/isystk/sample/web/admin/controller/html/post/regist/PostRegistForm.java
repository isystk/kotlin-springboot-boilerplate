package com.isystk.sample.web.admin.controller.html.post.regist;

import com.isystk.sample.web.base.controller.html.BaseForm;
import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostRegistForm extends BaseForm {

  private static final long serialVersionUID = 7593564324192730932L;

  @NotNull
  Integer userId;

  @NotEmpty
  String title;

  @NotEmpty
  String text;

  @NotEmpty
  List<@Digits(integer = 9, fraction = 0) Integer> postImageId;

  List<@Digits(integer = 9, fraction = 0) Integer> postTagId;

  public @NotNull Integer getUserId() {
    return this.userId;
  }

  public @NotEmpty String getTitle() {
    return this.title;
  }

  public @NotEmpty String getText() {
    return this.text;
  }

  public @NotEmpty List<@Digits(integer = 9, fraction = 0) Integer> getPostImageId() {
    return this.postImageId;
  }

  public List<@Digits(integer = 9, fraction = 0) Integer> getPostTagId() {
    return this.postTagId;
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

  public void setPostImageId(
      @NotEmpty List<@Digits(integer = 9, fraction = 0) Integer> postImageId) {
    this.postImageId = postImageId;
  }

  public void setPostTagId(List<@Digits(integer = 9, fraction = 0) Integer> postTagId) {
    this.postTagId = postTagId;
  }
}
