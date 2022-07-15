package com.isystk.sample.web.front.controller.api.v1.stock;

import com.isystk.sample.common.dto.Pageable;
import com.isystk.sample.web.base.controller.html.BaseSearchForm;

public class StockForm extends BaseSearchForm implements Pageable {

  private static final long serialVersionUID = 7593564324192730932L;

  int perpage = 6;

  public int getPerpage() {
    return this.perpage;
  }

  public void setPerpage(int perpage) {
    this.perpage = perpage;
  }
}
