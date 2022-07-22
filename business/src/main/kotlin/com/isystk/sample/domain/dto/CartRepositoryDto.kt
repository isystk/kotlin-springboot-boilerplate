package com.isystk.sample.domain.dto

import com.isystk.sample.domain.entity.Cart
import com.isystk.sample.domain.entity.Stock

class CartRepositoryDto : Cart() {
    var stock: Stock? = null
}