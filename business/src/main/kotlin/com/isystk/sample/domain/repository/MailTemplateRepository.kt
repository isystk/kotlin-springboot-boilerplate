package com.isystk.sample.domain.repository

import com.isystk.sample.common.exception.NoDataFoundException
import com.isystk.sample.common.service.BaseRepository
import com.isystk.sample.common.values.MailTemplateDiv
import com.isystk.sample.domain.dao.MailTemplateDao
import com.isystk.sample.domain.entity.MailTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.lang.Long

/**
 * メールテンプレートリポジトリ
 */
@Repository
class MailTemplateRepository : BaseRepository() {
    @Autowired
    var mailTemplateDao: MailTemplateDao? = null

    /**
     * メールテンプレートを取得する。
     *
     * @return
     */
    fun getMailTemplate(mailTemplateDiv: MailTemplateDiv): MailTemplate {
        return mailTemplateDao!!.selectById(Long.valueOf(mailTemplateDiv.code)).orElseThrow { NoDataFoundException("templateKey=" + mailTemplateDiv.code + " のデータが見つかりません。") }
    }
}