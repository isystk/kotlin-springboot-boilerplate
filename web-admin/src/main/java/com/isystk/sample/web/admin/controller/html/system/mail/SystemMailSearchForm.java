package com.isystk.sample.web.admin.controller.html.system.mail;

import com.isystk.sample.common.dto.Pageable;
import com.isystk.sample.web.base.controller.html.BaseSearchForm;
import javax.validation.constraints.Digits;

public class SystemMailSearchForm extends BaseSearchForm implements Pageable {

  private static final long serialVersionUID = 7593564324192730932L;

  @Digits(integer = 9, fraction = 0)
  Integer templateDiv;

  public @Digits(integer = 9, fraction = 0) Integer getTemplateDiv() {
    return this.templateDiv;
  }

  public void setTemplateDiv(@Digits(integer = 9, fraction = 0) Integer templateDiv) {
    this.templateDiv = templateDiv;
  }
}
