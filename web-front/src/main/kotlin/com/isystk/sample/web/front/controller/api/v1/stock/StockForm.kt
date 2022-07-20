package com.isystk.sample.web.front.controller.api.v1.stock

import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.web.base.controller.html.BaseSearchForm

class StockForm : BaseSearchForm(), Pageable {
    override fun perpage(): Int {
        return 6
    }

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}