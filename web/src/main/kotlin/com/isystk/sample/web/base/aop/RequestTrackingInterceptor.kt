package com.isystk.sample.web.base.aop

import com.isystk.sample.common.XORShiftRandom
import com.isystk.sample.common.logger
import org.slf4j.MDC
import java.util.concurrent.TimeUnit
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 処理時間をDEBUGログに出力する
 */
class RequestTrackingInterceptor : BaseHandlerInterceptor() {
    // 乱数生成器
    private val random = XORShiftRandom()
    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        // コントローラーの動作前

        // 現在時刻を記録
        val beforeNanoSec = System.nanoTime()
        startTimeHolder.set(beforeNanoSec)

        // トラッキングID
        val trackId = getTrackId(request)
        MDC.put(HEADER_X_TRACK_ID, trackId)
        response.setHeader(HEADER_X_TRACK_ID, trackId)
        return true
    }

    @Throws(Exception::class)
    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse,
                                 handler: Any, ex: Exception?) {
        // 処理完了後
        val beforeNanoSec = startTimeHolder.get() ?: return
        val elapsedNanoSec = System.nanoTime() - beforeNanoSec
        val elapsedMilliSec = TimeUnit.NANOSECONDS.toMillis(elapsedNanoSec)
        logger.info("path={}, method={}, Elapsed {}ms.", request.requestURI, request.method,
                elapsedMilliSec)

        // 破棄する
        startTimeHolder.remove()
    }

    /**
     * トラッキングIDを取得する。
     *
     * @param request
     * @return
     */
    private fun getTrackId(request: HttpServletRequest): String {
        var trackId = request.getHeader(HEADER_X_TRACK_ID)
        if (trackId == null) {
            val seed = Int.MAX_VALUE
            trackId = random.nextInt(seed).toString()
        }
        return trackId
    }

    companion object {
        private val startTimeHolder = ThreadLocal<Long>()
        private const val HEADER_X_TRACK_ID = "X-Track-Id"
    }
}