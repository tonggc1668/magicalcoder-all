package com.magicalcoder.youyaboot.core.utils;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间相关转换类
 * 
 * @author
 * 
 */
public class TimeUtil {
	public static final int SECOND = 0;
	public static final int MINUTE = 1;
	public static final int HOUR = 2;
	public static final int DAY = 3;
	public static final int MONTH = 4;
	
	/**
	 * 计算两个Date时刻的时间差,用指定格式(~天~小时~分钟 )字符串表示
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static String getDateTimeDelta(Date beginDate, Date endDate) {
		long delta = getDateTimeMillisDelta(beginDate, endDate);
		return convertMillisToTargetFormat(delta);
	}
	
	/**
	 * 计算两个时刻的时间差的毫秒数
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static long getDateTimeMillisDelta(Date beginDate, Date endDate) {
		Calendar _begin = Calendar.getInstance();
		_begin.setTime(beginDate);
		Calendar _end = Calendar.getInstance();
		_end.setTime(endDate);
		long delta = (_end.getTimeInMillis() - _begin.getTimeInMillis());
		return delta;
	}
	
	/**
	 * 计算两个时刻的时间差的毫秒数
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public static long getDateTimeMillisDelta(String begin, String end) {
		Date beginDate = convertStringToDate(begin, "yyyy-MM-dd HH:mm:ss");
		Date endDate = convertStringToDate(end, "yyyy-MM-dd HH:mm:ss");
		return getDateTimeMillisDelta(beginDate, endDate);
	}
	
	/**
	 * 将时间差(毫秒)转化为指定格式(e.g.~天~时~分  DD:HH:MM)字符串表示
	 * 
	 * @param millseconds
	 * @return
	 */
	public static String convertMillisToTargetFormat(Long millseconds) {
		if (millseconds == null) {
			return "";
		}
		
		String showtime = "";
		long days = (long) millseconds.longValue() / 1000 / 60 / 60 / 24;
		if (days >= 0) {
			showtime = showtime + days + "天";
		}
		long hourMillseconds = millseconds.longValue() - days * 1000 * 60 * 60 * 24;
		long hours = (long) hourMillseconds / 1000 / 60 / 60;
		if (hours >= 0) {
			showtime = showtime + hours + "小时";
		}
		long minuteMillseconds = millseconds.longValue() - days * 1000 * 60 * 60 * 24 - hours * 1000 * 60 * 60;
		long minutes = (long) minuteMillseconds / 1000 / 60;
		if (minutes >= 0) {
			showtime = showtime + minutes + "分钟";
		}
		return showtime;
	}

	/**
	 * 根据类型将String转换为Date
	 * 
	 * @param value
	 * @param format
	 *            样式：yyyy-MM-dd HH:mm:ss / yyyyMMdd 等等。。。
	 * @return
	 */
	public static Date convertString(String value, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (value == null)
			return null;
		try {
			return sdf.parse(value);
		} catch (Exception e) {
		}
		return null;
	}

	public static String convertDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (date == null)
			return "";
		try {
			return sdf.format(date);
		} catch (Exception e) {
		}
		return "";
	}

	/*
	 * public static Date toLocalDate(Date oldDate){ //String
	 * dateStr=oldDate.toString(); return
	 * convertString(TimeUtil.getDateString(oldDate)+" 00:00:00"); }
	 */

	/**
	 * 获取现在时间
	 * 
	 * @throws ParseException
	 *
	 * @return返回短时间格式 yyyy-MM-dd
	 */
	public static Date getNowDateShort() throws ParseException {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	/**
	 * 转换为yyyy-MM-dd HH:mm:ss样式的时间
	 * 
	 * @param value
	 * @return
	 */
	public static Date convertString(String value) {
		return convertString(value, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获得当前时间（格式：yyyy-MM-dd HH:mm:ss）
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return formatter.format(date);
	}

	/**
	 * 秒转换成"yyyy-MM-dd HH:mm:ss"的格式
	 * 
	 * @param millseconds
	 * @return
	 */
	public static String getDateTimeString(long millseconds) {
		return getDate(millseconds, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 秒转换成自定义formateStr的格式
	 * 
	 * @param millseconds
	 * @return
	 */
	public static String getDateTimeString(long millseconds,String formateStr) {
		return getDate(millseconds, formateStr);
	}

	/**
	 * 秒转换成特定格式的yyyyMMdd的字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String getDayDate(long time) {
		return getDate(time, "yyyyMMdd");
	}

	/**
	 * 秒转换为时间
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static String getDate(long time, String format) {
		SimpleDateFormat formater = new SimpleDateFormat(format);
		return formater.format(new Date(time));
	}

	/**
	 * 根据Date得到yyyy-MM-dd HH:mm:ss格式的字符串时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateTimeString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 根据Date获得当前时间时间字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDateTimeString(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 判断endTime大于startTime
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean isTimeLarge(String startTime, String endTime) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			ParsePosition pos1 = new ParsePosition(0);
			Date dt1 = formatter.parse(startTime, pos);
			Date dt2 = formatter.parse(endTime, pos1);
			long lDiff = dt2.getTime() - dt1.getTime();
			return lDiff > 0L;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 比较date2 是否大于date1 是返回true
	 *
	 */
	public static Boolean isDateLarge(Date date1, Date date2) {
		String startTime = getDateTimeString(date1);
		String endTime = getDateTimeString(date2);
		return isTimeLarge(startTime, endTime);
	}

	/**
	 * 获得两个时间的时间差(秒)
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static long getTime(Date startTime, Date endTime) {
		return endTime.getTime() - startTime.getTime();
	}

	/**
	 * 获得两个时间的标准时间差（天，小时，分钟）
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getTimeDiff(String startTime, String endTime) {
		try {
			String tmp = "";
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			ParsePosition pos = new ParsePosition(0);
			ParsePosition pos1 = new ParsePosition(0);
			Date dt1 = formatter.parse(startTime, pos);
			Date dt2 = formatter.parse(endTime, pos1);
			long lDiff = dt2.getTime() - dt1.getTime();
			int days = (int) (lDiff / 86400000L);
			if (days > 0) {
				lDiff -= days * 1000 * 60 * 60 * 24;
				tmp = tmp + days + "天";
			}
			int hours = (int) (lDiff / 3600000L);
			if (hours > 0)
				lDiff -= hours * 1000 * 60 * 60;
			tmp = tmp + hours + "小时";
			int minute = (int) (lDiff / 60000L);
			tmp = tmp + minute + "分钟";
			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "-1";
	}

	/**
	 * 根据秒获得，天，小时，分钟的格式串
	 * 
	 * @param millseconds
	 * @return
	 */
	public static String getTime(Long millseconds) {
		String time = "";
		if (millseconds == null) {
			return "";
		}
		int days = (int) millseconds.longValue() / 1000 / 60 / 60 / 24;
		if (days > 0) {
			time = time + days + "天";
		}
		long hourMillseconds = millseconds.longValue() - days * 1000 * 60 * 60 * 24;
		int hours = (int) hourMillseconds / 1000 / 60 / 60;
		if (hours > 0) {
			time = time + hours + "小时";
		}
		long minuteMillseconds = millseconds.longValue() - days * 1000 * 60 * 60 * 24 - hours * 1000 * 60 * 60;
		int minutes = (int) minuteMillseconds / 1000 / 60;
		if (minutes > 0) {
			time = time + minutes + "分钟";
		}
		return time;
	}

	/**
	 * 获得yyyy-MM-dd格式的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date) {
		if (date != null) {
			return new SimpleDateFormat("yyyy-MM-dd").format(date);
		}
		return "";
	}

	/**
	 * 获得当前时间yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return formatter.format(date);
	}

	/**
	 * 根据秒获得yyyy-MM-dd的时间串
	 * 
	 * @param millseconds
	 * @return
	 */
	public static String getDateString(long millseconds) {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date(millseconds));
	}

	public static String getDateString(String formater) {
		return new SimpleDateFormat(formater).format(new Date());
	}

	/**
	 * 根据当前时间，获得秒的数值
	 * 
	 * @return
	 */
	public static long getMillsByToday() {
		String str = getDateString("yyyy-MM-dd");
		str = String.valueOf(getMillsByDateString(str));
		return Long.parseLong(str.substring(0, str.length() - 3) + "000");
	}

	/**
	 * 获得当前下一天秒的数值
	 * 
	 * @param days
	 * @return
	 */
	public static long getNextDays(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(5, days);
		String str = String.valueOf(cal.getTimeInMillis());
		return Long.parseLong(str.substring(0, str.length() - 3) + "000");
	}

	/**
	 * 获得下 days天的long型秒的数值
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getNextDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(5, days);
		return cal.getTime();
	}

	/**
	 * 根据标准的日期串获得秒的值（yyyymmdd）
	 * 
	 * @param strDate
	 * @return
	 */
	public static long getMillsByDateString(String strDate) {
		Calendar cal = Calendar.getInstance();
		if ((strDate != null) && (strDate.trim().length() == 8)) {
			strDate = strDate.trim();
			try {
				int year = Integer.parseInt(strDate.substring(0, 4));
				int month = Integer.parseInt(strDate.substring(5, 6));
				int date = Integer.parseInt(strDate.substring(7, 8));
				cal.set(year, month, date, 0, 0, 0);
				String str = String.valueOf(cal.getTimeInMillis());
				return Long.parseLong(str.substring(0, str.length() - 3) + "000");
			} catch (Exception e) {
			}

		}

		return 0L;
	}

	/**
	 * 根据标准的时间串获得秒的值（yyyymmddhhmmss）
	 * 
	 * @param strDate
	 * @return
	 */
	public static long getMillsByDateTimeString(String strDateTime) {
		Calendar cal = Calendar.getInstance();
		if ((strDateTime != null) && (strDateTime.trim().length() > 18)) {
			strDateTime = strDateTime.trim();
			try {
				int year = Integer.parseInt(strDateTime.substring(0, 4));
				int month = Integer.parseInt(strDateTime.substring(5, 7)) - 1;
				int date = Integer.parseInt(strDateTime.substring(8, 10));
				int hour = Integer.parseInt(strDateTime.substring(11, 13));
				int minute = Integer.parseInt(strDateTime.substring(14, 16));
				int second = Integer.parseInt(strDateTime.substring(17, 19));
				cal.set(year, month, date, hour, minute, second);
				return cal.getTimeInMillis();
			} catch (Exception e) {
			}
		}
		return 0L;
	}

	/**
	 * 根据秒的数值获得特定格式的串
	 * 
	 * @param millseconds
	 * @param format
	 * @return
	 */
	public static String getFormatString(long millseconds, String format) {
		if ((format == null) || (format.trim().length() == 0)) {
			format = "yyyy-MM-dd";
		}
		format = format.trim();
		return new SimpleDateFormat(format).format(new Date(millseconds));
	}

	/**
	 * 获得当前时间的秒值
	 * 
	 * @return
	 */
	public static long getCurrentTimeMillis() {
		Calendar c = Calendar.getInstance();
		return c.getTimeInMillis();
	}

	/**
	 * 根据秒数值得到时间字符串
	 * 
	 * @param mills
	 * @return
	 * @throws Exception
	 */
	public static String getTimeByMills(long mills) throws Exception {
		try {
			if (mills == 0L)
				return "-";
			Date date = null;
			Calendar ca = Calendar.getInstance();
			ca.setTimeInMillis(mills);
			date = ca.getTime();
			SimpleDateFormat myformat;

			if ((ca.get(11) == 0) && (ca.get(12) == 0) && (ca.get(13) == 0)) {
				myformat = new SimpleDateFormat("yyyy-MM-dd");
			} else
				myformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			return myformat.format(date);
		} catch (Exception e) {
		}
		return "-";
	}

	/**
	 * 格式化时间yyyy-MM-dd
	 * 
	 * @param mills
	 * @return
	 * @throws Exception
	 */
	public static String formatDate(long mills) throws Exception {
		try {
			if (mills == 0L)
				return "-";
			Date date = null;
			Calendar ca = Calendar.getInstance();
			ca.setTimeInMillis(mills);
			date = ca.getTime();

			SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");

			return myformat.format(date);
		} catch (Exception e) {
		}
		return "-";
	}

	/**
	 * 格式化时间yyyy-MM-dd HH:mm:ss
	 * 
	 * @param mills
	 * @return
	 * @throws Exception
	 */
	public static String formatTime(long mills) throws Exception {
		try {
			if (mills == 0L)
				return "-";
			Date date = null;
			Calendar ca = Calendar.getInstance();
			ca.setTimeInMillis(mills);
			date = ca.getTime();

			SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			return myformat.format(date);
		} catch (Exception e) {
		}
		return "-";
	}

	/**
	 * 标准的格式化时间（优先考虑此方法）
	 * 
	 * @param strTime
	 * @return
	 * @throws Exception
	 */
	public static long getMillsByTime(String strTime) throws Exception {
		try {
			if ((strTime.length() != 19) && (strTime.length() != 10)) {
				throw new Exception("the time string is wrong.");
			}

			int year = Integer.parseInt(strTime.substring(0, 4));
			int month = Integer.parseInt(strTime.substring(5, 7)) - 1;
			int day = Integer.parseInt(strTime.substring(8, 10));

			if ((year < 1000) || (year > 3000)) {
				throw new Exception("the year is wrong.");
			}

			if ((month < 0) || (month > 12)) {
				throw new Exception("the month is wrong.");
			}

			if ((day < 1) || (day > 31)) {
				throw new Exception("the day is wrong");
			}

			Calendar ca = Calendar.getInstance();
			if (strTime.length() == 19) {
				int hour = Integer.parseInt(strTime.substring(11, 13));
				int minute = Integer.parseInt(strTime.substring(14, 16));
				int second = Integer.parseInt(strTime.substring(17, 19));

				if ((hour < 0) || (hour > 24)) {
					throw new Exception("the hour is wrong.");
				}

				if ((minute < 0) || (minute > 60)) {
					throw new Exception("the minute is wrong.");
				}

				if ((second < 0) || (second > 60)) {
					throw new Exception("the second is wrong.");
				}

				ca.set(year, month, day, hour, minute, second);
			} else if (strTime.length() == 10) {
				ca.set(year, month, day, 0, 0, 0);
			}
			return ca.getTimeInMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1L;
	}

	public static long getNextTime(int timeUnit, int interval, long timeMill) {
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(timeMill);
		switch (timeUnit) {
		case 0:
			ca.add(13, interval);
			break;
		case 1:
			ca.add(12, interval);
			break;
		case 2:
			ca.add(10, interval);
			break;
		case 3:
			ca.add(5, interval);
			break;
		case 4:
			ca.add(2, interval);
			break;
		default:
			return 0L;
		}
		return ca.getTimeInMillis();
	}

	/**
	 * 根据字符串获得Date类型yy-MM-dd HH:mm:ss
	 * 
	 * @param timeString
	 * @return
	 */
	public static Date getDateTimeByTimeString(String timeString) {
		DateFormat f = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		Date date = new Date();
		try {
			date = f.parse(timeString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 根据字符串获得Date类型yy-MM-dd
	 * 
	 * @param timeString
	 * @return
	 */
	public static Date getDateByDateString(String timeString) {
		DateFormat f = new SimpleDateFormat("yy-MM-dd");
		Date date = new Date();
		try {
			date = f.parse(timeString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 根据年月日获得当前时间
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date getDate(int year, int month, int day) {
		//year -= 1900;
		month -= 1;
		
		Calendar c = Calendar.getInstance();
		c.set(year, month, day);
		
		//Date d = new Date(year, month, day);
		Date d = c.getTime();
		return d;
	}

	/**
	 * 获得时间Date（年，月，日，小时，分钟）
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @return
	 */
	public static Date getDate(int year, int month, int day, int hour, int minute) {
		//year -= 1900;
		month -= 1;
		Calendar c = Calendar.getInstance();
		c.set(year, month, day, hour, minute);
		//Date d = new Date(year, month, day, hour, minute);
		Date d = c.getTime();
		return d;
	}
	
	/**
	 * 获得时间差的Int值
	 * 
	 * @param endTime
	 * @param startTime
	 * @return
	 */
	public static int getSecondDiff(Date endTime, Date startTime) {
		long start = startTime.getTime();
		long end = endTime.getTime();
		return (int) ((end - start) / 1000L);
	}

	/**
	 * 获得指定月的天数
	 * 
	 * @param year
	 * @param mon
	 * @return
	 */
	public static int getDaysOfMonth(int year, int mon) {
		Calendar cal = Calendar.getInstance();
		cal.set(1, year);
		cal.set(2, mon - 1);
		return cal.getActualMaximum(5);
	}

	/**
	 * 获得指定月的星期个数
	 * 
	 * @param year
	 * @param mon
	 * @return
	 */
	public static int getWeekDayOfMonth(int year, int mon) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, mon - 1, 1);
		return cal.get(7);
	}

	/**
	 * 计算两个日期相差的天数
	 * 
	 * @param small
	 * @param big
	 * @return正数表示前者更早，负数表示前者更晚
	 * @throws ParseException
	 */
	public static int getDifferDay(Date small, Date big) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(sdf.format(small)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time1 = cal.getTimeInMillis();
		try {
			cal.setTime(sdf.parse(sdf.format(big)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time2 = cal.getTimeInMillis();
		long differ = (long) ((time2 - time1) / (1000 * 3600 * 24));

		return Integer.parseInt(String.valueOf(differ));

	}

	/**
	 * 比较2个日期相差的月份数
	 * 
	 * @param small较小的日期
	 * @param big较大的日期
	 * @return正数表示前者早于后者，负数表示前者晚于后者。 不足一月按1月算
	 */
	public static int getDifferMonth(Date small, Date big) {
		
		Calendar c_s = Calendar.getInstance();
		c_s.setTime(small);
		
		Calendar c_b = Calendar.getInstance();
		c_b.setTime(big);
		
		/*
		int dy1 = small.getYear();
		int dy2 = big.getYear();

		int dm1 = small.getMonth();
		int dm2 = big.getMonth();

		int dd1 = small.getDate();
		int dd2 = big.getDate();
		*/
		
		int dy1 = c_s.get(Calendar.YEAR);
		int dy2 = c_b.get(Calendar.YEAR);

		int dm1 = c_s.get(Calendar.MONTH);
		int dm2 = c_b.get(Calendar.MONTH);

		int dd1 = c_s.get(Calendar.DATE);
		int dd2 = c_b.get(Calendar.DATE);
		

		int differ = 0;
		if (small.before(big)) {
			differ = dm2 - dm1 + (dy2 - dy1) * 12;
			if (dd1 < dd2) {
				differ++;
			}
		} else {
			differ = dm1 - dm2 + (dy1 - dy2) * 12;
			if (dd2 < dd1) {
				differ++;
			}
			differ = -differ;
		}

		return differ;
	}

	/**
	 * 根据今天日期，获取n天前的日期
	 * 
	 * @param num
	 *            几天前
	 * @return
	 */
	public static String beforeNDay(int num) {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, -num);
		Date dayNBefore = cal.getTime();
		String beforeNDay = TimeUtil.getDateString(dayNBefore);
		return beforeNDay;
	}

	/**
	 * 根据传入日期，其n天前日期
	 * 
	 * @param theDay
	 * @param num
	 * @return
	 */
	public static Date TodayBeforDay(Date theDay, int num) {
		Date today = theDay;
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, -num);
		Date dayNBefore = cal.getTime();
		return dayNBefore;
	}

	/**
	 * 根据传入日期，其n天后日期
	 * 
	 * @param theDay
	 * @param num
	 * @return
	 */
	public static Date TodayAfterDay(Date theDay, int num) {
		Date today = theDay;
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, num);
		Date dayNBefore = cal.getTime();
		return dayNBefore;
	}

	/**
	 * 根据起始结束时间，获取之间每一天组成的集合
	 * 
	 * @param startDay
	 * @param endDay
	 * @return
	 */
	public static List<String> makeaDayListBySE(String startDay, String endDay) {
		List<String> dayList = new ArrayList<String>();
		Date start = convertString(startDay, "yyyy-MM-dd");
		Date end = convertString(endDay, "yyyy-MM-dd");
		Date temp = start;
		dayList.add(startDay);
		int times = getDifferDay(start, end);
		if (times == 0) {
			return dayList;
		}
		for (int i = 0; i < times; i++) {
			temp = TodayAfterDay(temp, 1);
			if (!getDateString(temp).trim().equals(endDay.trim())) {
				dayList.add(getDateString(temp));
			}
		}
		// while(!getDateString(temp).trim().equals(endDay.trim())){
		// dayList.add(getDateString(temp));
		// temp = TodayAfterDay(temp,1);
		// }
		dayList.add(endDay);
		return dayList;
	}

	/**
	 * 根据传入日期获取所属周的星期一的日期
	 * 
	 * @param theDay
	 * @return
	 */
	public static Date getMondayByWeek(Date theDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(theDay);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date monday = cal.getTime();
		return monday;
	}

	/**
	 * 根据传入日期获取所属月的第一天日期(i,是几月前后的;当前月i为0，,上个月为-1，下个月为1)
	 * 
	 * @param theDay
	 * @return
	 */
	public static Date getFistDayOfMonth(Date theDay, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(theDay);
		cal.add(MONTH, i);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date fistDay = cal.getTime();
		return fistDay;
	}

	/**
	 * 根据传入日期获取所属月的最后一天日期(i,是几月前后的;当前月i为0，,上个月为-1，下个月为1)
	 * 
	 * @param theDay
	 * @return
	 */
	public static Date getLastDayofMonth(Date theDay, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(theDay);
		cal.add(MONTH, i);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date lastDay = cal.getTime();
		return lastDay;
	}

	/**
	 * 根据日期和希望获得的日期格式获取字符串时间
	 * 
	 * @param date
	 *            有效日期格式
	 * @param type
	 * @return
	 */
	public static String changeDateStringByTpye(Date date, String type) {
		if (date == null) {
			return "";
		}
		if (StringUtils.isBlank(type)) {
			getDateTimeString(date);
		}
		return new SimpleDateFormat(type).format(date);
	}

	/**
	 * 根据日期和希望获得的日期格式获取字符串时间
	 * 
	 * @param String
	 *            有效日期格式
	 * @param type
	 * @return
	 */
	public static String changeDateStringByType_2(String date, String type) {
		if (StringUtils.isBlank(date)) {
			return "";
		}
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date thdate = format.parse(date);
			if (StringUtils.isBlank(type)) {
				getDateTimeString(thdate);
			}
			return changeDateStringByTpye(thdate, type);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getAllMonthBewteenTime(String startDate, String endDate) {
		try {
			List<String> list = new ArrayList<String>();
			Date d1 = new SimpleDateFormat("yyyy-MM").parse(startDate);// 定义起始日期
			Date d2 = new SimpleDateFormat("yyyy-MM").parse(endDate);// 定义结束日期
			Calendar dd = Calendar.getInstance();// 定义日期实例
			dd.setTime(d1);// 设置日期起始时间
			while (dd.getTime().before(d2)) {// 判断是否到结束日期
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				String time = sdf.format(dd.getTime());// 获取格式化日期字符串
				list.add(time);
				dd.add(Calendar.MONTH, 1);// 进行当前日期月份加1
			}
			list.add(new SimpleDateFormat("yyyy-MM").format(d2.getTime()));
			return list;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取日期的年、月、日、时分秒，周几（外国），返回的都是int值的map
	 * 
	 * @param date
	 * @return
	 */
	public static Map<String, Integer> getYearMonthDayFromDate(Date date) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);// 获取年份
		int month = cal.get(Calendar.MONTH);// 获取月份
		int day = cal.get(Calendar.DATE);// 获取日
		int hour = cal.get(Calendar.HOUR);// 小时
		int minute = cal.get(Calendar.MINUTE);// 分
		int second = cal.get(Calendar.SECOND);// 秒
		int WeekOfYear = cal.get(Calendar.DAY_OF_WEEK);// 一周的第几天

		map.put("year", year);
		map.put("month", month + 1);// 月份0开始
		map.put("day", day);
		map.put("hour", hour);
		map.put("minute", minute);
		map.put("second", second);
		map.put("WeekOfYear", WeekOfYear);

		return map;
	}

	/**
	 * 根据日期时间获取其时间错，去除后三位
	 * 
	 * @param tempDate
	 * @return
	 */
	public static long getMillsByDate(String tempDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = df.parse(tempDate);
		} catch (ParseException e) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		long timestamp = cal.getTimeInMillis();
		return Long.valueOf(String.valueOf(timestamp).substring(0, String.valueOf(timestamp).length() - 3));
	}

	/**
	 * 将字符串日期转换成Calendar,需要日期格式yyyy-MM-dd
	 * 
	 * @param time
	 * @return
	 */
	public static Calendar changeStringToCalendar(String time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(time);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 根据起始结束日期获取之间全部日期
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getListDateBewteenDays(String startDate, String endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> result = new ArrayList<String>();
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		start.setTime(changeStringToDateNoTime(startDate));
		end.setTime(changeStringToDateNoTime(endDate));
		// 起始时间添加
		result.add(sdf.format(start.getTime()));
		// 中间时间添加
		Calendar temp = (Calendar) start.clone();
		temp.add(Calendar.DAY_OF_YEAR, 1);
		while (temp.before(end)) {
			result.add(sdf.format(temp.getTime()));
			temp.add(Calendar.DAY_OF_YEAR, 1);
		}
		// 结束时间添加
		if(!endDate.equals(startDate)){
			result.add(sdf.format(end.getTime()));
		}
		return result;
	}

	/**
	 * 获取该月份的第一天数据
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonthFirstDay(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar time = Calendar.getInstance();
		time.setTime(date);
		time.set(Calendar.DAY_OF_MONTH, 1);
		return sdf.format(time.getTime());
	}
	
	/**
	 * 获取当前年的第一天
	 * @param date
	 * @return
	 */
	public static String getYearFirstDay(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar time = Calendar.getInstance();
		time.setTime(date);
		time.set(Calendar.DAY_OF_YEAR, 1);
		return sdf.format(time.getTime());
	}

	/**
	 * 根据数字获取前后月份
	 * 
	 * @param MonthDate
	 * @param num
	 * @return
	 */
	public static String getMonthCalculateNum(String MonthDate, int num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar time = Calendar.getInstance();
		time.setTime(changeStringToDateNoTime(MonthDate));
		time.add(Calendar.MONTH, num);
		return sdf.format(time.getTime());
	}

	/**
	 * 根据数字获取前后年对应的这天
	 * 
	 * @param YearDate
	 * @param num
	 * @return
	 */
	public static String getYearCalculateNum(String YearDate, int num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar time = Calendar.getInstance();
		time.setTime(changeStringToDateNoTime(YearDate));
		time.add(Calendar.YEAR, num);
		return sdf.format(time.getTime());
	}

	/**
	 * 根据起始时间、结束时间获取期间的月份
	 * 
	 * @param startMonth
	 * @param endMonth
	 * @return
	 */
	public static List<String> getListDateByMonth(String startMonth, String endMonth) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> result = new ArrayList<String>();
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		start.setTime(changeStringToDateNoTime(startMonth));
		end.setTime(changeStringToDateNoTime(endMonth));
		// 起始时间添加
		result.add(sdf.format(start.getTime()));
		// 中间时间添加
		Calendar temp = (Calendar) start.clone();
		temp.add(Calendar.MONTH, 1);
		while (temp.before(end)) {
			result.add(sdf.format(temp.getTime()));
			temp.add(Calendar.MONTH, 1);
		}
		// 结束时间添加
		result.add(sdf.format(end.getTime()));
		return result;
	}

	/**
	 * 根据起始时间、结束时间获取期间的年对应的天
	 * 
	 * @param startYear
	 * @param endYear
	 * @return
	 */
	public static List<String> getListDateByYear(String startYear, String endYear) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> result = new ArrayList<String>();
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		start.setTime(changeStringToDateNoTime(startYear));
		end.setTime(changeStringToDateNoTime(endYear));
		// 起始时间添加
		result.add(sdf.format(start.getTime()));
		// 中间时间添加
		Calendar temp = (Calendar) start.clone();
		temp.add(Calendar.YEAR, 1);
		while (temp.before(end)) {
			result.add(sdf.format(temp.getTime()));
			temp.add(Calendar.YEAR, 1);
		}
		// 结束时间添加
		result.add(sdf.format(end.getTime()));
		return result;
	}

	public static Date changeStringToDateNoTime(String str) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取某月的前一个月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfLastMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1); // 上一个月
		calendar.set(Calendar.DATE, 1); // 设置为该月第一天
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 将字符串转换为指定format的Date
	 * 
	 * @param value
	 * @param format
	 * 			e.g. yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date convertStringToDate(String value, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (value == null)
			return null;
		try {
			return sdf.parse(value);
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 根据Date,转化为时间戳
	 * @param date
	 * @return
	 */
	public static Long convertDateToTimestamp(Date date){
		String dateValue = changeDateStringByTpye(date,"yyyy-MM-dd");
		return getMillsByDate(dateValue);
	}

    /**
     * 两个时间点是不是在同一天内
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Boolean isInOneDay(Date date1, Date date2) {
        return Math.abs(date1.getTime() - date2.getTime()) < (1000 * 60 * 60 * 24);
    }

	public static void main(String[] args) {
		System.out.println(getYearFirstDay(new Date()));
	}

}