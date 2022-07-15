package com.isystk.sample.domain.dto;

import com.isystk.sample.domain.entity.Stock;

public class StockRepositoryDto extends Stock {

  String stockImageName;

  String stockImageData;

  public String getStockImageName() {
    return this.stockImageName;
  }

  public String getStockImageData() {
    return this.stockImageData;
  }

  public void setStockImageName(String stockImageName) {
    this.stockImageName = stockImageName;
  }

  public void setStockImageData(String stockImageData) {
    this.stockImageData = stockImageData;
  }
}