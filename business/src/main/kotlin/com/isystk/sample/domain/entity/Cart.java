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
 * カート
 *
 * 自動生成のため原則修正禁止!!
 *
 */
@Entity
@Table(name = "cart")
public class Cart extends DomaDtoImpl {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;


    /** カートID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    BigInteger id;

    /** 商品ID */
    @Column(name = "stock_id")
    BigInteger stockId;

    /** ユーザID */
    @Column(name = "user_id")
    BigInteger userId;

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

    public BigInteger getStockId() {
        return this.stockId;
    }

    public BigInteger getUserId() {
        return this.userId;
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

    public void setStockId(BigInteger stockId) {
        this.stockId = stockId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
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