package com.example.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * 日期工具类
 *
 * @Author: dongqingyun
 * @Description:Created on 2018/11/29 14:28
 */

public class DateTools {


    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_HOUR_MINUTE = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_PATTERN2 = "yyyy-MM-dd";
    private static final String DEFAULT_PATTERN3 = "yyyyMMdd";
    private static final String DEFAULT_PATTERN4 = "yyyy-MM-dd HH:mm:ss.SSS";

    private static final String MONTH_DAY_HOUR_MINUTE = "yyyy-MM-dd HH:mm:ss";

    private static final String MONTH_DAY_HOUR_MINUTE_MDHM = "MM-dd HH:mm";

//    public static List<String> getMinuteArr3(Long startTime, Long endTime) {
//        return getMinuteArr2ByStep(startTime, endTime, 60);
//    }

    /**
     * long 2 str
     * 将时间戳 根据时间格式入参转为对应的时间格式字符串
     *
     * @param timestamp   时间戳
     * @param timePattern 日期格式
     * @return String
     */
    public static String longToTimeStr(long timestamp, String timePattern) {
        Date date = new Date(timestamp);
        SimpleDateFormat dateFormat = new SimpleDateFormat(timePattern);
        return dateFormat.format(date);
    }

    /**
     * 获取时间段内数据和时间跨度获取整分钟数据,格式为 yyyy-MM-dd HH:mm:ss
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param step      单位秒
     * @return java.util.List<java.lang.String>
     */
//    public static List<String> getMinuteArr2ByStep(Long startTime, Long endTime, Integer step) {
//        List<String> res = new ArrayList<>();
//        try {
//            if (startTime > endTime) {
//                return res;
//            }
//            res.add(addMinute2(startTime, 0));
//
//            while (startTime < (endTime - (Constants.NUM_60 * Constants.NUM_1000))) {
//                res.add(addMinute2(startTime, step / 60));
//                startTime = startTime + (step * 1000);
//            }
//            return res;
//
//        } catch (Exception e) {
//            return res;
//        }
//    }

    /**
     * 按照时间分钟增加n分钟,格式为yyyy-MM-dd HH:mm
     *
     * @param time 时间戳
     * @param n
     * @return java.lang.String
     */
    public static String addMinute2(Long time, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_HOUR_MINUTE);

            Calendar cd = Calendar.getInstance();
            cd.setTime(new Date(time));
            //增加一天
            cd.add(Calendar.MINUTE, n);
            return sdf.format(cd.getTime());

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * timestamp转date
     *
     * @param timeStamp timeStamp
     * @param pattern   转换格式
     * @return string类型日期
     */
    public static String dealTimeStampToString(Long timeStamp, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(timeStamp);
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param
     * @param //format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return String.valueOf(sdf.parse(date_str).getTime());
    }

    public static String date2Stamp(String date_str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.valueOf(sdf.parse(date_str).getTime());
    }

    /**
     * @param currentTime 传入时间
     * @param time        计算当前时间的指定前几天天数，并以毫秒级的时间戳形式返回
     * @return
     */
    public static String getPreviousDayTime(String currentTime, Integer time) {
        if (time == 1) {
            long timestamp = Long.parseLong(currentTime);
            long previousDayTime = timestamp - 24 * 60 * 60 * 1000;
            return String.valueOf(previousDayTime);
        }
        if (time == 7) {
            long timestamp = Long.parseLong(currentTime);
            long previousDayTime = timestamp - 7 * 24 * 60 * 60 * 1000;
            return String.valueOf(previousDayTime);
        }
        return null;
    }

    /**
     * 　* @Description: 时间戳转Data
     * 　* @Param [date_str]
     * 　* @Return java.util.Date
     * 　* @Throws
     */
    public static Date dateStamp(String date_str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(date_str);
        return date;
    }


    /**
     * 处理2016-09-03T00:00:00.000+08:00时间格式
     *
     * @param
     * @param //format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String dealDate(String oldDateStr) throws ParseException {
        if (StringUtils.isEmpty(oldDateStr)) {
            return oldDateStr;
        } else {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = df.parse(oldDateStr);
            SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            Date date1 = df1.parse(date.toString());
            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df2.format(date1);
        }
    }

    /**
     * @Description:获取当前系统时间
     * @Param: []
     * @return: java.lang.String
     * @Author: dongqingyun
     * @Date: 2019/5/20 19:47
     */
    public static String currentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = df.format(new Date());
        return format;
    }

    /**
     * @Description:获取当前系统时间的前10分钟的时间点
     * @Param: []
     * @return: java.lang.String
     * @Author: dongqingyun
     * @Date: 2019/5/20 19:47
     */
    public static String beforeTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -10);// 3分钟之前的时间
        Date beforeD = beforeTime.getTime();
        String time = sdf.format(beforeD);
        return time;
    }

    /**
     * 　* @Description: 获取当前系统时间的前一天的时间点
     * 　* @Param []
     * 　* @Return java.lang.String
     * 　* @Throws
     * 　* @Date 2019/8/23 12:12
     */
    public static String beforesTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.DAY_OF_MONTH, -1);// 1天之前的时间
        Date beforeD = beforeTime.getTime();
        String time = sdf.format(beforeD);
        return time;
    }

    /**
     * 　* @Description: 获取当前系统时间的前三天的时间点
     * 　* @Param []
     * 　* @Return java.lang.String
     * 　* @Throws
     * 　* @Date 2019/8/23 12:12
     */
    public static String beforeThreeTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.DAY_OF_MONTH, -3);// 1天之前的时间
        Date beforeD = beforeTime.getTime();
        String time = sdf.format(beforeD);
        return time;
    }

    /**
     * 　* @Description: 获取当天
     * 　* @Param []
     * 　* @Return java.lang.String
     * 　* @Throws
     * 　* @Date 2019/8/22 17:45
     */
    public static String currentDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(new Date());
        return format;
    }

    /**
     * 　* @Description: 获取前一天
     * 　* @Param []
     * 　* @Return java.lang.String
     * 　* @Throws
     * 　* @Date 2019/8/22 17:47
     */
    public static String beforeDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.DAY_OF_MONTH, -1);
        Date beforeD = beforeTime.getTime();
        String time = sdf.format(beforeD);
        return time;
    }

    /**
     * 　* @Description: 获取指定时间的当天
     * 　* @Param []
     * 　* @Return java.lang.String
     * 　* @Throws
     * 　* @Date 2019/8/22 17:45
     */
    public static String currentDays(String endTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(endTime);
        String format = sdf.format(new Date());
        return format;
    }

    /**
     * 　* @Description: 获取前三天
     * 　* @Param []
     * 　* @Return java.lang.String
     * 　* @Throws
     * 　* @Date 2019/8/22 17:47
     */
    public static String beforeThreeDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.DAY_OF_MONTH, -3);
        Date beforeD = beforeTime.getTime();
        String time = sdf.format(beforeD);
        return time;
    }
    /**
     * 　* @Description: 获取指定时间的前一天
     * 　* @Param [endTime]
     * 　* @Return java.lang.String
     * 　* @Throws
     * 　* @Date 2019/8/22 17:49
     */
    public static String beforeDays(String endTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(endTime);
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.setTime(date);
        beforeTime.add(Calendar.DAY_OF_MONTH, -1);
        Date beforeD = beforeTime.getTime();
        String time = sdf.format(beforeD);
        return time;
    }

    /**
     * 　* @Description: 获得某天最小时间
     * 　* @Param [date]
     * 　* @Return java.util.Date
     * 　* @Throws
     * 　* @Date 2019/8/23 12:06
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    /**
     * 　* @Description: 获得指定日期的当天
     * 　* @Param [specifiedDay]
     * 　* @Return java.lang.String
     * 　* @Throws
     * 　* @Date 2019/8/30 11:05
     */
    public static String getSpecifiedDay(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 0);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param
     * @return
     */
    public static String timeStamp2Date(String seconds) {
        String format = DEFAULT_PATTERN;
        if (seconds == null || seconds.isEmpty() || "null".equals(seconds)) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    /**
     * 根据传入时间转换获取CK分区时间
     *
     * @param seconds
     * @return
     */
    public static String timeStampGangDate(String seconds) {
        if (seconds == null || seconds.isEmpty() || "null".equals(seconds)) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN2);
        return sdf.format(new Date(Long.parseLong(seconds)));
    }

    /**
     * 时间戳转为yyyy-MM-dd日期
     *
     * @param seconds
     * @return
     */
    public static String timeStampGangDate(Long seconds) {
        if (seconds == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN2);
        return sdf.format(new Date(seconds));
    }

    /**
     * 获取当前时间
     */
    public static String getCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat(DEFAULT_PATTERN2);//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        return date;
    }


    public static String timeStampNoGangDate(String seconds) {
        String format = DEFAULT_PATTERN3;
        if (seconds == null || seconds.isEmpty() || "null".equals(seconds)) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyyMMdd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    public static String timeStampHaoMiaoDate(String seconds) {
        String format = DEFAULT_PATTERN4;
        if (seconds == null || seconds.isEmpty() || "null".equals(seconds)) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss.SSS";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    public static String simpleConvert(Long date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }


    /**
     * Long 格式化
     */
    public static Long formatByLong(String pattern, Long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Long timetimeLong = 0L;
        try {
            timetimeLong = simpleDateFormat.parse(simpleConvert(date, pattern)).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timetimeLong;
    }


    /**
     * 跨天入参格式化
     *
     * @param jsonObject
     * @param pattern
     */
    public static void formatAcrossDay(JSONObject jsonObject, String pattern) {
//            String date=simpleConvert(jsonObject.getLong("startTime"),pattern);
//            String endDate=simpleConvert(jsonObject.getLong("endTime"),pattern);
//            jsonObject.put("date",date);
//            jsonObject.put("is_across","0");
//            if(!date.equals(endDate)){
//                jsonObject.put("is_across","1");
//                jsonObject.put("endDate",endDate);
//            }
        String date = simpleConvert(jsonObject.getLong("startTime"), pattern);
        String endDate = simpleConvert(jsonObject.getLong("endTime"), pattern);
        String startHourTime = simpleConvert(jsonObject.getLong("startTime"), "yyyy-MM-dd HH:mm");
        String endHourTime = simpleConvert(jsonObject.getLong("endTime"), "yyyy-MM-dd HH:mm");
        String startHour = startHourTime.substring(0, 13) + ":00:00";
        String endHour = endHourTime.substring(0, 13) + ":00:00";
        jsonObject.put("date", date);
        jsonObject.put("is_across", "0");
        jsonObject.put("startHour", startHour);
        jsonObject.put("is_houracross", "0");
        if (!date.equals(endDate)) {
            jsonObject.put("is_across", "1");
            jsonObject.put("endDate", endDate);
        }
        if (!startHour.equals(endHour)) {
            jsonObject.put("is_houracross", "1");
            jsonObject.put("endHour", endHour);
        }

    }

    /**
     * 获取时间段内数据,格式为 MM-dd HH-mm
     *
     * @param startTime 整分钟
     * @param endTime
     * @return java.util.List<java.lang.String>
     */
    public static List<String> getMinuteArr(Long startTime, Long endTime) {
        List<String> res = new ArrayList<>();
        try {
            if (startTime > endTime) {
                return res;
            }
            res.add(addMinute(startTime, 0));
            while (startTime < (endTime - (60 * 1000))) {
                res.add(addMinute(startTime, 1));
                startTime = startTime + (60 * 1000);
            }
            return res;

        } catch (Exception e) {
            return res;
        }
    }

    /**
     * 按照时间分钟增加n分钟,格式为MM-dd HH-mm
     *
     * @param time 时间戳
     * @param n
     * @return java.lang.String
     */
    public static String addMinute1(Long time, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(MONTH_DAY_HOUR_MINUTE);

            Calendar cd = Calendar.getInstance();
            cd.setTime(new Date(time));
            //增加一天
            cd.add(Calendar.MINUTE, n);
            return sdf.format(cd.getTime());

        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 按照时间分钟增加n分钟,格式为MM-dd HH-mm
     *
     * @param time 时间戳
     * @param n
     * @return java.lang.String
     */
    public static String addMinute(Long time, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(MONTH_DAY_HOUR_MINUTE_MDHM);

            Calendar cd = Calendar.getInstance();
            cd.setTime(new Date(time));
            //增加一天
            cd.add(Calendar.MINUTE, n);
            return sdf.format(cd.getTime());

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取日期
     *
     * @return
     */
    public static String formatTime(boolean flag) {
        LocalDate today = LocalDate.now();

        if (flag) {
            return today.toString();
        } else {
            LocalDate tenYear = today.plus(10, ChronoUnit.YEARS);
            return tenYear.toString();
        }
    }

    /**
     * 　* @Description: 获取当前系统时间的之前的时间点
     * 　* @Param []
     * 　* @Return java.lang.String
     * 　* @Throws
     * 　* @Date 2019/8/23 12:12
     */
    public static String beforesDateTime(int monthNum) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN2);
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MONTH, monthNum);
        Date beforeD = beforeTime.getTime();
        String time = sdf.format(beforeD);
        return time;
    }

    /**
     * 根据时间戳获取时间
     *
     * @param time
     * @return java.lang.String
     */
    public static String timestampFortime(Long time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(MONTH_DAY_HOUR_MINUTE);
            return sdf.format(time);

        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 三个小时以下一分钟,3-6小时5分钟,6小时以上10分钟
     *
     * @param startTime
     * @param endTime
     * @return
     */
//    public static Integer timestepCalculate(Long startTime, Long endTime) {
//        int timeStep = (endTime - startTime) >= 3 * 60 * 60 * 1000 ? 300 : 60;
//        if (timeStep == Constants.NUM_300) {
//            timeStep = (endTime - startTime) >= 6 * 60 * 60 * 1000 ? 600 : 300;
//        }
//        return timeStep;
//    }

    /**
     * 根据时间跨度获取最近整分钟数据 如step 300   8：48 会获取8：45, 格式为 MM-dd HH-mm
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param step      单位秒
     * @return java.util.List<java.lang.String>
     */
    public static List<String> getNearMinuteArrByStep(Long startTime, Long endTime, Integer step) {
        startTime = startTime - (startTime % (step * 1000));
        return getMinuteArrByStep(startTime, endTime, step);
    }

    /**
     * 跨天入参格式化 西咸使用的hour分区，特殊处理
     *
     * @param jsonObject
     * @param pattern
     */
    public static void formatAcrossDayNew(JSONObject jsonObject, String pattern) {
        Long startTime = jsonObject.getLong("startTime");
        Long endTime = jsonObject.getLong("endTime");
        startTime = startTime / 1000 / 60 / 60 * 60 * 60 * 1000;
        endTime = endTime / 1000 / 60 / 60 * 60 * 60 * 1000 + 60 * 60 * 1000;
        String date = simpleConvert(startTime, pattern);
        String endDate = simpleConvert(endTime, pattern);
        jsonObject.put("startTime", startTime);
        jsonObject.put("endTime", endTime);
        jsonObject.put("hour", date);
        if (!date.equals(endDate)) {
            jsonObject.put("endHour", endDate);
        }

    }


    /**
     * 获取时间段内数据和时间跨度获取整分钟数据,格式为 MM-dd HH-mm
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param step      单位秒
     * @return java.util.List<java.lang.String>
     */
//    public static List<String> getMinuteArrByStep(Long startTime, Long endTime, Integer step) {
//        List<String> res = new ArrayList<>();
//        try {
//            if (startTime > endTime) {
//                return res;
//            }
//            res.add(addMinute(startTime, 0));
//
//            while (startTime < (endTime - Constants.NUM_60 )) {
//                res.add(addMinute(startTime, step / 60));
//                startTime = startTime + (step * 1000);
//            }
//            return res;
//
//        } catch (Exception e) {
//            return res;
//        }
//    }

    /**
     * 获取时间段内数据和时间跨度获取整分钟数据,格式为 MM-dd HH-mm
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param step      单位秒
     * @return java.util.List<java.lang.String>
     */
    public static List<String> getMinuteArrByStep(Long startTime, Long endTime, Integer step) {

        Instant instant = Instant.ofEpochMilli(endTime);
        Instant newInstant = instant.minus(Duration.ofMinutes(1));
        long newEndTime = newInstant.toEpochMilli();
//        System.out.println("原始时间戳：" + endTime);
//        System.out.println("减去一分钟后的时间戳：" + newEndTime);


        List<String> res = new ArrayList<>();
        try {
            if (startTime >= endTime) { // 修改条件为大于等于
                return res;
            }
            res.add(addMinute(startTime, 0));

            while (startTime < newEndTime) { // 修改条件为小于
                res.add(addMinute(startTime, step / 60));
//                System.out.println("处理过程时间"+addMinute(startTime, step / 60));
                startTime = startTime + (step * 1000);

            }

//            for (String s : res) {
//                System.out.println("工具类时间 ： " + s);
//            }
            return res;

        } catch (Exception e) {
            return res;
        }
    }
//
//    public static List<Long> bulildIntervalByLong(Long sTime, Long endTime, long intervalMilliSecond) {
//        List<Long> list = Lists.newArrayList();
//        while (sTime < endTime) {
//            list.add(sTime);
//            sTime += intervalMilliSecond;
//        }
//        return list;
//    }

    /**
     * @param startTime startTime
     * @param endTime   endTime
     * @return xArr
     */
    public static List<String> getXarrByStartEndTime(Long startTime, Long endTime) {
        List<Long> xArr = new ArrayList<>();
        List<String> result = new ArrayList<>();

        long differ = endTime - startTime;
        // 小于等于2天
        if (differ <= 172800000) {
            while (startTime < endTime) {
                xArr.add(startTime);
                startTime += 60000;
            }
            // 转换字符串时间
            xArr.forEach(x -> result.add(dealTimeStampToString(x, "yyyy-MM-dd HH:mm")));

        }
        // 大于2天 小于等于12天
        if (differ > 172800000 && differ <= 1036800000) {
            while (startTime < endTime + 3600000) {
                xArr.add(startTime);
                startTime += 3600000;
            }
            // 转换字符串时间
            xArr.forEach(x -> result.add(dealTimeStampToString(x, "yyyy-MM-dd HH:00")));
        }
        // 大于12天
        if (differ > 1036800000) {
            while (startTime < endTime + 86400000) {
                xArr.add(startTime);
                startTime += 86400000;
            }
            // 转换字符串时间
            xArr.forEach(x -> result.add(dealTimeStampToString(x, "yyyy-MM-dd")));
        }

        return result;
    }


    /**
     * @param startTime startTime
     * @param endTime   endTime
     * @return xArr
     */
    public static List<String> getXarrMMdd(Long startTime, Long endTime) {
        List<Long> xArr = new ArrayList<>();
        List<String> result = new ArrayList<>();

        long differ = endTime - startTime;
        // 小于等于2天
        if (differ <= 172800000) {
            while (startTime < endTime) {
                xArr.add(startTime);
                startTime += 60000;
            }
            // 转换字符串时间
            xArr.forEach(x -> result.add(dealTimeStampToString(x, "MM-dd HH:mm")));

        }
        // 大于2天 小于等于12天
        if (differ > 172800000 && differ <= 1036800000) {
            while (startTime < endTime + 3600000) {
                xArr.add(startTime);
                startTime += 3600000;
            }
            // 转换字符串时间
            xArr.forEach(x -> result.add(dealTimeStampToString(x, "MM-dd HH:00")));
        }
        // 大于12天
        if (differ > 1036800000) {
            while (startTime < endTime + 86400000) {
                xArr.add(startTime);
                startTime += 86400000;
            }
            // 转换字符串时间
            xArr.forEach(x -> result.add(dealTimeStampToString(x, "MM-dd")));
        }

        return result;
    }


    /**
     * 添加查询分布图条件
     */
    public static String addQueryCondition(String startTime, String endTime) {
        String time = "%m-%d %H:%M";
        long differ = Long.parseLong(startTime) - Long.parseLong(endTime);
        // 小于等于2天，分钟刻度
        // 大于2天，小于等于12天，小时刻度
        if (differ > 172800000 && differ <= 1036800000) {
            time = "%m-%d %H:00";
        }
        // 大于12天，天刻度
        if (differ > 1036800000) {
            time = "%m-%d";
        }
        return time;
    }

    public static String convertTimeAccFormat(long startTime, long endTime) {
        long differ = endTime - startTime;
        if (differ <= 172800000) {
            // 小于等于2天，分钟刻度
            return "%Y-%m-%d %H:%M";
        } else if (differ > 172800000 && differ <= 1036800000) {
            // 大于2天，小于等于12天，小时刻度
            return "%Y-%m-%d %H:00";
        } else {
            // 大于12天，天刻度
            return "%Y-%m-%d";
        }
    }

    //时间戳转换格式，用于下载execl的名称
    public static String outTimeForExecl(String timeStr) {
        // 将字符串时间戳转换为long
        long time = Long.parseLong(timeStr);
        // 处理时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date(time);
        // 格式化并返回
        return sdf.format(date);
    }
    public static String manipulateTimestamp(String timestampString, int flag) {
        try {
            // 将传入的字符串时间戳解析为Date对象
            long timestamp = Long.parseLong(timestampString);
            Date date = new Date(timestamp);

            // 根据flag的值进行时间调整
            if (flag == -1) {
                // 减去一个小时
                date.setTime(date.getTime() - 3600000); // 1小时 = 60分钟 * 60秒 * 1000毫秒
            } else if (flag == 1) {
                // 加上一个小时
                date.setTime(date.getTime() + 3600000);
            }

            // 将调整后的时间转换为字符串
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String manipulatedTime = dateFormat.format(date);
            long time = date.getTime();
            // 返回调整后的时间戳和格式化后的时间
            return String.valueOf(time);
        } catch (NumberFormatException e) {
            // 处理传入的时间戳不合法的情况
            return "Invalid timestamp format";
        }
    }

    public static void main(String[] args) {
//        // 获取当前时间
//        Date currentTime = new Date();
//        // 格式化时间显示，如果需要的话
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String formattedTime = sdf.format(currentTime);
//        // 输出当前时间和格式化后的时间
//        System.out.println("当前时间（格式化后）：" + formattedTime);
//
//
//        System.out.println("----------------------------");
//        String timestamp = "1677620300000";
//        String s = outTimeForExecl(timestamp);
//        System.out.println(s);

        String i= "1705471080000";
        String s = (manipulateTimestamp(i,1));
        System.out.println(s);
    }

}
