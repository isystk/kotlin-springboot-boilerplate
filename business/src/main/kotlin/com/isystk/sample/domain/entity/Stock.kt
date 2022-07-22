package com.isystk.sample.domain.entity

import com.isystk.sample.domain.dto.DomaDtoImpl
import org.seasar.doma.*
import java.math.BigInteger
import java.time.LocalDateTime

/**
 * 商品
 *
 * 自動生成のため原則修正禁止!!
 *
 */
@Entity
@Table(name = "stock")
open class Stock : DomaDtoImpl() {
    /** 商品ID  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: BigInteger? = null

    /** 商品名  */
    @Column(name = "name")
    var name: String? = null

    /** 説明文  */
    @Column(name = "detail")
    var detail: String? = null

    /** 価格  */
    @Column(name = "price")
    var price: Int? = null

    /** 画像ファイル名  */
    @Column(name = "imgpath")
    var imgpath: String? = null

    /** 在庫数  */
    @Column(name = "quantity")
    var quantity: Int? = null

    /** 登録日時  */
    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null

    /** 更新日時  */
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null

    /** 削除フラグ  */
    @Column(name = "delete_flg")
    var deleteFlg: Boolean? = null

    /** 楽観チェック用バージョン  */
    @Version
    @Column(name = "version")
    var version: Long? = null

    companion object {
        /** serialVersionUID  */
        private const val serialVersionUID = 1L
    }
}