package com.isystk.sample.batch.controller.html.system.mail

import com.isystk.sample.common.AdminUrl
import com.isystk.sample.domain.dto.MMailTemplateCriteria
import com.isystk.sample.domain.repository.MMailTemplateRepository
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes

@Controller
@RequestMapping(AdminUrl.SYSTEM_MAIL)
@SessionAttributes(types = [SystemMailSearchForm::class])
class SystemMailHtmlController : AbstractHtmlController() {
    @Autowired
    var mMailTemplateRepository: MMailTemplateRepository? = null
    override fun getFunctionName(): String {
        return "A_SYSTEM_MAIL"
    }

    /**
     * 一覧画面表示
     *
     * @param form
     * @param model
     * @return
     */
    @GetMapping
    fun index(form: SystemMailSearchForm, model: Model): String {

        // 10件区切りで取得する
        val pages = mMailTemplateRepository!!.findAll(formToCriteria(form), form)

        // 画面に検索結果を渡す
        model.addAttribute("pages", pages)
        return "modules/system/mail/list"
    }

    /**
     * 検索条件を詰める
     *
     * @return
     */
    private fun formToCriteria(form: SystemMailSearchForm): MMailTemplateCriteria {

        // 入力値を詰め替える
        val criteria = MMailTemplateCriteria()
        criteria.templateDivEq = form.templateDiv
        criteria.isDeleteFlgFalse = true
        criteria.orderBy = "order by mail_template_id asc"
        return criteria
    }

    /**
     * 詳細画面表示
     *
     * @param mailTemplateId
     * @param model
     * @return
     */
    @GetMapping("{mailTemplateId}")
    fun show(@PathVariable mailTemplateId: Int?, model: Model): String {
        val mMailTemplateRepositoryDto = mMailTemplateRepository!!.findById(mailTemplateId)
        model.addAttribute("mMailTemplateRepositoryDto", mMailTemplateRepositoryDto)
        return "modules/system/mail/detail"
    }

    companion object {
        private val log = LoggerFactory
                .getLogger(SystemMailHtmlController::class.java)
    }
}