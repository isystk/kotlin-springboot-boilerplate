package com.isystk.sample.domain.entity

import com.isystk.sample.domain.dto.DomaDtoImpl
import org.seasar.doma.*
import java.math.BigInteger
import java.time.LocalDateTime

/**
 * お問い合わせ画像
 *
 * 自動生成のため原則修正禁止!!
 *
 */
@Entity
@Table(name = "contact_form_image")
open class ContactFormImage : DomaDtoImpl() {
    /** お問い合わせ画像ID  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: BigInteger? = null

    /** お問い合わせID  */
    @Column(name = "contact_form_id")
    var contactFormId: BigInteger? = null

    /** ファイル名  */
    @Column(name = "file_name")
    var fileName: String? = null

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