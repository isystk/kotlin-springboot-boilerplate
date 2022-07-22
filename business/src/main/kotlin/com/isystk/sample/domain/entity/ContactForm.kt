package com.isystk.sample.domain.entity

import com.isystk.sample.domain.dto.DomaDtoImpl
import org.seasar.doma.*
import java.math.BigInteger
import java.time.LocalDateTime

/**
 * お問い合わせ
 *
 * 自動生成のため原則修正禁止!!
 *
 */
@Entity
@Table(name = "contact_form")
open class ContactForm : DomaDtoImpl() {
    /** id  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: BigInteger? = null

    /** お名前  */
    @Column(name = "your_name")
    var yourName: String? = null

    /** タイトル  */
    @Column(name = "title")
    var title: String? = null

    /** メールアドレス  */
    @Column(name = "email")
    var email: String? = null

    /** URL  */
    @Column(name = "url")
    var url: String? = null

    /** 性別  */
    @Column(name = "gender")
    var gender: Byte? = null

    /** 年齢  */
    @Column(name = "age")
    var age: Byte? = null

    /** お問い合わせ内容  */
    @Column(name = "contact")
    var contact: String? = null

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