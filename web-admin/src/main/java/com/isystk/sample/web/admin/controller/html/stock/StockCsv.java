package com.isystk.sample.web.admin.controller.html.stock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true) // 定義されていないプロパティを無視してマッピングする
@JsonPropertyOrder({"ID", "商品名", "価格"}) // CSVのヘッダ順
public class StockCsv implements Serializable {

  private static final long serialVersionUID = -1883999589975469540L;

  @JsonProperty("ID")
  String id;

  @JsonProperty("商品名")
  String name;

  @JsonProperty("価格")
  String price;

  public String getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getPrice() {
    return this.price;
  }

  @JsonProperty("ID")
  public void setId(String id) {
    this.id = id;
  }

  @JsonProperty("商品名")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("価格")
  public void setPrice(String price) {
    this.price = price;
  }
}
