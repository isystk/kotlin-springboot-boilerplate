package com.isystk.sample.web.front.controller.api.v1.entry.remind

import com.isystk.sample.common.Const
import com.isystk.sample.common.FrontUrl
import com.isystk.sample.common.exception.ValidationErrorException
import com.isystk.sample.web.base.controller.api.AbstractRestController
import com.isystk.sample.web.base.controller.api.resource.Resource
import com.isystk.sample.web.front.service.EntryRemindService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.validation.BindingResult
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*

/**
 * 会員パスワード変更
 */
@RestController
@RequestMapping(path = [FrontUrl.API_V1_ENTRY_REMIND], produces = [MediaType.APPLICATION_JSON_VALUE])
class EntryRemindRestController : AbstractRestController() {
    @Autowired
    var entryRemindService: EntryRemindService? = null

    @Autowired
    var entryRemindRestFormValidator: EntryRemindRestFormValidator? = null

    @ModelAttribute("entryRemindRestForm")
    fun entryRemindRestForm(): EntryRemindRestForm {
        return EntryRemindRestForm()
    }

    @InitBinder("entryRemindRestForm")
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(entryRemindRestFormValidator)
    }

    override fun getFunctionName(): String {
        return "API_ENTRY_REMIND"
    }

    /**
     * パスワード変更メール送信処理
     *
     * @param form
     * @param br
     * @param errors
     * @return
     */
    @PostMapping
    fun registOnetimePass(@Validated @ModelAttribute form: EntryRemindRestForm,
                          br: BindingResult, errors: Errors?): Resource {
        val resource = resourceFactory.create()

        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            throw ValidationErrorException(errors)
        }

        // パスワード変更ワンタイムパス登録
        entryRemindService!!.registOnetimePass(form.email)
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        return resource
    }
}