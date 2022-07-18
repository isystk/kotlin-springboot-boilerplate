package com.isystk.sample.web.admin.controller.html.stock

import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.web.base.controller.html.BaseSearchForm
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigInteger
import java.time.LocalDate
import javax.validation.constraints.Digits

class StockListForm : BaseSearchForm(), Pageable {

    @Digits(integer = 9, fraction = 0)
    var stockId: BigInteger? = null

    var name: String? = null

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var createdAtFrom: LocalDate? = null

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var createdAtTo: LocalDate? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}