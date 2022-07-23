package com.isystk.sample.web.admin.controller.html.contact.edit

import com.isystk.sample.common.AdminUrl
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.common.util.StringUtils
import com.isystk.sample.domain.dto.ContactFormImageRepositoryDto
import com.isystk.sample.domain.dto.ContactFormRepositoryDto
import com.isystk.sample.web.admin.service.ContactService
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import org.apache.commons.compress.utils.Lists
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping(AdminUrl.CONTACTS_EDIT)
@SessionAttributes(types = [ContactEditForm::class])
class ContactEditController : AbstractHtmlController() {
    @Autowired
    var contactService: ContactService? = null

    @Autowired
    var contactEditFormValidator: ContactEditFormValidator? = null
    @ModelAttribute
    fun initForm(): ContactEditForm {
        return ContactEditForm()
    }

    @InitBinder
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(contactEditFormValidator)
    }

    override fun getFunctionName(): String {
        return "A_CONTACT_EDIT"
    }

    /**
     * 初期表示
     *
     * @param form
     * @param model
     * @return
     */
    @GetMapping("{contactId}")
    fun editIndex(@ModelAttribute form: ContactEditForm, model: Model): String {

        // 1件取得する
        val contact = contactService!!.findById(form.contactId!!)

        // 取得したDtoをFromに詰め替える
        ObjectMapperUtils.map(contact, form)
        val contactImageList = contact!!.imageList
        if (contactImageList != null) {
            if (0 < contactImageList.size) {
                form.contactImageData = contactImageList[0].contactImageData
                form.contactImageName = contactImageList[0].contactImageName
            }
        }
        return showEditIndex(form, model)
    }

    /**
     * 修正画面表示
     *
     * @param form
     * @param model
     * @return
     */
    private fun showEditIndex(
            form: ContactEditForm, model: Model): String {
        return "modules/contact/edit/index"
    }

    /**
     * 修正確認画面表示
     *
     * @param form
     * @param br
     * @param sessionStatus
     * @param attributes
     * @return
     */
    @PutMapping(params = ["confirm"])
    fun editConfirm(@Validated @ModelAttribute form: ContactEditForm, br: BindingResult,
                    sessionStatus: SessionStatus?, attributes: RedirectAttributes, model: Model): String {

        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            setFlashAttributeErrors(attributes, br)
            return showEditIndex(form, model)
        }
        return "modules/contact/edit/confirm"
    }

    /**
     * 前に戻る
     *
     * @param form
     * @param br
     * @param sessionStatus
     * @param attributes
     * @return
     */
    @PutMapping(params = ["back"])
    fun editBack(@Validated @ModelAttribute form: ContactEditForm, br: BindingResult?,
                 sessionStatus: SessionStatus?, attributes: RedirectAttributes, model: Model): String {
        return showEditIndex(form, model)
    }

    /**
     * 更新処理
     *
     * @param form
     * @param br
     * @param sessionStatus
     * @param attributes
     * @return
     */
    @PutMapping(params = ["complete"])
    fun updateComplete(@Validated @ModelAttribute form: ContactEditForm, br: BindingResult,
                       sessionStatus: SessionStatus, attributes: RedirectAttributes, model: Model): String {

        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            setFlashAttributeErrors(attributes, br)
            return showEditIndex(form, model)
        }

        // 入力値を詰め替える
        val tContactsDto = ObjectMapperUtils.map(form, ContactFormRepositoryDto::class.java)
        if (!StringUtils.isBlankOrSpace(form.contactImageName) && !StringUtils.isBlankOrSpace(form.contactImageData)) {
            val imageList: MutableList<ContactFormImageRepositoryDto> = Lists.newArrayList()
            val dto = ContactFormImageRepositoryDto()
            dto.contactImageName = form.contactImageName
            dto.contactImageData = form.contactImageData
            imageList.add(dto)
            tContactsDto.imageList = imageList
        }
        tContactsDto.id = form.contactId

        // 更新する
        contactService!!.update(tContactsDto)

        // セッションのcontactsEditFormをクリアする
        sessionStatus.setComplete()
        return "redirect:/contacts/edit/complete"
    }

    /**
     * 修正完了画面表示
     *
     * @return
     */
    @GetMapping("complete")
    fun showComplete(): String {
        return "modules/contact/edit/complete"
    }

}