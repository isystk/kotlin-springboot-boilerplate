package com.isystk.sample.common.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.slf4j.Logger;

/**
 * 日付ユーティリティ
 */
public class DateUtils {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(DateUtils.class);

  /**
   * Date型の値を指定されたDateTimeFormatterフォーマットした値を返します。
   *
   * @param fromDate
   * @param formatter
   * @return
   */
  public static String format(final Date fromDate, final DateTimeFormatter formatter) {
    ZoneId zoneId = getZoneId();
    LocalDateTime localDateTime = fromDate.toInstant().atZone(zoneId).toLocalDateTime();
    String result = formatter.format(localDateTime);
    return result;
  }

  /**
   * LocalDateTime型の値を指定されたDateTimeFormatterフォーマットした値を返します。
   *
   * @param fromLocalDateTime
   * @param formatter
   * @return
   */
  public static String format(final LocalDateTime fromLocalDateTime,
      final DateTimeFormatter formatter) {
    String result = formatter.format(fromLocalDateTime);
    return result;
  }

  /**
   * Date型の値をLocalDateTime型に変換して返します。
   *
   * @param fromDate
   * @return
   */
  public static LocalDateTime toLocalDateTime(final Date fromDate) {
    ZoneId zoneId = getZoneId();
    return fromDate.toInstant().atZone(zoneId).toLocalDateTime();
  }

  /**
   * LocalDateTime型の値をDate型に変換して返します。
   *
   * @param fromLocalDateTime
   * @return
   */
  public static Date toDate(final LocalDateTime fromLocalDateTime) {
    ZoneId zoneId = getZoneId();
    ZonedDateTime zoneDateTime = ZonedDateTime.of(fromLocalDateTime, zoneId);
    return Date.from(zoneDateTime.toInstant());
  }

  /**
   * LocalDate型の値をDate型に変換して返します。
   *
   * @param localDate
   * @return
   */
  public static Date toDate(final LocalDate localDate) {
    ZoneId zoneId = getZoneId();
    Instant zoneDateTime = localDate.atStartOfDay(zoneId).toInstant();
    return Date.from(zoneDateTime);
  }

  protected static ZoneId getZoneId() {
    return ZoneId.systemDefault();
  }

  /**
   * システムで利用する現在日時を取得します。
   *
   * @return
   */
  public static LocalDateTime getNow() {
    return LocalDateTime.now();
  }

  /**
   * 引数は現在日時よりも後です。
   *
   * @param fromLocalDateTime
   * @return
   */
  public static boolean afterNow(final LocalDateTime fromLocalDateTime) {
    return fromLocalDateTime.isAfter(LocalDateTime.now());
  }

  /**
   * 引数は現在日時よりも前です。
   *
   * @param fromLocalDateTime
   * @return
   */
  public static boolean beforeNow(final LocalDateTime fromLocalDateTime) {
    return fromLocalDateTime.isBefore(LocalDateTime.now());
  }

  /**
   * time1の方が後の場合は正、time2がの方が後の場合は負の数を返却します。
   *
   * @param time1
   * @param time2
   * @return
   */
  public static int compareTo(final LocalDateTime time1, final LocalDateTime time2) {
    return time1.compareTo(time2);
  }

}
