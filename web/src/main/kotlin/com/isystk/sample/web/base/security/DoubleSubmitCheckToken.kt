package com.isystk.sample.web.base.security

import com.isystk.sample.common.XORShiftRandom
import com.isystk.sample.web.base.util.SessionUtils
import org.apache.commons.collections.map.LRUMap
import javax.servlet.http.HttpServletRequest

object DoubleSubmitCheckToken {
    const val DOUBLE_SUBMIT_CHECK_PARAMETER = "_double"
    private val DOUBLE_SUBMIT_CHECK_CONTEXT = DoubleSubmitCheckToken::class.java.name + ".CONTEXT"

    // 乱数生成器
    private val random = XORShiftRandom()

    /**
     * 画面から渡ってきたトークンを返します。
     *
     * @param request
     * @return actual token
     */
    fun getActualToken(request: HttpServletRequest): String? {
        return request.getParameter(DOUBLE_SUBMIT_CHECK_PARAMETER)
    }

    /**
     * 指定のキーをもとにセッションに保存されているトークンを返します。
     *
     * @param request
     * @param key
     * @return expected token
     */
    fun getExpectedToken(request: HttpServletRequest, key: String?): String? {
        var key = key
        var token: String? = null
        if (key == null) {
            key = request.requestURI
        }
        val mutex = SessionUtils.getMutex(request)
        if (mutex != null) {
            synchronized(mutex) { token = getToken(request, key) }
        }
        return token
    }

    /**
     * セッションに保存されているトークンを返します。
     *
     * @param request
     * @return expected token
     */
    fun getExpectedToken(request: HttpServletRequest): String? {
        return getExpectedToken(request, null)
    }
    /**
     * 指定のキーをもとにセッションにトークンを設定します。
     *
     * @param request
     * @param key
     * @return token
     */
    /**
     * セッションにトークンを設定します。
     *
     * @param request
     * @return token
     */
    @JvmOverloads
    fun renewToken(request: HttpServletRequest, key: String? = null): String {
        var key = key
        if (key == null) {
            key = request.requestURI
        }
        val token = generateToken()
        val mutex = SessionUtils.getMutex(request)
        if (mutex != null) {
            synchronized(mutex) { setToken(request, key, token) }
        }
        return token
    }

    /**
     * トークンを生成します。
     *
     * @return token
     */
    fun generateToken(): String {
        return random.nextInt(Int.MAX_VALUE).toString()
    }

    /**
     * セッションに格納されたLRUMapを取り出す。存在しない場合は作成して返す。
     *
     * @param request
     * @return
     */
    internal fun getLRUMap(request: HttpServletRequest): LRUMap {
        var map = SessionUtils.getAttribute<LRUMap>(request, DOUBLE_SUBMIT_CHECK_CONTEXT)
        if (map == null) {
            map = LRUMap(10)
        }
        return map
    }

    /**
     * トークンを取得する。
     *
     * @param request
     * @param key
     * @return
     */
    internal fun getToken(request: HttpServletRequest, key: String?): String? {
        val map = getLRUMap(request)
        return map[key] as String?
    }

    /**
     * トークンを保存する。
     *
     * @param request
     * @param key
     * @param token
     */
    internal fun setToken(request: HttpServletRequest, key: String?, token: String?) {
        val map = getLRUMap(request)
        map[key] = token
        SessionUtils.setAttribute(request, DOUBLE_SUBMIT_CHECK_CONTEXT, map)
    }
}