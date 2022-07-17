package com.isystk.sample.web.base.filter

interface UserIdAware {
    val userId: String?
    val userName: String?

    fun userId(): String? {
        return userId
    }

    fun userName(): String? {
        return userName
    }

}