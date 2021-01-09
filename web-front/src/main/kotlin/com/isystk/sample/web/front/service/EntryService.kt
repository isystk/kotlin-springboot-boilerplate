package com.isystk.sample.web.front.service

import com.isystk.sample.common.dto.mail.EntryRegistTemporary
import com.isystk.sample.common.exception.NoDataFoundException
import com.isystk.sample.common.helper.SendMailHelper
import com.isystk.sample.common.service.BaseTransactionalService
import com.isystk.sample.common.util.DateUtils
import com.isystk.sample.common.values.MailTemplate
import com.isystk.sample.common.values.UserStatus
import com.isystk.sample.domain.dao.MMailTemplateDao
import com.isystk.sample.domain.dao.TUserDao
import com.isystk.sample.domain.dao.TUserOnetimeValidDao
import com.isystk.sample.domain.dto.MMailTemplateCriteria
import com.isystk.sample.domain.dto.TUserOnetimeValidCriteria
import com.isystk.sample.domain.entity.MMailTemplate
import com.isystk.sample.domain.entity.TUser
import com.isystk.sample.domain.entity.TUserOnetimeValid
import com.isystk.sample.domain.repository.TUserRepository
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class EntryService : BaseTransactionalService() {
    @Value("\${spring.mail.properties.mail.from}")
    var fromAddress: String? = null

    @Value("\${server.address}")
    var domain: String? = null

    @Autowired
    var tUserDao: TUserDao? = null

    @Autowired
    var tUserRepository: TUserRepository? = null

    @Autowired
    var tUserOnetimeValidDao: TUserOnetimeValidDao? = null

    @Autowired
    var mMailTemplateDao: MMailTemplateDao? = null

    @Autowired
    var sendMailHelper: SendMailHelper? = null

    /**
     * 仮会員登録
     *
     * @param user
     */
    fun registTemporary(tUser: TUser) {

        // DB登録する
        tUser.status = UserStatus.TEMPORARY.code
        tUserRepository!!.create(tUser)

        // 会員-初期承認を登録する
        val tUserOnetimeValid = TUserOnetimeValid()
        tUserOnetimeValid.userId = tUser.userId
        val onetimeKey = generateOnetimeKey()
        tUserOnetimeValid.onetimeKey = onetimeKey
        // 7時間の制限時間を設ける
        tUserOnetimeValid.onetimeValidTime = DateUtils.getNow().plusHours(7)
        tUserOnetimeValidDao!!.insert(tUserOnetimeValid)

        // 仮会員登録メールを送信する
        val mailTemplate = getMailTemplate(MailTemplate.ENTRY_REGIST_TEMPORARY.code)
        val subject = mailTemplate.title
        val templateBody = mailTemplate.text
        val dto = EntryRegistTemporary()
        dto.familyName = tUser.familyName
        dto.domain = domain
        dto.onetimeKey = onetimeKey
        val objects: MutableMap<String, Any> = HashMap()
        objects["dto"] = dto
        val body = sendMailHelper!!.getMailBody(templateBody, objects)
        sendMailHelper!!.sendMail(fromAddress, tUser.email, subject, body)
    }

    /**
     * 本会員登録
     *
     * @param onetimeKey
     */
    fun registComplete(onetimeKey: String) {

        // ワンタイムキーからユーザーIDを取得する
        val tUserOnetimeValid = getTUserOnetimeValid(onetimeKey)
                ?: throw NoDataFoundException("指定されたワンタイムキーが見つかりません。[onetimeKey=$onetimeKey]")

        // 承認期限オーバー
        if (DateUtils.beforeNow(tUserOnetimeValid.onetimeValidTime)) {
            throw NoDataFoundException("指定されたワンタイムキーは承認期限を過ぎています。[onetimeKey=$onetimeKey]")
        }

        // ユーザー情報を取得する
        val tUser = tUserDao!!.selectById(tUserOnetimeValid.userId)
                .orElseThrow {
                    NoDataFoundException(
                            "user_id=" + tUserOnetimeValid.userId + " のデータが見つかりません。")
                }

        // DB登録する
        tUser.status = UserStatus.VALID.code
        tUserRepository!!.update(tUser)

        // 本会員登録完了メールを送信する
        val mailTemplate = getMailTemplate(MailTemplate.ENTRY_REGIST_VALID.code)
        val subject = mailTemplate.title
        val templateBody = mailTemplate.text
        val dto = EntryRegistTemporary()
        dto.familyName = tUser.familyName
        dto.domain = domain
        val objects: MutableMap<String, Any> = HashMap()
        objects["dto"] = dto
        val body = sendMailHelper!!.getMailBody(templateBody, objects)
        sendMailHelper!!.sendMail(fromAddress, tUser.email, subject, body)

        // ワンタイムキーを削除
        tUserOnetimeValidDao!!.delete(tUserOnetimeValid)
    }

    /**
     * ワンタイムキー生成
     *
     * @return 生成されたワンタイムキー
     */
    private fun generateOnetimeKey(): String {
        var onetimeKey = ""
        var loopFlg = true
        do {
            // ランダムな文字列を生成する。
            onetimeKey = RandomStringUtils.randomAlphanumeric(32)

            // 生成したキーが存在しないか確認する
            if (null == getTUserOnetimeValid(onetimeKey)) {
                loopFlg = false
            }
        } while (loopFlg)
        return onetimeKey
    }

    /**
     * 会員-初期承認Entityを取得する
     *
     * @param onetimeKey ワンタイムキー
     * @return 会員Entity
     */
    fun getTUserOnetimeValid(onetimeKey: String?): TUserOnetimeValid? {
        val criteria = TUserOnetimeValidCriteria()
        criteria.onetimeKeyEq = onetimeKey
        return tUserOnetimeValidDao!!.findOne(criteria).orElse(null)
    }

    /**
     * メールテンプレートを取得する。
     *
     * @return
     */
    protected fun getMailTemplate(templateId: Int): MMailTemplate {
        val criteria = MMailTemplateCriteria()
        criteria.mailTemplateIdEq = templateId
        return mMailTemplateDao!!.findOne(criteria).orElseThrow { NoDataFoundException("templateKey=$templateId のデータが見つかりません。") }
    }
}