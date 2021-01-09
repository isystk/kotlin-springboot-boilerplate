package com.isystk.sample.web.front.controller.api.v1.member.post;

import com.isystk.sample.web.base.controller.html.BaseForm;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MemberPostEditRestForm extends BaseForm {

  private static final long serialVersionUID = 1L;

  @NotNull
  Integer postId;

  @NotEmpty
  String title;

  @NotEmpty
  String text;

  @Valid
  List<MemberPostDetailImageRestForm> imageList;

  @Valid
  List<MemberPostDetailTagRestForm> tagList;

  public @NotNull Integer getPostId() {
    return this.postId;
  }

  public @NotEmpty String getTitle() {
    return this.title;
  }

  public @NotEmpty String getText() {
    return this.text;
  }

  public @Valid List<MemberPostDetailImageRestForm> getImageList() {
    return this.imageList;
  }

  public @Valid List<MemberPostDetailTagRestForm> getTagList() {
    return this.tagList;
  }

  public void setPostId(@NotNull Integer postId) {
    this.postId = postId;
  }

  public void setTitle(@NotEmpty String title) {
    this.title = title;
  }

  public void setText(@NotEmpty String text) {
    this.text = text;
  }

  public void setImageList(@Valid List<MemberPostDetailImageRestForm> imageList) {
    this.imageList = imageList;
  }

  public void setTagList(@Valid List<MemberPostDetailTagRestForm> tagList) {
    this.tagList = tagList;
  }
}
