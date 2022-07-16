package com.isystk.sample.domain.entity;

import com.isystk.sample.domain.dto.DomaDtoImpl;
import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.Version;

/**
 * 管理者
 *
 * 自動生成のため原則修正禁止!!
 *
 */
@Entity
@Table(name = "admin")
public class Admin extends DomaDtoImpl {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;


    /** 管理者ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /** 管理者名 */
    @Column(name = "name")
    String name;

    /** メールアドレス */
    @Column(name = "email")
    String email;

    /** パスワード */
    @Column(name = "password")
    String password;

    /** remember_token */
    @Column(name = "remember_token")
    String rememberToken;

    /** 最終ログイン日時 */
    @Column(name = "last_login_at")
    LocalDateTime lastLoginAt;

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

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRememberToken() {
        return this.rememberToken;
    }

    public LocalDateTime getLastLoginAt() {
        return this.lastLoginAt;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public void setLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
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