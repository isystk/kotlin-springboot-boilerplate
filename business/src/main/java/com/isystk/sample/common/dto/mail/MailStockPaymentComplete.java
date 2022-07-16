package com.isystk.sample.common.dto.mail;

/**
 * 商品決算完了メール
 */
public class MailStockPaymentComplete {

  String userName;
  Integer amount;

  public String getUserName() {
    return this.userName;
  }

  public Integer getAmount() {
    return this.amount;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }
}
