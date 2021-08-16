


package com.ml.timi.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * ClassName:      DateTool
 * Description:    处理日期的工具类
 * Date:           2021 2021/7/16 8:25
 * Author:         Lin
 * Copyright:      Lin
 */
public class DateTool {

    /** 计算时间单位到年 */
    public  static final String UNIT_YEARS = "YEARS";
    /** 计算时间单位到小时 */
    public  static final String UNIT_HOURS = "HOURS";

    static DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    static DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * LocalDateTime 转 String 年月日 时分秒
     */
    public static String dateTimeString(LocalDateTime localDateTime) {

        return dateTime.format(localDateTime);
    }

    /**
     * LocalDateTime 转 String 年月日
     */
    public static String dateString(LocalDateTime localDateTime) {

        return date.format(localDateTime);
    }

    /**
     * LocalDateTime 转 String 时分秒
     */
    public static String timeString(LocalDateTime localDateTime) {

        return time.format(localDateTime);
    }

    /**
     * String 转 localDateTime 年月日 时分秒
     */
    public static LocalDateTime toDateTime(String localDateTime) {

        return LocalDateTime.parse(localDateTime, dateTime);
    }

    /**
     * String 转 localDate 年月日
     */
    public static LocalDate toDate(String localDate) {

        return LocalDate.parse(localDate, date);
    }

    /**
     * String 转 localTime 时分秒
     */
    public static LocalTime toTime(String localTime) {

        return LocalTime.parse(localTime, time);
    }

    /**
     * Method               TimeCalculation
     * Description          时间差计算
     * Author               Lin
     * Date                 2021/7/16 8:26
     * Version              1.0.0
     * @param               startLocalDateTime  开始时间
     * @param               endLocalDateTime    结束时间
     * @param               unit                单位
     * @return java.lang.String
     */
    public static String TimeCalculation(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime, String unit) {
        LocalDateTime tempDateTime = LocalDateTime.from(startLocalDateTime);

        if (unit.equals(UNIT_YEARS)) {
            long years = tempDateTime.until(endLocalDateTime, ChronoUnit.YEARS);
            tempDateTime = tempDateTime.plusYears(years);

            long months = tempDateTime.until(endLocalDateTime, ChronoUnit.MONTHS);
            tempDateTime = tempDateTime.plusMonths(months);

            long days = tempDateTime.until(endLocalDateTime, ChronoUnit.DAYS);
            tempDateTime = tempDateTime.plusDays(days);

            long hours = tempDateTime.until(endLocalDateTime, ChronoUnit.HOURS);
            tempDateTime = tempDateTime.plusHours(hours);

            long minutes = tempDateTime.until(endLocalDateTime, ChronoUnit.MINUTES);
            tempDateTime = tempDateTime.plusMinutes(minutes);

            long seconds = tempDateTime.until(endLocalDateTime, ChronoUnit.SECONDS);
            tempDateTime = tempDateTime.plusSeconds(seconds);

            long NANOS = tempDateTime.until(endLocalDateTime, ChronoUnit.NANOS);
            return "[" +years + " 年]  " +
                    "[" +months + " 月]  " +
                    "[" +days + " 天]  " +
                    "[" +hours + " 时]  " +
                    "[" +minutes + " 分]  " +
                    "[" +seconds + " 秒]  " +
                    "[" +NANOS + " 纳秒]  ";
        } else if (unit.equals(UNIT_HOURS)) {
            long hours = tempDateTime.until(endLocalDateTime, ChronoUnit.HOURS);
            tempDateTime = tempDateTime.plusHours(hours);

            long minutes = tempDateTime.until(endLocalDateTime, ChronoUnit.MINUTES);
            tempDateTime = tempDateTime.plusMinutes(minutes);

            long seconds = tempDateTime.until(endLocalDateTime, ChronoUnit.SECONDS);
            tempDateTime = tempDateTime.plusSeconds(seconds);

            long NANOS = tempDateTime.until(endLocalDateTime, ChronoUnit.NANOS);
            return "[" + hours + " 时]  " +"[" + minutes + " 分]  " +"[" + seconds + " 秒]" +"[" + NANOS + " 纳秒]";
        }
        return "null";
    }
}
