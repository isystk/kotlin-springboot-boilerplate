package com.isystk.sample.web.admin.controller.html.photo

import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.web.base.controller.html.BaseSearchForm

class PhotoListForm : BaseSearchForm(), Pageable {

    var name: String? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}