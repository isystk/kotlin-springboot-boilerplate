package com.isystk.sample.web.front.dto;

import com.isystk.sample.common.dto.Dto;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 投稿フロント表示用DTO
 */
public class FrontPostDto implements Dto {

  Integer postId;

  Integer userId;

  String title;

  String text;

  LocalDateTime registTime;

  String registTimeYYYYMMDD;

  String registTimeMMDD;

  List<FrontPostImageDto> imageList;

  List<FrontPostTagDto> tagList;

  public Integer getPostId() {
    return this.postId;
  }

  public Integer getUserId() {
    return this.userId;
  }

  public String getTitle() {
    return this.title;
  }

  public String getText() {
    return this.text;
  }

  public LocalDateTime getRegistTime() {
    return this.registTime;
  }

  public String getRegistTimeYYYYMMDD() {
    return this.registTimeYYYYMMDD;
  }

  public String getRegistTimeMMDD() {
    return this.registTimeMMDD;
  }

  public List<FrontPostImageDto> getImageList() {
    return this.imageList;
  }

  public List<FrontPostTagDto> getTagList() {
    return this.tagList;
  }

  public void setPostId(Integer postId) {
    this.postId = postId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setRegistTime(LocalDateTime registTime) {
    this.registTime = registTime;
  }

  public void setRegistTimeYYYYMMDD(String registTimeYYYYMMDD) {
    this.registTimeYYYYMMDD = registTimeYYYYMMDD;
  }

  public void setRegistTimeMMDD(String registTimeMMDD) {
    this.registTimeMMDD = registTimeMMDD;
  }

  public void setImageList(List<FrontPostImageDto> imageList) {
    this.imageList = imageList;
  }

  public void setTagList(List<FrontPostTagDto> tagList) {
    this.tagList = tagList;
  }
}
