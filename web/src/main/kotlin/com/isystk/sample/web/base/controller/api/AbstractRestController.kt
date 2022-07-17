package com.isystk.sample.web.base.controller.api

import com.isystk.sample.common.FunctionNameAware
import com.isystk.sample.web.base.controller.BaseController
import com.isystk.sample.web.base.controller.api.resource.ResourceFactory
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * 基底APIコントローラー
 */
@ResponseStatus(HttpStatus.OK)
@CrossOrigin
abstract class AbstractRestController : BaseController(), FunctionNameAware {
    @Autowired
    protected var resourceFactory: ResourceFactory? = null

    companion object {
        private val log = LoggerFactory.getLogger(AbstractRestController::class.java)
    }
}