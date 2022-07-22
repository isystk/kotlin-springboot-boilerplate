package com.isystk.sample.domain.dto

import com.isystk.sample.domain.entity.OrderHistory
import com.isystk.sample.domain.entity.Stock
import com.isystk.sample.domain.entity.User

class OrderHistoryRepositoryDto : OrderHistory() {
    var stock: Stock? = null
    var user: User? = null
}