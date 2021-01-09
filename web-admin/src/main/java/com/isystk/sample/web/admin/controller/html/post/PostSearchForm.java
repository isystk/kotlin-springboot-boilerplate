package com.isystk.sample.web.admin.controller.html.post;

import com.isystk.sample.common.dto.Pageable;
import com.isystk.sample.web.base.controller.html.BaseSearchForm;
import java.time.LocalDate;
import javax.validation.constraints.Digits;
import org.springframework.format.annotation.DateTimeFormat;

public class PostSearchForm extends BaseSearchForm implements Pageable {

  private static final long serialVersionUID = 7593564324192730932L;

  @Digits(integer = 9, fraction = 0)
  Integer postId;

  @Digits(integer = 9, fraction = 0)
  Integer userId;

  String title;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  LocalDate registDateFrom;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  LocalDate registDateTo;

  public @Digits(integer = 9, fraction = 0) Integer getPostId() {
    return this.postId;
  }

  public @Digits(integer = 9, fraction = 0) Integer getUserId() {
    return this.userId;
  }

  public String getTitle() {
    return this.title;
  }

  public LocalDate getRegistDateFrom() {
    return this.registDateFrom;
  }

  public LocalDate getRegistDateTo() {
    return this.registDateTo;
  }

  public void setPostId(@Digits(integer = 9, fraction = 0) Integer postId) {
    this.postId = postId;
  }

  public void setUserId(@Digits(integer = 9, fraction = 0) Integer userId) {
    this.userId = userId;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setRegistDateFrom(LocalDate registDateFrom) {
    this.registDateFrom = registDateFrom;
  }

  public void setRegistDateTo(LocalDate registDateTo) {
    this.registDateTo = registDateTo;
  }
}
