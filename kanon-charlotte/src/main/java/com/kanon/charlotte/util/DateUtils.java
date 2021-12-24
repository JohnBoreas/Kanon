package com.kanon.charlotte.util;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class DateUtils {
    /**
     * 默认时间格式
     */
    public final static SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static Map<String, String> MONTH_MAP = new HashMap<String, String>();

    static {
        MONTH_MAP.put("JAN", "01");
        MONTH_MAP.put("FEB", "02");
        MONTH_MAP.put("MAR", "03");
        MONTH_MAP.put("APR", "04");
        MONTH_MAP.put("MAY", "05");
        MONTH_MAP.put("JUN", "06");
        MONTH_MAP.put("JUL", "07");
        MONTH_MAP.put("AUG", "08");
        MONTH_MAP.put("SEP", "09");
        MONTH_MAP.put("OCT", "10");
        MONTH_MAP.put("NOV", "11");
        MONTH_MAP.put("DEC", "12");
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取当前日期的周一日期
     */
    public static Date getFirstDayOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK);
        if (w == 1) w = 8;
        cal.add(Calendar.DATE, 2 - w);
        return cal.getTime();
    }


    /**
     * 获取beforeMinute分钟前(负数为之后)的时间的字符串 格式化为format
     *
     * @param date
     */
    public static String getBeforeMinuteTimeStr(Date date, int beforeMinute, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(getBeforeMinuteTime(date, beforeMinute));
    }

    public static String getBeforeMinuteTimeStr(Date date, int beforeMinute, String format, Locale local) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, local);
        return sdf.format(getBeforeMinuteTime(date, beforeMinute));
    }

    /**
     * 获取beforeMinute分钟前的时间时期对象
     *
     * @param date
     */
    public static Date getBeforeMinuteTime(Date date, int beforeMinute) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.MINUTE, 0 - beforeMinute);
        return cl.getTime();
    }

    /**
     * 获取指定格式的日期
     * example:
     * 将当前日期格式化成当天2点并以日期格式输出
     * DateUtil.getFormatDate(new Date(), "yyyy-MM-dd 02:00:00", "yyyy-MM-dd HH:mm:ss")
     *
     * @param date
     */
    public static Date getFormatDate(Date date, String inputFormat, String outputFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(inputFormat);
        try {
            String input = sdf.format(date);
            sdf.applyPattern(outputFormat);
            return sdf.parse(input);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * TODO
     * 获取年
     */
    public static int getNowYearString() {
        return new Date().getYear() + 1900;
    }

    /**
     * 根据字符串得到时间
     * format 需要格式化的字符串，如yyyy-MM-dd HH:mm:ss或者yyyy-MM-dd
     */
    public static Date getDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date c = sdf.parse(date);
            return c;
        } catch (ParseException e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 英文形式的日期字符串转Date
     * format 需要格式化的字符串，如:MMM dd,yyyy HH:mm:ss, 其中表示月份的最少要有3个M
     */
    public static Date getDateForEnglish(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        try {
            Date c = sdf.parse(date);
            return c;
        } catch (ParseException e) {
            log.error("", e);
        }
        return null;
    }

    public static boolean valid(String str, String formatPattern) {
        try {
            DateFormat formatter = new SimpleDateFormat(formatPattern);
            Date date = (Date) formatter.parse(str);
            return str.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据字符串得到GMT时间(两种格式 9 Dec 2011 01:38:41 GMT 和 Tue, 14 Jun 2011 03:17:24 GMT)
     */
    public static Date getGMTDate(String date) {
        String[] dates = date.split(" ");
        StringBuilder builder = new StringBuilder();
        int length = dates.length;
        if (length == 6 || length == 5) {
            builder.append(dates[3 - (6 - length)] + "-");
            builder.append(MONTH_MAP.get(dates[2 - (6 - length)].toUpperCase()) + "-");
            builder.append(dates[1 - (6 - length)] + " ");
            builder.append(dates[4 - (6 - length)]);
        }

        Date gmtDate = getDate(builder.toString(), "yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(gmtDate);
        // return new Date (gmtDate.getTime());
        calendar.add(Calendar.HOUR, 8);
        return calendar.getTime();
    }

    /**
     * 得到本月第一天
     */
    public static String getThisMonthFirstDay() {
        return getBeforeMinuteTimeStr(Calendar.getInstance().getTime(), 0, "yyyy-MM") + "-01";
    }

    /**
     * 得到上个月第一天
     *
     * @return
     */

    public static String getLastMonthFirstDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.add(Calendar.MONTH, -1);
        Date theDate = calendar.getTime();
        gcLast.setTime(theDate);
        calendar.set(GregorianCalendar.DATE, 1);

        String day_end_prevM = df.format(calendar.getTime());
        return day_end_prevM;

    }

    /**
     * 得到上个月最后一天
     *
     * @return
     */
    public static String getLastMonthLastDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(GregorianCalendar.DATE, 1);
        calendar.add(GregorianCalendar.DATE, -1);
        String day_end_prevM = df.format(calendar.getTime());
        return day_end_prevM;
    }

    /**
     * 得到指定日期的上number天或者后number
     *
     * @param date   指定时间
     * @param number 正数为后number天 负数向前number天
     * @return
     */
    public static String getLastDay(Date date, int number, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(GregorianCalendar.DATE, number);
        String day_end_prevM = df.format(calendar.getTime());
        return day_end_prevM;
    }

    /**
     * 秒数格式时间 转为 2010-08-18 00:00:00类型时间
     *
     * @return
     */
    public static String longToStringDate(long time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time * 1000));
        String day_end_prevM = df.format(calendar.getTime());
        return day_end_prevM;
    }

    /**
     * yyyy-MM-dd
     * @return
     */
    public static String currentDate() {
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // 获得格式化后的东八区时间
        String day = df.format(time.getTime());
        return day;
    }

    /**
     * 时间格式化2011-06-14 13:54:01为毫秒
     *
     * @return
     */
    public static Date getBeijingTime(String dateStr) {
        TimeZone timezone = TimeZone.getTimeZone("GMT+08:00");
        DEFAULT_SDF.setTimeZone(timezone);
        // 获得格式化后的东八区时间
        try {
            Date c = DEFAULT_SDF.parse(dateStr);
            return c;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
}
