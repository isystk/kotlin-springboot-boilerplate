package com.isystk.sample.web.front.controller.api.v1.entry

import com.isystk.sample.common.Const
import com.isystk.sample.common.FrontUrl
import com.isystk.sample.common.exception.ValidationErrorException
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.domain.entity.TUser
import com.isystk.sample.web.base.controller.api.AbstractRestController
import com.isystk.sample.web.base.controller.api.resource.Resource
import com.isystk.sample.web.front.service.EntryService
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
 * 会員登録
 */
@RestController
@RequestMapping(path = [FrontUrl.API_V1_ENTRY_REGIST], produces = [MediaType.APPLICATION_JSON_VALUE])
class EntryRestController : AbstractRestController() {
    @Autowired
    var entryService: EntryService? = null

    @Autowired
    var passwordEncoder: PasswordEncoder? = null

    @Autowired
    var entryFormValidator: EntryRestFormValidator? = null

    @ModelAttribute("entryRestForm")
    fun entryRestForm(): EntryRestForm {
        return EntryRestForm()
    }

    @InitBinder("entryRestForm")
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(entryFormValidator)
    }

    override fun getFunctionName(): String {
        return "API_ENTRY"
    }

    /**
     * 仮会員登録処理
     *
     * @param form
     * @param br
     * @param errors
     * @return
     */
    @PostMapping
    fun ontime(@Validated @ModelAttribute form: EntryRestForm, br: BindingResult, errors: Errors?): Resource {
        val resource = resourceFactory.create()

        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            throw ValidationErrorException(errors)
        }

        // 入力値からDTOを作成する
        val inputUser = ObjectMapperUtils.map(form, TUser::class.java)
        val password = form.password

        // パスワードをハッシュ化する
        inputUser.password = passwordEncoder!!.encode(password)

        // 仮会員登録
        entryService!!.registTemporary(inputUser)
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        return resource
    }

    /**
     * 本会員登録処理
     *
     * @param onetimeKey
     * @return
     */
    @PutMapping("{onetimeKey}")
    fun complete(@PathVariable onetimeKey: String, model: Model?): Resource {
        val resource = resourceFactory.create()

        // 本会員登録
        entryService!!.registComplete(onetimeKey)
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        return resource
    }
}