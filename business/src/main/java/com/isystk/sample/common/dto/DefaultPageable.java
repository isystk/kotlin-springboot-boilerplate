package com.isystk.sample.common.dto;

public class DefaultPageable implements Pageable {

  int page = 1;

  int perpage = 10;

  public DefaultPageable(int page, int perpage) {
    this.page = page;
    this.perpage = perpage;
  }

  public int getPage() {
    return this.page;
  }

  public int getPerpage() {
    return this.perpage;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public void setPerpage(int perpage) {
    this.perpage = perpage;
  }
}
