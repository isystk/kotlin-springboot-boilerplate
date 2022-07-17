package com.isystk.sample.common.dto

import java.io.Serializable

class PageImpl<T>(data: List<T>, pageable: Pageable, count: Long) : Page<T>, Serializable {
    override var data: List<T> = data
    override var count: Long = count
    var page = pageable.page()
    var perpage = pageable.perpage()
    override var totalPages = Math.max(1, Math.ceil(count.toDouble() / perpage).toInt())

    override fun page(): Int {
        return page
    }

    override fun perpage(): Int {
        return perpage
    }

    companion object {
        private const val serialVersionUID = -4365096766976038677L
    }
}