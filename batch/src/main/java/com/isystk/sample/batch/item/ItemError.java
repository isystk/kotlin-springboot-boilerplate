package com.isystk.sample.batch.item;

/**
 * 処理対象のエラーメッセージ
 */
public class ItemError {

  String sourceName;

  int position;

  String errorMessage;

  public String getSourceName() {
    return this.sourceName;
  }

  public int getPosition() {
    return this.position;
  }

  public String getErrorMessage() {
    return this.errorMessage;
  }

  public void setSourceName(String sourceName) {
    this.sourceName = sourceName;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
