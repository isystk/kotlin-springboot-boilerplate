package com.isystk.sample.web.admin.dto

import java.math.BigInteger
import java.time.LocalDate

class StockSearchConditionDto {

    var stockId: Long? = null

    var name: String? = null

    var createdAtFrom: LocalDate? = null

    var createdAtTo: LocalDate? = null

}