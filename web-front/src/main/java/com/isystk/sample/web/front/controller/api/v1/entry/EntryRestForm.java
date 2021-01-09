package com.isystk.sample.web.front.controller.api.v1.entry;

import com.isystk.sample.common.validator.annotation.PhoneNumber;
import com.isystk.sample.common.validator.annotation.ZenKana;
import com.isystk.sample.common.validator.annotation.ZipCode;
import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

class EntryRestForm implements Serializable {

  private static final long serialVersionUID = 7593564324192730932L;

  /**
   * 姓
   */
  @NotBlank
  @Size(max = 100)
  String familyName;

  /**
   * 名
   */
  @NotBlank
  @Size(max = 100)
  String name;

  /**
   * 姓（カナ）
   */
  @NotBlank
  @Size(max = 100)
  @ZenKana
  String familyNameKana;

  /**
   * 名（カナ）
   */
  @NotBlank
  @Size(max = 100)
  @ZenKana
  String nameKana;

  /**
   * メールアドレス
   */
  @NotBlank
  @Email
  @Size(max = 100)
  String email;

  /**
   * パスワード
   */
  @NotBlank
  @Size(max = 100)
  String password;

  /**
   * パスワード(確認用)
   */
  @NotBlank
  @Size(max = 100)
  String passwordConf;

  /**
   * 性別
   */
  @NotNull
  @Digits(integer = 11, fraction = 0)
  Integer sex;

  /**
   * 郵便番号
   */
  @Size(max = 11)
  @ZipCode
  String zip;

  /**
   * 都道府県
   */
  @Digits(integer = 11, fraction = 0)
  String prefectureId;

  /**
   * 市区町村
   */
  @Size(max = 100)
  String area;

  /**
   * 町名番地
   */
  @Size(max = 100)
  String address;

  /**
   * 建物名
   */
  @Size(max = 100)
  String building;

  /**
   * 電話番号
   */
  @PhoneNumber
  String tel;

  /**
   * 生年月日
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  LocalDate birthday;

  public @NotBlank @Size(max = 100) String getFamilyName() {
    return this.familyName;
  }

  public @NotBlank @Size(max = 100) String getName() {
    return this.name;
  }

  public @NotBlank @Size(max = 100) String getFamilyNameKana() {
    return this.familyNameKana;
  }

  public @NotBlank @Size(max = 100) String getNameKana() {
    return this.nameKana;
  }

  public @NotBlank @Email @Size(max = 100) String getEmail() {
    return this.email;
  }

  public @NotBlank @Size(max = 100) String getPassword() {
    return this.password;
  }

  public @NotBlank @Size(max = 100) String getPasswordConf() {
    return this.passwordConf;
  }

  public @NotNull @Digits(integer = 11, fraction = 0) Integer getSex() {
    return this.sex;
  }

  public @Size(max = 11) String getZip() {
    return this.zip;
  }

  public @Digits(integer = 11, fraction = 0) String getPrefectureId() {
    return this.prefectureId;
  }

  public @Size(max = 100) String getArea() {
    return this.area;
  }

  public @Size(max = 100) String getAddress() {
    return this.address;
  }

  public @Size(max = 100) String getBuilding() {
    return this.building;
  }

  public String getTel() {
    return this.tel;
  }

  public LocalDate getBirthday() {
    return this.birthday;
  }

  public void setFamilyName(@NotBlank @Size(max = 100) String familyName) {
    this.familyName = familyName;
  }

  public void setName(@NotBlank @Size(max = 100) String name) {
    this.name = name;
  }

  public void setFamilyNameKana(@NotBlank @Size(max = 100) String familyNameKana) {
    this.familyNameKana = familyNameKana;
  }

  public void setNameKana(@NotBlank @Size(max = 100) String nameKana) {
    this.nameKana = nameKana;
  }

  public void setEmail(@NotBlank @Email @Size(max = 100) String email) {
    this.email = email;
  }

  public void setPassword(@NotBlank @Size(max = 100) String password) {
    this.password = password;
  }

  public void setPasswordConf(@NotBlank @Size(max = 100) String passwordConf) {
    this.passwordConf = passwordConf;
  }

  public void setSex(@NotNull @Digits(integer = 11, fraction = 0) Integer sex) {
    this.sex = sex;
  }

  public void setZip(@Size(max = 11) String zip) {
    this.zip = zip;
  }

  public void setPrefectureId(@Digits(integer = 11, fraction = 0) String prefectureId) {
    this.prefectureId = prefectureId;
  }

  public void setArea(@Size(max = 100) String area) {
    this.area = area;
  }

  public void setAddress(@Size(max = 100) String address) {
    this.address = address;
  }

  public void setBuilding(@Size(max = 100) String building) {
    this.building = building;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }
}
