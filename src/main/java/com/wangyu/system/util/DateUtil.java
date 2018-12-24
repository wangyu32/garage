package com.wangyu.system.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description
 * @Author wangyu
 * @Date 2018/12/24 21:46
 */
public class DateUtil {

    /**
     *  获得当前时间
     *  @param format 格式为：yyyy-MM-dd HH:mm:ss/yyyyMMddHHmmss
     *  @return format
     */
    public static String getNowTime(String format) {
        Date nowday = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);// 精确到秒
        String time = sdf.format(nowday);
        return time;
    }
    /**
     * 获得当前时间
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getNowTimeFormat() {
        return getNowTime("yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 获取当前系统时间戳
     * @return
     */
    public static Long getNowTimeStamp() {
        return System.currentTimeMillis();
    }
    /**
     * 将时间字符转成时间戳
     * @param timestring
     * @return
     */
    public static Long getTime(String timestring) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 精确到秒
        Date date = sdf.parse(timestring);
        return date.getTime()/1000;
    }
    /**
     * 将时间戳转成时间字符
     * @param timestamp 秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 精确到秒
        Date date = new Date(timestamp*1000);
        return sdf.format(date);
    }
    /**
     * 获取当前时间增减后的时间
     * @param duration
     * @param format 'm/M'=分钟 'h/H'=小时 's/S'=秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String addFormatTime(Integer duration,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 精确到秒

        Calendar calendar = Calendar.getInstance();
        if(format.toLowerCase().equals("m")){
            calendar.add(Calendar.MINUTE, duration);
        }else if(format.toLowerCase().equals("h")){
            calendar.add(Calendar.HOUR, duration);
        }else if(format.toLowerCase().equals("s")){
            calendar.add(Calendar.SECOND, duration);
        }
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取查询日期增减n天/n月/n年后的日期
     * @param dateStr - yyyy-MM-dd格式日期（多由日期控件产生）
     * @param offset - 增减n天/n月/n年数
     * @param type - 类型 d-日，m-月, y-年
     * @return yyyy-MM-dd格式日期
     * @throws Exception
     */
    public final static String addDateStr(String dateStr, Integer offset, String type){
        Date date = null;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        if(type == null || type.trim().equals("")){
            type = "d";
        }
        try {
            if (type.toLowerCase().equals("d")) {
                date = sf.parse(dateStr);
                date.setDate(date.getDate() + offset);
            } else if (type.toLowerCase().equals("m")) {
                date = sf.parse(dateStr);
                date.setMonth(date.getMonth() + offset);
            } else if (type.toLowerCase().equals("y")) {
                date = sf.parse(dateStr);
                date.setYear(date.getYear() + offset);
            } else {
                throw new RuntimeException("date type error !");
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return sf.format(date);
    }

    /**
     * 获取查询日期增减n天后的日期
     * @param dateStr - yyyy-MM-dd格式日期（多由日期控件产生）
     * @param offset - 增减n天数
     * @return yyyy-MM-dd格式日期
     * @throws Exception
     */
    public final static String addDateStr(String dateStr, Integer offset) {
        return addDateStr(dateStr, offset, null);
    }

    /**
     * 生成两个日期之间的每一天日期
     * (Java1.8版本以上)
     * @param startTime 开始日期 yyyy-MM-dd格式日期
     * @param endTime  结束日期 yyyy-MM-dd格式日期
     * @return List<String>    返回类型
     * @author renchenyang
     */
    public static List<String> generatesDatesBetweenTwoDates(String startTime, String endTime) {
        LocalDate start = LocalDate.parse(startTime);
        LocalDate end = LocalDate.parse(endTime);
        // 用起始时间作为流的源头，按照每次加一天的方式创建一个无限流
        return Stream.iterate(start, localDate -> localDate.plusDays(1))
                // 截断无限流，长度为起始时间和结束时间的差+1个
                .limit(ChronoUnit.DAYS.between(start, end) + 1)
                // 由于最后要的是字符串，所以map转换一下
                .map(LocalDate::toString)
                // 把流收集为List
                .collect(Collectors.toList());
    }
}
