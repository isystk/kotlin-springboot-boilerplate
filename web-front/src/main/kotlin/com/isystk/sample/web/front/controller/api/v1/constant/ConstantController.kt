package com.isystk.sample.web.front.controller.api.v1.constant

import com.isystk.sample.common.Const
import com.isystk.sample.common.FrontUrl
import com.isystk.sample.common.dto.CodeValueDto
import com.isystk.sample.common.dto.CodeValueGroupDto
import com.isystk.sample.common.values.Age
import com.isystk.sample.common.values.Gender
import com.isystk.sample.common.values.Prefecture
import com.isystk.sample.web.base.controller.api.AbstractRestController
import com.isystk.sample.web.base.controller.api.resource.Resource
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = [FrontUrl.API_V1_CONSTS], produces = [MediaType.APPLICATION_JSON_VALUE])
class ConstantController : AbstractRestController() {
    @Value("\${stripe.apiKey}")
    var apiKey: String? = null
    override fun getFunctionName(): String {
        return "API_CONSTANTS"
    }

    /**
     * 定数取得します。
     *
     * @return
     */
    @GetMapping
    fun index(): Resource {
        val resource = resourceFactory!!.create()
        var list = mutableListOf<CodeValueGroupDto>()
        list.add(CodeValueGroupDto("gender", Gender.values()
                .map { values: Gender ->
                    val dto = CodeValueDto()
                    dto.text = values.text
                    dto.code = values.code
                    dto
                }))
        list.add(CodeValueGroupDto("age", Age.values()
                .map { values: Age ->
                    val dto = CodeValueDto()
                    dto.text = values.text
                    dto.code = values.code
                    dto
                }))
        list.add(CodeValueGroupDto("prefecture", Prefecture.values()
                .map { values: Prefecture ->
                    val dto = CodeValueDto()
                    dto.text = values.text
                    dto.code = values.code
                    dto
                }))
        val dto = CodeValueDto()
        dto.text = "stripe_key"
        dto.code = apiKey
        list.add(CodeValueGroupDto("stripe", listOf(dto)))
        resource.data = list
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        resource.result = true
        return resource
    }
}