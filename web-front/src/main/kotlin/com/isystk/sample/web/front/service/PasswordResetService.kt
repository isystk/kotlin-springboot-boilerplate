package com.isystk.sample.web.front.service

import com.google.common.collect.Maps
import com.isystk.sample.common.dto.mail.MailEntryRegistTemporary
import com.isystk.sample.common.exception.NoDataFoundException
import com.isystk.sample.common.helper.SendMailHelper
import com.isystk.sample.common.service.BaseTransactionalService
import com.isystk.sample.common.util.DateUtils
import com.isystk.sample.common.values.MailTemplateDiv
import com.isystk.sample.domain.dao.PasswordResetDao
import com.isystk.sample.domain.dto.PasswordResetCriteria
import com.isystk.sample.domain.dto.UserCriteria
import com.isystk.sample.domain.entity.PasswordReset
import com.isystk.sample.domain.entity.User
import com.isystk.sample.domain.repository.MailTemplateRepository
import com.isystk.sample.domain.repository.UserRepository
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class PasswordResetService : BaseTransactionalService() {
    @Value("\${spring.mail.properties.mail.from}")
    var fromAddress: String? = null

    @Value("\${server.address}")
    var domain: String? = null

    @Autowired
    var passwordResetDao: PasswordResetDao? = null

    @Autowired
    var mailTemplateRepository: MailTemplateRepository? = null

    @Autowired
    var userRepository: UserRepository? = null

    @Autowired
    var sendMailHelper: SendMailHelper? = null

    /**
     * パスワード変更ワンタイムパス登録
     *
     * @param email
     */
    fun registOnetimePass(email: String?) {

        // ユーザー情報を取得する
        val criteria = UserCriteria()
        criteria.emailEq = email
        criteria.isDeleteFlgFalse = true
        val user: User = userRepository!!.findOne(criteria)
            ?: throw NoDataFoundException("email=$email のデータが見つかりません。")

        // パスワード変更ワンタイムパスを登録する(Delete→Insert)
        run {
            val passwordResetCriteria = PasswordResetCriteria()
            passwordResetCriteria.emailEq = user.email
            val passwordReset = passwordResetDao!!.findOne(passwordResetCriteria)
            if (passwordReset !== null) {
                passwordResetDao!!.delete(passwordReset)
            }
        }
        val passwordReset = PasswordReset()
        passwordReset.email = user.email
        val onetimeKey = generateOnetimeKey()
        passwordReset.token = onetimeKey
        passwordReset.createdAt = DateUtils.now
        passwordReset.updatedAt = DateUtils.now
        passwordReset.deleteFlg = false
        passwordResetDao!!.insert(passwordReset)

        // 新パスワード設定画面のお知らせメールを送信する
        val mailTemplate = mailTemplateRepository!!.getMailTemplate(MailTemplateDiv.ENTRY_REMIND)
        val subject = mailTemplate.title
        val templateBody = mailTemplate.text
        val dto = MailEntryRegistTemporary()
        dto.userName = user.name
        dto.domain = domain
        dto.onetimeKey = onetimeKey
        val objects: MutableMap<String, Any> = Maps.newHashMap()
        objects["dto"] = dto
        val body = sendMailHelper!!.getMailBody(templateBody, objects)
        sendMailHelper!!.sendMail(fromAddress, user.email, subject, body)
    }

    /**
     * パスワード変更
     *
     * @param onetimeKey
     * @param password
     */
    fun changePassword(onetimeKey: String, password: String?) {

        // ワンタイムキーからユーザーIDを取得する
        val criteria = PasswordResetCriteria()
        criteria.tokenEq = onetimeKey
        val passwordReset = passwordResetDao!!.findOne(criteria) ?: throw NoDataFoundException("指定されたワンタイムキーが見つかりません。[onetimeKey=$onetimeKey]")

        // 承認期限オーバー
        if (DateUtils.beforeNow(DateUtils.addMinutes(passwordReset.createdAt, 60))) {
            throw NoDataFoundException("指定されたワンタイムキーは期限を過ぎています。[onetimeKey=$onetimeKey]")
        }

        // ユーザー情報を取得する
        val userCriteria = UserCriteria()
        userCriteria.emailEq = passwordReset.email
        val user: User = userRepository!!.findOne(userCriteria) ?: throw NoDataFoundException("email=" + passwordReset.email + " のデータが見つかりません。")

        // パスワードを変更する
        user.password = password
        userRepository!!.update(user)

        // ワンタイムキーを削除
        passwordResetDao!!.delete(passwordReset)
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