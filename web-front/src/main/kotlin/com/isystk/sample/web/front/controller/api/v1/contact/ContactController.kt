package com.isystk.sample.web.front.controller.api.v1.contact

import com.google.common.collect.Lists
import com.isystk.sample.common.Const
import com.isystk.sample.common.FrontUrl
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.common.util.StringUtils
import com.isystk.sample.domain.dto.ContactFormImageRepositoryDto
import com.isystk.sample.domain.dto.ContactFormRepositoryDto
import com.isystk.sample.web.base.controller.api.AbstractRestController
import com.isystk.sample.web.base.controller.api.resource.Resource
import com.isystk.sample.web.front.service.ContactService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = [FrontUrl.API_V1_CONTACTS], produces = [MediaType.APPLICATION_JSON_VALUE])
class ContactController : AbstractRestController() {
    @Autowired
    var contactService: ContactService? = null
    override fun getFunctionName(): String {
        return "API_CONTACTS"
    }

    @Autowired
    var contactFormValidator: ContactFormValidator? = null
    @ModelAttribute("form")
    fun initForm(): ContactForm {
        return ContactForm()
    }

    @InitBinder("form")
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(contactFormValidator)
    }

    /**
     * お問い合わせを登録します。
     *
     * @return
     */
    @PostMapping("/regist")
    fun regist(@Validated @ModelAttribute("form") form: ContactForm, br: BindingResult): Resource {
        val resource = resourceFactory!!.create()

        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            // TODO エラーメッセージを返却できるようにしたい
            resource.message = getMessage(VALIDATION_ERROR)
            resource.result = false
            return resource
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

        // 登録する
        contactService!!.create(tContactsDto)
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        resource.result = true
        return resource
    }
}