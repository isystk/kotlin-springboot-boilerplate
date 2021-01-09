package com.isystk.sample.domain.entity;

import com.isystk.sample.domain.dto.common.DomaDtoImpl;
import java.time.LocalDate;
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
@Table(name = "t_user")
public class TUser extends DomaDtoImpl {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;


  /**
   * 会員ID
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_ID")
  Integer userId;

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
   * 郵便番号
   */
  @Column(name = "ZIP")
  String zip;

  /**
   * 都道府県
   */
  @Column(name = "PREFECTURE_ID")
  Integer prefectureId;

  /**
   * 市区町村
   */
  @Column(name = "AREA")
  String area;

  /**
   * 町名番地
   */
  @Column(name = "ADDRESS")
  String address;

  /**
   * 建物名
   */
  @Column(name = "BUILDING")
  String building;

  /**
   * 電話番号
   */
  @Column(name = "TEL")
  String tel;

  /**
   * 性別
   */
  @Column(name = "SEX")
  Integer sex;

  /**
   * 生年月日
   */
  @Column(name = "BIRTHDAY")
  LocalDate birthday;

  /**
   * 最終ログイン日時
   */
  @Column(name = "LAST_LOGIN_TIME")
  LocalDateTime lastLoginTime;

  /**
   * ステータス
   */
  @Column(name = "STATUS")
  Integer status;

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

  public Integer getUserId() {
    return this.userId;
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

  public String getZip() {
    return this.zip;
  }

  public Integer getPrefectureId() {
    return this.prefectureId;
  }

  public String getArea() {
    return this.area;
  }

  public String getAddress() {
    return this.address;
  }

  public String getBuilding() {
    return this.building;
  }

  public String getTel() {
    return this.tel;
  }

  public Integer getSex() {
    return this.sex;
  }

  public LocalDate getBirthday() {
    return this.birthday;
  }

  public LocalDateTime getLastLoginTime() {
    return this.lastLoginTime;
  }

  public Integer getStatus() {
    return this.status;
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

  public void setUserId(Integer userId) {
    this.userId = userId;
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

  public void setZip(String zip) {
    this.zip = zip;
  }

  public void setPrefectureId(Integer prefectureId) {
    this.prefectureId = prefectureId;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setBuilding(String building) {
    this.building = building;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public void setLastLoginTime(LocalDateTime lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

  public void setStatus(Integer status) {
    this.status = status;
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