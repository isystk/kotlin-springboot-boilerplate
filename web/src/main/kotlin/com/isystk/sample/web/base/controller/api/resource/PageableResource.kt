package com.isystk.sample.web.base.controller.api.resource

interface PageableResource : Resource {
    var currentPage: Int
    var total: Int
    abstract override var result: Boolean?
}