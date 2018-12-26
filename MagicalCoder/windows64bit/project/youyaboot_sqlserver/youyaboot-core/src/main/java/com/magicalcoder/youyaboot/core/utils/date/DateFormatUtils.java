package com.magicalcoder.youyaboot.core.utils.date;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * qq:799374340 hdy 2013-4-17上午11:47:47
 */
public class DateFormatUtils {

/*	public static final SimpleDateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static SimpleDateFormat getDateFormat() {
		return df;
	}*/
	public static Date getDate(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(getStringDate(date,format));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Date getDate(String dateStr, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * @param year
	 * @param month 1-12
	 * @param day 1-31
	 * @return
	 */
	public static Date getDateDefined(int year, int month, int day) {
		month = month-1;
		Calendar calendar = Calendar.getInstance();
		// month，day格式不正确，默认取当天时间
		if (month >= 0 && month <= 11 && day >= 1 && day <= 31) {
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, month);
			calendar.set(Calendar.DATE, day);
		}
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		// calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	/** 获取自定义日期
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date getDateDefined(int year, int month, int day,int hour,int minute,int second) {
		month = month-1;
		Calendar calendar = Calendar.getInstance();
		// month，day格式不正确，默认取当天时间
		if (month >= 0 && month <= 11 && day >= 1 && day <= 31) {
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, month);
			calendar.set(Calendar.DATE, day);
		}
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		return calendar.getTime();
	}
	/**
	 * 返回当天 自定义时分秒
	 * @return
	 */
	public static Date getDateDefined(Date date, int hour, int minute,
			int second) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		return calendar.getTime();
	}
	/**
	 * 以当天零点零分零秒零毫秒格式返回date
	 * @param date
	 * @return
	 */
	public static Date getDateBegin(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		// calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	/**
	 * 以当天23点59分59秒格式返回date
	 * @param date
	 * @return
	 */
	public static Date getDateEnd(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	//int返回日期
	public static Date getDateEnd(int dateYmd){
		String dateStr = dateYmd+"235959";
		return getDate(dateStr,"yyyyMMddHHmmss");
	}
	//int返回日期
	public static Date getDateBegin(int dateYmd ){
		String dateStr = dateYmd+"000000";
		return getDate(dateStr,"yyyyMMddHHmmss");
	}
	public static String getStringDate(Date date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
	/** yyyy-MM-dd HH:mm:ss格式
	 * @param date
	 * @return
	 */
	public static String getStringDefaultDate(Date date) {
		if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
			return df.format(date);
		}
		return "";
	}
	
	/**获取年月日整形返回
	 * @param date
	 * @return
	 */
	public static int getIntDateYmd(Date date){
		if(date==null){
			date = new Date();
		}
		String dateStr = getStringDate(date, "yyyyMMdd");
		return Integer.valueOf(dateStr);
	}

	/**获取时分秒整形返回
	 * @param date
	 * @return
	 */
	public static int getIntDateHms(Date date){
		if(date==null){
			date = new Date();
		}
		String dateStr = getStringDate(date, "HHmmss");
		return Integer.valueOf(dateStr);
	}
	   /** 
     * 获取日期年份 
     * @param date 
     * @return 
     * @throws ParseException
     */
    public static int getIntYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取日期月份
     * @param date
     * @return
     * @throws ParseException
     */
    public static int getIntMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.get(Calendar.MONTH) + 1);
    }
	/**
	 * 获取日期天
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static int getIntDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return (calendar.get(Calendar.DAY_OF_MONTH));
	}
	/**
	 * 返回格式化的字符串
	 * @param date 11
	 * @param format 000000
	 * @return 000011
	 */
	public static String getFormatStr(int date,String format){
		DecimalFormat df = new DecimalFormat(format);
		return df.format(date);
	}
	 /**
     * 获取月份起始日期
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getMonthDateBegin(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取月份最后日期
     * @param date
     * @return
     * @throws ParseException
     */  
    public static Date getMonthDateEnd(Date date){  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        return calendar.getTime();  
    }

    /**
     * 获取本周周一
     * @param now
     * @return
     */
    public static Date getMonday(Date now){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        return calendar.getTime();
    }

    /**
     * 获取本周某天
     * @param now
     * @param calendayDay Calendar.MONDAY ...
     * @return
     */
    public static Date getCalendayDay(Date now,int calendayDay){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK,calendayDay);
        return calendar.getTime();
    }
    /**
     * 获取本周周日
     * @param now
     * @return
     */
    public static Date getSunday(Date now){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
        return calendar.getTime();
    }

/*    public static void main(String[] args) {
        Date now =
        DateFormatUtils.getMonday(DateFormatUtils.getDateBegin(20140320));
        Date monday = getMonday(now);
        Date sunday = getSunday(now);
        System.out.println(getIntDateYmd(monday));
        System.out.println(getIntDateYmd(sunday));
    }*/
}
