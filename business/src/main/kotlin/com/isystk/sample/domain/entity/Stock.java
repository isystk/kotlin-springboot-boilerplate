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
 * 商品
 *
 * 自動生成のため原則修正禁止!!
 *
 */
@Entity
@Table(name = "stock")
public class Stock extends DomaDtoImpl {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;


    /** 商品ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    BigInteger id;

    /** 商品名 */
    @Column(name = "name")
    String name;

    /** 説明文 */
    @Column(name = "detail")
    String detail;

    /** 価格 */
    @Column(name = "price")
    Integer price;

    /** 画像ファイル名 */
    @Column(name = "imgpath")
    String imgpath;

    /** 在庫数 */
    @Column(name = "quantity")
    Integer quantity;

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

    public String getName() {
        return this.name;
    }

    public String getDetail() {
        return this.detail;
    }

    public Integer getPrice() {
        return this.price;
    }

    public String getImgpath() {
        return this.imgpath;
    }

    public Integer getQuantity() {
        return this.quantity;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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