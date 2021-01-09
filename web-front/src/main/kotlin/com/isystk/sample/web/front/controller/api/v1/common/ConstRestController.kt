package com.isystk.sample.web.front.controller.api.v1.common

import com.isystk.sample.common.Const
import com.isystk.sample.common.FrontUrl
import com.isystk.sample.common.dto.CodeValueDto
import com.isystk.sample.common.dto.CodeValueGroupDto
import com.isystk.sample.common.values.Prefecture
import com.isystk.sample.common.values.Sex
import com.isystk.sample.domain.repository.MPostTagRepository
import com.isystk.sample.web.base.controller.api.AbstractRestController
import com.isystk.sample.web.base.controller.api.resource.Resource
import org.apache.commons.compress.utils.Lists
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping(path = [FrontUrl.API_V1_COMMON_CONST], produces = [MediaType.APPLICATION_JSON_VALUE])
class ConstRestController : AbstractRestController() {
    @Autowired
    var mPostTagRepository: MPostTagRepository? = null
    override fun getFunctionName(): String {
        return "API_COMMON_CONST"
    }

    /**
     * 定数取得します。
     *
     * @return
     */
    @GetMapping
    fun index(): Resource {
        val resource = resourceFactory.create()
        val list: MutableList<CodeValueGroupDto> = Lists.newArrayList()
        list.add(CodeValueGroupDto("sex", Arrays.stream(Sex.values())
                .map { values: Sex ->
                    val dto = CodeValueDto()
                    dto.text = values.text
                    dto.code = values.code
                    dto
                }.collect(Collectors.toList())))
        list.add(CodeValueGroupDto("prefecture", Arrays.stream(Prefecture.values())
                .map { values: Prefecture ->
                    val dto = CodeValueDto()
                    dto.text = values.text
                    dto.code = values.code
                    dto
                }.collect(Collectors.toList())))
        list.add(CodeValueGroupDto("postTag", mPostTagRepository!!.findAllSelectList()))
        resource.data = list
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        return resource
    }
}