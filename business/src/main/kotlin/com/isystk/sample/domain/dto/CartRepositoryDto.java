package com.isystk.sample.domain.dto;

import com.isystk.sample.domain.entity.Cart;
import com.isystk.sample.domain.entity.Stock;

public class CartRepositoryDto extends Cart {

  Stock stock;

  public Stock getStock() {
    return this.stock;
  }

  public void setStock(Stock stock) {
    this.stock = stock;
  }
}