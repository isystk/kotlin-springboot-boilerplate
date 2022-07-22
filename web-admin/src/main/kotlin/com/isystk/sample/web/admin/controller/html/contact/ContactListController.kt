package com.isystk.sample.web.admin.controller.html.contact

import com.isystk.sample.common.AdminUrl
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.web.admin.dto.ContactSearchConditionDto
import com.isystk.sample.web.admin.service.ContactService
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.math.BigInteger
import javax.validation.Valid

@Controller
@RequestMapping(AdminUrl.CONTACTS)
class ContactListController : AbstractHtmlController() {
    @Autowired
    var contactService: ContactService? = null

    @Autowired
    var contactListFormValidator: ContactListFormValidator? = null
    override fun getFunctionName(): String {
        return "A_CONTACT_LIST"
    }

    @ModelAttribute
    fun initForm(): ContactListForm {
        return ContactListForm()
    }

    @InitBinder
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(contactListFormValidator)
    }

    /**
     * 一覧画面表示
     *
     * @param form
     * @param model
     * @return
     */
    @GetMapping
    fun index(@ModelAttribute form: @Valid ContactListForm, br: BindingResult,
              sessionStatus: SessionStatus?, attributes: RedirectAttributes, model: Model): String {
        if (br.hasErrors()) {
            setFlashAttributeErrors(attributes, br)
            return "modules/contact/list"
        }

        // 10件区切りで取得する
        val pages = contactService!!.findPage(formToDto(form), form)

        // 画面に検索結果を渡す
        model.addAttribute("pages", pages)
        return "modules/contact/list"
    }

    /**
     * 検索条件を詰める
     *
     * @return
     */
    private fun formToDto(
            form: ContactListForm?): ContactSearchConditionDto {

        // 入力値を詰め替える
        return ObjectMapperUtils.map(form, ContactSearchConditionDto::class.java)
    }

    /**
     * 削除処理
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): String {
        contactService!!.delete(id)
        return "redirect:/contacts"
    }

    companion object {
        private val log = LoggerFactory.getLogger(ContactListController::class.java)
    }
}