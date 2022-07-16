package com.isystk.sample.web.base.controller.html

abstract class BaseSearchForm : BaseForm() {

    var page = 1
    var perpage = 10

    open fun page(): Int {
        return page;
    }

   open fun perpage(): Int {
        return perpage
    }

    companion object {
        private const val serialVersionUID = -7129975017789825804L
    }
}