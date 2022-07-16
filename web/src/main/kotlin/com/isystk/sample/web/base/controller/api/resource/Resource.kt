package com.isystk.sample.web.base.controller.api.resource

import com.isystk.sample.common.dto.Dto

interface Resource {
    var data: List<Dto?>?
    var message: String?
    var result: Boolean?
}