package com.isystk.sample.web.base.util

import com.isystk.sample.common.util.NumberUtils
import javax.servlet.http.Cookie

object CookieUtils {
    /**
     * クッキーを取得します。(文字列)
     *
     * @param name 保存名
     * @return Cookie
     */
    fun getValue(name: String?): String? {
        require(!(name == null || RequestUtils.request == null))
        if (RequestUtils.request!!.cookies == null) {
            return null
        }
        for (cookie in RequestUtils.request!!.cookies) {
            if (name == cookie.name) {
                return cookie.value
            }
        }
        return null
    }

    /**
     * クッキーを取得します。(数値)
     *
     * @param name 保存名
     * @return Cookie
     */
    fun getValueInteger(name: String?): Int? {
        val value = getValue(name)
        var result: Int? = null
        if (value != null) {
            result = NumberUtils.toInteger(value)
        }
        return result
    }

    /**
     * クッキーを保存します。
     *
     * @param name 保存名
     * @param value 値
     */
    fun setValue(name: String?, value: String?) {
        val cookie = Cookie(name, value)
        cookie.maxAge = 30
        cookie.path = "/"
        cookie.secure = true
        RequestUtils.response?.addCookie(cookie)
    }

    /**
     * クッキーを保存します。(内容は細かく指定可能)
     *
     * @param key Cookie 名
     * @param value 値
     * @param path パス
     * @param maxAge 寿命
     * @param domainName ドメイン名
     */
    fun setCookie(key: String?, value: String?, path: String?, maxAge: Int, domainName: String?) {
        val cookie = Cookie(key, value)
        if (domainName != null) {
            cookie.domain = domainName
        }
        cookie.maxAge = maxAge
        cookie.secure = true
        cookie.path = path
        RequestUtils.response?.addCookie(cookie)
    }

    /**
     * クッキーを削除します
     *
     * @param key Cookie名
     */
    fun removeCookie(key: String?) {
        require(!(key == null || RequestUtils.request == null))
        if (RequestUtils.request!!.cookies == null) {
            return
        }
        for (cookie in RequestUtils.request!!.cookies) {
            if (key == cookie.name) {
                val targetCookie = Cookie(key, null)
                targetCookie.maxAge = 0
                targetCookie.path = "/"
                RequestUtils.response?.addCookie(targetCookie)
                return
            }
        }
    }
}