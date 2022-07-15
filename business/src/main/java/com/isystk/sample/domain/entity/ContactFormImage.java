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
 * お問い合わせ画像
 *
 * 自動生成のため原則修正禁止!!
 *
 */
@Entity
@Table(name = "contact_form_image")
public class ContactFormImage extends DomaDtoImpl {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;


    /** お問い合わせ画像ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    BigInteger id;

    /** お問い合わせID */
    @Column(name = "contact_form_id")
    BigInteger contactFormId;

    /** ファイル名 */
    @Column(name = "file_name")
    String fileName;

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

    public BigInteger getContactFormId() {
        return this.contactFormId;
    }

    public String getFileName() {
        return this.fileName;
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

    public void setContactFormId(BigInteger contactFormId) {
        this.contactFormId = contactFormId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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