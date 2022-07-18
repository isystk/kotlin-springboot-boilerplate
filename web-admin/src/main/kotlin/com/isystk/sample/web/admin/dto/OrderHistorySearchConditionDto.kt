package com.isystk.sample.web.admin.dto

import java.time.LocalDate

class OrderHistorySearchConditionDto {

    var userName: String? = null

    var stockName: String? = null

    var createdAtFrom: LocalDate? = null

    var createdAtTo: LocalDate? = null

}