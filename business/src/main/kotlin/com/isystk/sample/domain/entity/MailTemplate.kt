package com.isystk.sample.domain.entity

import com.isystk.sample.domain.dto.DomaDtoImpl
import org.seasar.doma.*
import java.time.LocalDateTime

/**
 * メールテンプレート
 *
 * 自動生成のため原則修正禁止!!
 *
 */
@Entity
@Table(name = "mail_template")
class MailTemplate : DomaDtoImpl() {
    /** テンプレートID  */
    @Id
    @Column(name = "mail_template_id")
    var mailTemplateId: Int? = null

    /** テンプレート区分  */
    @Column(name = "mail_template_div")
    var mailTemplateDiv: Byte? = null

    /** タイトル  */
    @Column(name = "title")
    var title: String? = null

    /** 本文  */
    @Column(name = "text")
    var text: String? = null

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