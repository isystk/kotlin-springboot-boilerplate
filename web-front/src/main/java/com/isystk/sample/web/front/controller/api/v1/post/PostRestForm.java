package com.isystk.sample.web.front.controller.api.v1.post;

import java.io.Serializable;

public class PostRestForm implements Serializable {

  private static final long serialVersionUID = 7593564324192730932L;

  String title;

  String text;

  public String getTitle() {
    return this.title;
  }

  public String getText() {
    return this.text;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setText(String text) {
    this.text = text;
  }
}
