package com.isystk.sample.web.front.service

import com.google.common.collect.Maps
import com.isystk.sample.common.dto.mail.MailEntryRegistTemporary
import com.isystk.sample.common.exception.NoDataFoundException
import com.isystk.sample.common.helper.SendMailHelper
import com.isystk.sample.common.service.BaseTransactionalService
import com.isystk.sample.common.util.DateUtils
import com.isystk.sample.common.values.MailTemplateDiv
import com.isystk.sample.domain.dto.UserCriteria
import com.isystk.sample.domain.entity.User
import com.isystk.sample.domain.repository.MailTemplateRepository
import com.isystk.sample.domain.repository.UserRepository
import org.apache.commons.lang.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class RegisterService : BaseTransactionalService() {
    @Value("\${spring.mail.properties.mail.from}")
    var fromAddress: String? = null

    @Value("\${server.address}")
    var domain: String? = null

    @Autowired
    var userRepository: UserRepository? = null

    @Autowired
    var mailTemplateRepository: MailTemplateRepository? = null

    @Autowired
    var sendMailHelper: SendMailHelper? = null

    /**
     * 仮会員登録
     *
     * @param user
     */
    fun registTemporary(user: User) {
        val onetimeKey = generateOnetimeKey()
        user.rememberToken = onetimeKey
        userRepository!!.create(user)

        // 仮会員登録メールを送信する
        sendMail(user.id!!)
    }

    /**
     * 仮会員登録メール再送信
     *
     * @param userId
     */
    fun sendMail(userId: BigInteger) {
        val user = userRepository!!.findById(userId)
        if (user.emailVerifiedAt != null) {
            throw NoDataFoundException("既に本会員の状態です。")
        }

        // 仮会員登録メールを送信する
        val mailTemplate = mailTemplateRepository!!.getMailTemplate(MailTemplateDiv.ENTRY_REGIST_TEMPORARY)
        val subject = mailTemplate.title
        val templateBody = mailTemplate.text
        val dto = MailEntryRegistTemporary()
        dto.userName = user.name
        dto.domain = domain
        dto.onetimeKey = user.rememberToken
        val objects: MutableMap<String, Any> = Maps.newHashMap()
        objects["dto"] = dto
        val body = sendMailHelper!!.getMailBody(templateBody, objects)
        sendMailHelper!!.sendMail(fromAddress, user.email, subject, body)
    }

    /**
     * 本会員登録
     *
     * @param onetimeKey
     */
    fun registComplete(onetimeKey: String) {

        // ワンタイムキーからユーザを取得する
        val criteria = UserCriteria()
        criteria.rememberTokenEq = onetimeKey
        val user = userRepository!!.findOne(criteria)
            ?: throw NoDataFoundException("onetimeKey=$onetimeKey のデータが見つかりません。")

        // メールアドレスを検証済みにする
        user.emailVerifiedAt = DateUtils.now
        // ワンタイムトークンを削除する
        user.rememberToken = null
        userRepository!!.update(user)

        // 本会員登録完了メールを送信する
        val mailTemplate = mailTemplateRepository!!.getMailTemplate(MailTemplateDiv.ENTRY_REGIST_VALID)
        val subject = mailTemplate.title
        val templateBody = mailTemplate.text
        val dto = MailEntryRegistTemporary()
        dto.userName = user.name
        dto.domain = domain
        val objects: MutableMap<String, Any> = Maps.newHashMap()
        objects["dto"] = dto
        val body = sendMailHelper!!.getMailBody(templateBody, objects)
        sendMailHelper!!.sendMail(fromAddress, user.email, subject, body)
    }

    /**
     * ワンタイムキー生成
     *
     * @return 生成されたワンタイムキー
     */
    private fun generateOnetimeKey(): String {
        // ランダムな文字列を生成する。
        return RandomStringUtils.randomAlphanumeric(32)
    }
}