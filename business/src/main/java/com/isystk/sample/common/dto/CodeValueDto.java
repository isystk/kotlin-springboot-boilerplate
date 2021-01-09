package com.isystk.sample.common.dto;

/**
 * 名称とコードのDTO
 */
public class CodeValueDto implements Dto {

  Integer code;

  String text;

  public Integer getCode() {
    return this.code;
  }

  public String getText() {
    return this.text;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public void setText(String text) {
    this.text = text;
  }
}
