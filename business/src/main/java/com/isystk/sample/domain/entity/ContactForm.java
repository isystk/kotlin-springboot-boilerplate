package com.isystk.sample.domain.entity;

import com.isystk.sample.domain.dto.DomaDtoImpl;
import java.math.BigInteger;
import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.Version;

/**
 * お問い合わせ
 *
 * 自動生成のため原則修正禁止!!
 *
 */
@Entity
@Table(name = "contact_form")
public class ContactForm extends DomaDtoImpl {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;


    /** id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    BigInteger id;

    /** お名前 */
    @Column(name = "your_name")
    String yourName;

    /** タイトル */
    @Column(name = "title")
    String title;

    /** メールアドレス */
    @Column(name = "email")
    String email;

    /** URL */
    @Column(name = "url")
    String url;

    /** 性別 */
    @Column(name = "gender")
    Byte gender;

    /** 年齢 */
    @Column(name = "age")
    Byte age;

    /** お問い合わせ内容 */
    @Column(name = "contact")
    String contact;

    /** 登録日時 */
    @Column(name = "created_at")
    LocalDateTime createdAt;

    /** 更新日時 */
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    /** 削除フラグ */
    @Column(name = "delete_flg")
    Boolean deleteFlg;

    /** 楽観チェック用バージョン */
    @Version
    @Column(name = "version")
    Long version;

    public BigInteger getId() {
        return this.id;
    }

    public String getYourName() {
        return this.yourName;
    }

    public String getTitle() {
        return this.title;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUrl() {
        return this.url;
    }

    public Byte getGender() {
        return this.gender;
    }

    public Byte getAge() {
        return this.age;
    }

    public String getContact() {
        return this.contact;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public Boolean getDeleteFlg() {
        return this.deleteFlg;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeleteFlg(Boolean deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}