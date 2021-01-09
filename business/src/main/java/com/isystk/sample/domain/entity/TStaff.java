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
@Table(name = "t_staff")
public class TStaff extends DomaDtoImpl {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;


  /**
   * 管理者ID
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "STAFF_ID")
  Integer staffId;

  /**
   * メールアドレス
   */
  @Column(name = "EMAIL")
  String email;

  /**
   * パスワード
   */
  @Column(name = "PASSWORD")
  String password;

  /**
   * 姓
   */
  @Column(name = "FAMILY_NAME")
  String familyName;

  /**
   * 名
   */
  @Column(name = "NAME")
  String name;

  /**
   * 姓（カナ）
   */
  @Column(name = "FAMILY_NAME_KANA")
  String familyNameKana;

  /**
   * 名（カナ）
   */
  @Column(name = "NAME_KANA")
  String nameKana;

  /**
   * 最終ログイン日時
   */
  @Column(name = "LAST_LOGIN_TIME")
  LocalDateTime lastLoginTime;

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

  public Integer getStaffId() {
    return this.staffId;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  public String getFamilyName() {
    return this.familyName;
  }

  public String getName() {
    return this.name;
  }

  public String getFamilyNameKana() {
    return this.familyNameKana;
  }

  public String getNameKana() {
    return this.nameKana;
  }

  public LocalDateTime getLastLoginTime() {
    return this.lastLoginTime;
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

  public void setStaffId(Integer staffId) {
    this.staffId = staffId;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setFamilyNameKana(String familyNameKana) {
    this.familyNameKana = familyNameKana;
  }

  public void setNameKana(String nameKana) {
    this.nameKana = nameKana;
  }

  public void setLastLoginTime(LocalDateTime lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
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