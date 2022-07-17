package com.isystk.sample.common.dto

class DefaultPageable(page: Int, perpage: Int) : Pageable {
    var page = 1
    var perpage = 10

    init {
        this.page = page
        this.perpage = perpage
    }

    override fun page(): Int {
        return page
    }

    override fun perpage(): Int {
        return perpage
    }
}