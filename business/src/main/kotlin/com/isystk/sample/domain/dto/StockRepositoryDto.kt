package com.isystk.sample.domain.dto

import com.isystk.sample.domain.entity.Stock

class StockRepositoryDto : Stock() {
    var stockImageName: String? = null
    var stockImageData: String? = null
}