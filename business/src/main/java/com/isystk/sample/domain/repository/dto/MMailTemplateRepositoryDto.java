package com.isystk.sample.domain.repository.dto;

import com.isystk.sample.common.values.MailTemplateDiv;
import com.isystk.sample.domain.entity.MMailTemplate;

public class MMailTemplateRepositoryDto extends MMailTemplate {

  MailTemplateDiv mailTemplateDiv;

  public MailTemplateDiv getMailTemplateDiv() {
    return this.mailTemplateDiv;
  }

  public void setMailTemplateDiv(MailTemplateDiv mailTemplateDiv) {
    this.mailTemplateDiv = mailTemplateDiv;
  }
}