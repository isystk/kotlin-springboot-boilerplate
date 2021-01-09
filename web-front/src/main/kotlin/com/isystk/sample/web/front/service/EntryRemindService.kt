package com.isystk.sample.web.front.service

import com.isystk.sample.common.dto.mail.EntryRegistTemporary
import com.isystk.sample.common.exception.NoDataFoundException
import com.isystk.sample.common.helper.SendMailHelper
import com.isystk.sample.common.service.BaseTransactionalService
import com.isystk.sample.common.util.DateUtils
import com.isystk.sample.common.values.MailTemplate
import com.isystk.sample.domain.dao.MMailTemplateDao
import com.isystk.sample.domain.dao.TUserDao
import com.isystk.sample.domain.dao.TUserOnetimePassDao
import com.isystk.sample.domain.dto.MMailTemplateCriteria
import com.isystk.sample.domain.dto.TUserCriteria
import com.isystk.sample.domain.dto.TUserOnetimePassCriteria
import com.isystk.sample.domain.entity.MMailTemplate
import com.isystk.sample.domain.entity.TUserOnetimePass
import com.isystk.sample.domain.repository.TUserRepository
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class EntryRemindService : BaseTransactionalService() {
    @Value("\${spring.mail.properties.mail.from}")
    var fromAddress: String? = null

    @Value("\${server.address}")
    var domain: String? = null

    @Autowired
    var tUserDao: TUserDao? = null

    @Autowired
    var tUserRepository: TUserRepository? = null

    @Autowired
    var tUserOnetimePassDao: TUserOnetimePassDao? = null

    @Autowired
    var mMailTemplateDao: MMailTemplateDao? = null

    @Autowired
    var sendMailHelper: SendMailHelper? = null

    /**
     * パスワード変更ワンタイムパス登録
     *
     * @param email
     */
    fun registOnetimePass(email: String?) {

        // ユーザー情報を取得する
        val criteria = TUserCriteria()
        criteria.emailEq = email
        criteria.isDeleteFlgFalse = true
        val tUser = tUserDao!!.findOne(criteria)
                .orElseThrow { NoDataFoundException("email=$email のデータが見つかりません。") }

        // パスワード変更ワンタイムパスを登録する(Delete→Insert)
        val onetimePassCriteria = TUserOnetimePassCriteria()
        onetimePassCriteria.userIdEq = tUser.userId
        var tUserOnetimePass = tUserOnetimePassDao!!.findOne(onetimePassCriteria)
                .orElse(null)
        if (tUserOnetimePass != null) {
            tUserOnetimePassDao!!.delete(tUserOnetimePass)
        }
        tUserOnetimePass = TUserOnetimePass()
        tUserOnetimePass.userId = tUser.userId
        val onetimeKey = generateOnetimeKey()
        tUserOnetimePass.onetimeKey = onetimeKey
        // 7時間の制限時間を設ける
        tUserOnetimePass.onetimeValidTime = DateUtils.getNow().plusHours(7)
        tUserOnetimePassDao!!.insert(tUserOnetimePass)

        // 新パスワード設定画面のお知らせメールを送信する
        val mailTemplate = getMailTemplate(MailTemplate.ENTRY_REMIND.code)
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
     * パスワード変更
     *
     * @param onetimeKey
     * @param password
     */
    fun changePassword(onetimeKey: String?, password: String?) {

        // ワンタイムキーからユーザーIDを取得する
        val tUserOnetimePass = getTUserOnetimePass(onetimeKey)
                ?: throw NoDataFoundException("指定されたワンタイムキーが見つかりません。[onetimeKey=$onetimeKey]")

        // 承認期限オーバー
        if (DateUtils.beforeNow(tUserOnetimePass.onetimeValidTime)) {
            throw NoDataFoundException("指定されたワンタイムキーは承認期限を過ぎています。[onetimeKey=$onetimeKey]")
        }

        // ユーザー情報を取得する
        val tUser = tUserDao!!.selectById(tUserOnetimePass.userId)
                .orElseThrow {
                    NoDataFoundException(
                            "user_id=" + tUserOnetimePass.userId + " のデータが見つかりません。")
                }

        // パスワードを変更する
        tUser.password = password
        tUserRepository!!.update(tUser)

        // ワンタイムキーを削除
        tUserOnetimePassDao!!.delete(tUserOnetimePass)
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
            if (null == getTUserOnetimePass(onetimeKey)) {
                loopFlg = false
            }
        } while (loopFlg)
        return onetimeKey
    }

    /**
     * パスワード変更ワンタイムEntityを取得する
     *
     * @param onetimeKey ワンタイムキー
     * @return パスワード変更ワンタイムEntity
     */
    fun getTUserOnetimePass(onetimeKey: String?): TUserOnetimePass? {
        val criteria = TUserOnetimePassCriteria()
        criteria.onetimeKeyEq = onetimeKey
        return tUserOnetimePassDao!!.findOne(criteria).orElse(null)
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