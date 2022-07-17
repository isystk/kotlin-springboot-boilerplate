package com.isystk.sample.common

/**
 * 管理画面 URL
 */
interface AdminUrl {
    companion object {
        /**
         * ---- URLs ----
         */
        const val HOME = "/"
        const val STOCKS = "/stocks"
        const val STOCKS_REGIST = "/stocks/regist"
        const val STOCKS_EDIT = "/stocks/edit"
        const val ORDERS = "/orders"
        const val PHOTOS = "/photos"
        const val USERS = "/users"
        const val CONTACTS = "/contacts"
        const val CONTACTS_EDIT = "/contacts/edit"
    }
}