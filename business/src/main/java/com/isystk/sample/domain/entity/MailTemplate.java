package com.isystk.sample.domain.entity;

import com.isystk.sample.domain.dto.DomaDtoImpl;
import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.Version;

/**
 * メールテンプレート
 *
 * 自動生成のため原則修正禁止!!
 *
 */
@Entity
@Table(name = "mail_template")
public class MailTemplate extends DomaDtoImpl {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;


    /** テンプレートID */
    @Id
    @Column(name = "mail_template_id")
    Integer mailTemplateId;

    /** テンプレート区分 */
    @Column(name = "mail_template_div")
    Byte mailTemplateDiv;

    /** タイトル */
    @Column(name = "title")
    String title;

    /** 本文 */
    @Column(name = "text")
    String text;

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

    public Integer getMailTemplateId() {
        return this.mailTemplateId;
    }

    public Byte getMailTemplateDiv() {
        return this.mailTemplateDiv;
    }

    public String getTitle() {
        return this.title;
    }

    public String getText() {
        return this.text;
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

    public void setMailTemplateId(Integer mailTemplateId) {
        this.mailTemplateId = mailTemplateId;
    }

    public void setMailTemplateDiv(Byte mailTemplateDiv) {
        this.mailTemplateDiv = mailTemplateDiv;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
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