package com.isystk.sample.common.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * 日付ユーティリティ
 */
object DateUtils {

    /**
     * Date型の値を指定されたDateTimeFormatterフォーマットした値を返します。
     *
     * @param fromDate
     * @param formatter
     * @return
     */
    fun format(fromDate: Date, formatter: DateTimeFormatter): String {
        val zoneId = zoneId
        val localDateTime = fromDate.toInstant().atZone(zoneId).toLocalDateTime()
        return formatter.format(localDateTime)
    }

    /**
     * LocalDateTime型の値を指定されたDateTimeFormatterフォーマットした値を返します。
     *
     * @param fromLocalDateTime
     * @param formatter
     * @return
     */
    fun format(fromLocalDateTime: LocalDateTime?,
               formatter: DateTimeFormatter): String {
        return formatter.format(fromLocalDateTime)
    }

    /**
     * Date型の値をLocalDateTime型に変換して返します。
     *
     * @param fromDate
     * @return
     */
    fun toLocalDateTime(fromDate: Date): LocalDateTime {
        val zoneId = zoneId
        return fromDate.toInstant().atZone(zoneId).toLocalDateTime()
    }

    /**
     * LocalDateTime型の値をDate型に変換して返します。
     *
     * @param fromLocalDateTime
     * @return
     */
    fun toDate(fromLocalDateTime: LocalDateTime?): Date {
        val zoneId = zoneId
        val zoneDateTime = ZonedDateTime.of(fromLocalDateTime, zoneId)
        return Date.from(zoneDateTime.toInstant())
    }

    /**
     * LocalDate型の値をDate型に変換して返します。
     *
     * @param localDate
     * @return
     */
    fun toDate(localDate: LocalDate): Date {
        val zoneId = zoneId
        val zoneDateTime = localDate.atStartOfDay(zoneId).toInstant()
        return Date.from(zoneDateTime)
    }

    internal val zoneId: ZoneId
        internal get() = ZoneId.systemDefault()

    /**
     * システムで利用する現在日時を取得します。
     *
     * @return
     */
    val now: LocalDateTime
        get() = LocalDateTime.now()

    /**
     * 引数は現在日時よりも後です。
     *
     * @param fromLocalDateTime
     * @return
     */
    fun afterNow(fromLocalDateTime: LocalDateTime): Boolean {
        return fromLocalDateTime.isAfter(LocalDateTime.now())
    }

    /**
     * 引数は現在日時よりも前です。
     *
     * @param fromLocalDateTime
     * @return
     */
    fun beforeNow(fromLocalDateTime: LocalDateTime): Boolean {
        return fromLocalDateTime.isBefore(LocalDateTime.now())
    }

    /**
     * time1の方が後の場合は正、time2がの方が後の場合は負の数を返却します。
     *
     * @param time1
     * @param time2
     * @return
     */
    fun compareTo(time1: LocalDateTime, time2: LocalDateTime?): Int {
        return time1.compareTo(time2)
    }

    /**
     * 時間（分）を加算します
     *
     * @param time
     * @param minutes
     * @return
     */
    fun addMinutes(time: LocalDateTime, minutes: Long): LocalDateTime {
        return time.plusMinutes(minutes)
    }
}