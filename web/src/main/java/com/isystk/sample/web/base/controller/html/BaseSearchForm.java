package com.isystk.sample.web.base.controller.html;

public abstract class BaseSearchForm extends BaseForm {

  private static final long serialVersionUID = -7129975017789825804L;

  int page = 1;

  int perpage = 10;

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
