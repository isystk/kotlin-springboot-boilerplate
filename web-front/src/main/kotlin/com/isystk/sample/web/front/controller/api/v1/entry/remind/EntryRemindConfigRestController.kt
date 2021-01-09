package com.isystk.sample.web.front.controller.api.v1.entry.remind

import com.isystk.sample.common.Const
import com.isystk.sample.common.FrontUrl
import com.isystk.sample.common.exception.NoDataFoundException
import com.isystk.sample.common.exception.ValidationErrorException
import com.isystk.sample.web.base.controller.api.AbstractRestController
import com.isystk.sample.web.base.controller.api.resource.Resource
import com.isystk.sample.web.front.service.EntryRemindService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*

/**
 * 会員パスワード変更
 */
@RestController
@RequestMapping(path = [FrontUrl.API_V1_ENTRY_REMIND_CONFIG], produces = [MediaType.APPLICATION_JSON_VALUE])
class EntryRemindConfigRestController : AbstractRestController() {
    @Autowired
    var entryRemindService: EntryRemindService? = null

    @Autowired
    var passwordEncoder: PasswordEncoder? = null

    @Autowired
    var entryRemindConfigRestFormValidator: EntryRemindConfigRestFormValidator? = null

    @ModelAttribute("entryRemindConfigRestForm")
    fun entryRemindConfigRestForm(): EntryRemindConfigRestForm {
        return EntryRemindConfigRestForm()
    }

    @InitBinder("entryRemindConfigRestForm")
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(entryRemindConfigRestFormValidator)
    }

    override fun getFunctionName(): String {
        return "API_ENTRY_REMIND_CONFIG"
    }

    /**
     * パスワード変更画面表示
     *
     * @param onetimeKey
     * @return
     */
    @GetMapping("{onetimeKey}")
    fun config(@PathVariable onetimeKey: String,
               @ModelAttribute form: EntryRemindConfigRestForm, model: Model?): Resource {
        val resource = resourceFactory.create()

        // ワンタイムキーからユーザーIDを取得する
        val tUserOnetimePass = entryRemindService!!.getTUserOnetimePass(onetimeKey)
                ?: throw NoDataFoundException("指定されたワンタイムキーが見つかりません。[onetimeKey=$onetimeKey]")
        form.onetimeKey = onetimeKey
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        return resource
    }

    /**
     * パスワード変更処理
     *
     * @return
     */
    @PostMapping
    fun changePassword(@Validated @ModelAttribute form: EntryRemindConfigRestForm,
                       br: BindingResult, errors: Errors?): Resource {
        val resource = resourceFactory.create()

        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            throw ValidationErrorException(errors)
        }

        // ワンタイムキーからユーザーIDを取得する
        val tUserOnetimePass = entryRemindService!!.getTUserOnetimePass(form.onetimeKey)
                ?: throw NoDataFoundException(
                        "指定されたワンタイムキーが見つかりません。[onetimeKey=" + form.onetimeKey + "]")

        // パスワードをハッシュ化する
        val password = passwordEncoder!!.encode(form.password)

        // パスワード変更ワンタイムパス登録
        entryRemindService!!.changePassword(form.onetimeKey, password)
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        return resource
    }
}