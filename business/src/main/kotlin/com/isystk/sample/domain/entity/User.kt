package com.isystk.sample.domain.entity

import com.isystk.sample.domain.dto.DomaDtoImpl
import org.seasar.doma.*
import java.math.BigInteger
import java.time.LocalDateTime

/**
 * ユーザ
 *
 * 自動生成のため原則修正禁止!!
 *
 */
@Entity
@Table(name = "user")
open class User : DomaDtoImpl() {
    /** ユーザID  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: BigInteger? = null

    /** プロバイダID  */
    @Column(name = "provider_id")
    var providerId: String? = null

    /** プロバイダ名  */
    @Column(name = "provider_name")
    var providerName: String? = null

    /** ユーザ名  */
    @Column(name = "name")
    var name: String? = null

    /** メールアドレス  */
    @Column(name = "email")
    var email: String? = null

    /** メール検証日時  */
    @Column(name = "email_verified_at")
    var emailVerifiedAt: LocalDateTime? = null

    /** パスワード  */
    @Column(name = "password")
    var password: String? = null

    /** remember_token  */
    @Column(name = "remember_token")
    var rememberToken: String? = null

    /** 最終ログイン日時  */
    @Column(name = "last_login_at")
    var lastLoginAt: LocalDateTime? = null

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