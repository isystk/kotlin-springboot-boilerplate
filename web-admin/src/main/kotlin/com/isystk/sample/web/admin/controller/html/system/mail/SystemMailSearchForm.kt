package com.isystk.sample.web.admin.controller.html.system.mail

import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.web.base.controller.html.BaseSearchForm
import javax.validation.constraints.Digits

class SystemMailSearchForm : BaseSearchForm(), Pageable {
    var templateDiv: @Digits(integer = 9, fraction = 0) Int? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}