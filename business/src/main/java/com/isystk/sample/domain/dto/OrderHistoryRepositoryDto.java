package com.isystk.sample.domain.dto;

import com.isystk.sample.domain.entity.OrderHistory;
import com.isystk.sample.domain.entity.Stock;
import com.isystk.sample.domain.entity.User;

public class OrderHistoryRepositoryDto extends OrderHistory {

  Stock stock;

  User user;

  public Stock getStock() {
    return this.stock;
  }

  public User getUser() {
    return this.user;
  }

  public void setStock(Stock stock) {
    this.stock = stock;
  }

  public void setUser(User user) {
    this.user = user;
  }
}