package com.isystk.sample.common.dto;

public class DefaultPageable implements Pageable {

  int page = 1;

  int perpage = 10;

  public DefaultPageable(int page, int perpage) {
    this.page = page;
    this.perpage = perpage;
  }

  public int page() {
    return this.page;
  }

  public int perpage() {
    return this.perpage;
  }

}
