package com.isystk.sample.common

import java.util.concurrent.TimeUnit

/**
 * スロットリングする
 */
class Throttler(maxRequestsPerPeriod: Int = 0) {
    private val maxRequestsPerPeriod: Long = maxRequestsPerPeriod.toLong()
    private val timePeriodMillis: Long = 1000
    private var timeSlot: TimeSlot? = null

    /**
     * 処理を実行します。
     *
     * @param callback
     */
    fun <T> process(callback: Callback<T>) {
        // 秒間アクセス数を超えている場合の遅延させるべきミリ秒数
        val delay = calculateDelay()
        if (0 < delay) {
            delay(delay)
        }
        callback.execute()
    }

    /**
     * 指定されたミリ秒スリープさせる。
     *
     * @param delay
     * @throws InterruptedException
     */
    protected fun delay(delay: Long) {
        if (0 < delay) {
            try {
                TimeUnit.MICROSECONDS.sleep(delay)
            } catch (e: InterruptedException) {
            }
        }
    }

    @Synchronized
    protected fun calculateDelay(): Long {
        val slot = nextSlot()
        return if (!slot!!.isActive) {
            slot.startTime - System.currentTimeMillis()
        } else 0
    }

    @Synchronized
    protected fun nextSlot(): TimeSlot? {
        if (timeSlot == null) {
            timeSlot = TimeSlot()
        }
        if (timeSlot!!.isFull) {
            timeSlot = timeSlot!!.next()
        }
        timeSlot!!.assign()
        return timeSlot
    }

    protected inner class TimeSlot protected constructor(val startTime: Long) {
        private var capacity = maxRequestsPerPeriod
        private val duration = timePeriodMillis

        constructor() : this(System.currentTimeMillis()) {}

        @Synchronized
        fun assign() {
            capacity--
        }

        @Synchronized
        operator fun next(): TimeSlot {
            val startTime = Math.max(System.currentTimeMillis(), startTime + duration)
            return TimeSlot(startTime)
        }

        @get:Synchronized
        val isActive: Boolean
            get() = startTime <= System.currentTimeMillis()

        @get:Synchronized
        val isFull: Boolean
            get() = capacity <= 0
    }

    interface Callback<T> {
        fun execute(): T
    }
}