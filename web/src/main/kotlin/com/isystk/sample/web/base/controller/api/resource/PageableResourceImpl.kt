package com.isystk.sample.web.base.controller.api.resource

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.isystk.sample.common.dto.Dto

@JsonIgnoreProperties(ignoreUnknown = true)
class PageableResourceImpl : ResourceImpl, PageableResource {
    @set:JsonProperty("current_page")
    @JsonProperty("current_page")
    override var currentPage = 1
    override var total = 0

    constructor() {}
    constructor(data: List<Dto?>?, currentPage: Int, total: Int) {
        this.data = data
        this.currentPage = currentPage
        this.total = total
    }

    override fun equals(o: Any?): Boolean {
        if (o === this) {
            return true
        }
        if (o !is PageableResourceImpl) {
            return false
        }
        val other = o
        if (!other.canEqual(this as Any)) {
            return false
        }
        if (!super.equals(o)) {
            return false
        }
        if (currentPage != other.currentPage) {
            return false
        }
        return if (total != other.total) {
            false
        } else true
    }

    override fun canEqual(other: Any?): Boolean {
        return other is PageableResourceImpl
    }

    override fun hashCode(): Int {
        val PRIME = 59
        var result = super.hashCode()
        result = result * PRIME + currentPage
        result = result * PRIME + total
        return result
    }
}