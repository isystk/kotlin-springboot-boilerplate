package com.isystk.sample.domain.entity

import com.isystk.sample.domain.dto.DomaDtoImpl
import org.seasar.doma.*
import java.math.BigInteger
import java.time.LocalDateTime

/**
 * パスワードリセット
 *
 * 自動生成のため原則修正禁止!!
 *
 */
@Entity
@Table(name = "password_reset")
class PasswordReset : DomaDtoImpl() {
    /** id  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: BigInteger? = null

    /** メールアドレス  */
    @Column(name = "email")
    var email: String? = null

    /** ワンタイムトークン  */
    @Column(name = "token")
    var token: String? = null

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