package com.isystk.sample.common.dto;

/**
 * 名称とコードのDTO
 */
public class CodeValueDto implements Dto {

  String code;

  String text;

  public String getCode() {
    return this.code;
  }

  public String getText() {
    return this.text;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setText(String text) {
    this.text = text;
  }
}
