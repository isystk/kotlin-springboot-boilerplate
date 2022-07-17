package com.isystk.sample.common.helper

import com.isystk.sample.common.exception.SystemException
import com.isystk.sample.common.util.ValidateUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.MailException
import org.springframework.stereotype.Component
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.templateresolver.ITemplateResolver
import org.thymeleaf.templateresolver.StringTemplateResolver
import java.util.*
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

/**
 * メール送信ヘルパー
 */
@Component
class SendMailHelper {
    @Value("\${spring.mail.host}")
    private val SMTP_HOST_NAME: String? = null

    /**
     * メールを送信します。
     *
     * @param fromAddress
     * @param toAddress
     * @param subject
     * @param body
     */
    fun sendMail(fromAddress: String?, toAddress: String?, subject: String?, body: String?) {
        try {
            val props = Properties()
            props["mail.localhost.host"] = SMTP_HOST_NAME
            val session2 = Session.getDefaultInstance(props, null)
            val message = MimeMessage(session2)
            message.addFrom(InternetAddress.parse(fromAddress))
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress))
            //			message.setSubject(subject, "iso-2022-jp");
//			message.setText(body, "iso-2022-jp");
            message.setSubject(subject, "utf-8")
            message.setText(body, "utf-8")
            Transport.send(message)
        } catch (e: MailException) {
            log.error("failed to send mail.", e)
            throw SystemException(e)
        } catch (e: AddressException) {
            log.error("failed to send mail.", e)
            throw SystemException(e)
        } catch (e: MessagingException) {
            log.error("failed to send mail.", e)
            throw SystemException(e)
        }
    }

    /**
     * 指定したテンプレートのメール本文を返します。
     *
     * @param template
     * @param objects
     * @return
     */
    fun getMailBody(template: String?, objects: Map<String, Any>): String {
        val templateEngine = SpringTemplateEngine()
        templateEngine.setTemplateResolver(templateResolver())
        val context = Context()
        if (ValidateUtils.isNotEmpty(objects)) {
            objects.forEach { (name: String?, value: Any?) -> context.setVariable(name, value) }
        }
        return templateEngine.process(template, context)
    }

    private fun templateResolver(): ITemplateResolver {
        val resolver = StringTemplateResolver()
        resolver.setTemplateMode("TEXT")
        resolver.isCacheable = false // 安全をとってキャッシュしない
        return resolver
    }

    companion object {
        private val log = LoggerFactory.getLogger(SendMailHelper::class.java)
    }
}