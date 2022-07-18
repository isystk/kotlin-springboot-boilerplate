package com.isystk.sample.web.front.service

import com.google.common.collect.Lists
import com.isystk.sample.common.service.BaseTransactionalService
import com.isystk.sample.common.util.NumberUtils
import com.isystk.sample.common.util.StringUtils
import com.isystk.sample.web.base.util.CookieUtils
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import java.math.BigInteger

@Service
class LikeService : BaseTransactionalService() {
    /**
     * クッキーからお気に入り情報を取得します。
     *
     * @return
     */
    val likes: List<BigInteger>
        get() {
            val cookieString = CookieUtils.getValue(LIKE_COOKIE_KEY)
            val likes = parseCommaSeparatedValue(cookieString)
            return likes
                    .filter { e: String? ->
                        val stockId = NumberUtils.toBigInteger(e)
                        stockId != null
                    }
                    .map { e: String -> NumberUtils.toBigInteger(e)!! }
        }

    /**
     * カンマ区切りの文字列から、文字列の配列を作成する
     *
     * @param cookieString カンマ区切りの文字列
     * @return
     */
    private fun parseCommaSeparatedValue(cookieString: String?): List<String> {
        val result: MutableList<String> = Lists.newArrayList()
        try {
            if (cookieString != null) {
                for (`val` in cookieString.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
                    if (result.contains(`val`) == false) {
                        result.add(`val`)
                    }
                }
            }
        } catch (e: Exception) {
            // void
        }
        return result
    }

    /**
     * クッキーにお気に入り情報を追加します。
     *
     * @return
     */
    fun addLike(stockId: BigInteger): List<BigInteger> {
        var likes = likes.toMutableList()
        Assert.notNull(likes, "likes must not be null")
        likes.add(stockId)
        val join = StringUtils.join("-", *likes.toTypedArray())
        CookieUtils.setCookie(LIKE_COOKIE_KEY, join, "/", COOKIE_MAXAGE, null)
        return likes
    }

    /**
     * クッキーからお気に入り情報を削除します。
     *
     * @return
     */
    fun removeLike(stockId: BigInteger): List<BigInteger> {
        var likes: List<BigInteger> = likes
        Assert.notNull(likes, "likes must not be null")
        likes = likes.filter { e: BigInteger -> e != stockId }
        val join = StringUtils.join("-", *likes.toTypedArray())
        CookieUtils.setCookie(LIKE_COOKIE_KEY, join, "/", COOKIE_MAXAGE, null)
        return likes
    }

    companion object {
        private const val LIKE_COOKIE_KEY = "likes"
        private const val COOKIE_MAXAGE = 24 * 60 * 60 * 365 // 1年
    }
}