package com.isystk.sample.web.base.controller.html;

import java.io.Serializable;

public abstract class BaseForm implements Serializable {

  // 改定番号
  Integer version;

  public Integer getVersion() {
    return this.version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }
}
