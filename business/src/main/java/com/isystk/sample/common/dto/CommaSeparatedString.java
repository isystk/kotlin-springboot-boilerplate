package com.isystk.sample.common.dto;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;
import org.seasar.doma.Domain;

@Domain(valueType = String.class, factoryMethod = "of")
public class CommaSeparatedString implements Serializable {

  private static final long serialVersionUID = -6864852815920199569L;

  String data;

  public CommaSeparatedString() {
  }

  /**
   * ファクトリメソッド
   *
   * @param data
   * @return
   */
  public static CommaSeparatedString of(String data) {
    CommaSeparatedString css = new CommaSeparatedString();
    css.data = StringUtils.join(data, ",");
    return css;
  }

  // ResultSet.getBytes(int)で取得された値がこのコンストラクタで設定される
  CommaSeparatedString(String data) {
    this.data = StringUtils.join(data, ",");
  }

  // PreparedStatement.setBytes(int, bytes)へ設定する値がこのメソッドから取得される
  String getValue() {
    return this.data;
  }

  public String getData() {
    return this.data;
  }
}
