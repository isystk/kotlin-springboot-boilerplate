package com.isystk.sample.domain.entity;

import com.isystk.sample.domain.dto.common.DomaDtoImpl;
import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.Version;

/**
 * 自動生成のため原則修正禁止!!
 */
@Entity
@Table(name = "m_mail_template")
public class MMailTemplate extends DomaDtoImpl {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;


  /**
   * MAIL_TEMPLATEID
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MAIL_TEMPLATE_ID")
  Integer mailTemplateId;

  /**
   * テンプレート区分
   */
  @Column(name = "TEMPLATE_DIV")
  Integer templateDiv;

  /**
   * タイトル
   */
  @Column(name = "TITLE")
  String title;

  /**
   * 本文
   */
  @Column(name = "TEXT")
  String text;

  /**
   * 登録日時
   */
  @Column(name = "REGIST_TIME")
  LocalDateTime registTime;

  /**
   * 更新日時
   */
  @Column(name = "UPDATE_TIME")
  LocalDateTime updateTime;

  /**
   * 削除フラグ
   */
  @Column(name = "DELETE_FLG")
  Boolean deleteFlg;

  /**
   * 楽観チェック用バージョン
   */
  @Version
  @Column(name = "VERSION")
  Long version;

  public Integer getMailTemplateId() {
    return this.mailTemplateId;
  }

  public Integer getTemplateDiv() {
    return this.templateDiv;
  }

  public String getTitle() {
    return this.title;
  }

  public String getText() {
    return this.text;
  }

  public LocalDateTime getRegistTime() {
    return this.registTime;
  }

  public LocalDateTime getUpdateTime() {
    return this.updateTime;
  }

  public Boolean getDeleteFlg() {
    return this.deleteFlg;
  }

  public Long getVersion() {
    return this.version;
  }

  public void setMailTemplateId(Integer mailTemplateId) {
    this.mailTemplateId = mailTemplateId;
  }

  public void setTemplateDiv(Integer templateDiv) {
    this.templateDiv = templateDiv;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setRegistTime(LocalDateTime registTime) {
    this.registTime = registTime;
  }

  public void setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
  }

  public void setDeleteFlg(Boolean deleteFlg) {
    this.deleteFlg = deleteFlg;
  }

  public void setVersion(Long version) {
    this.version = version;
  }
}