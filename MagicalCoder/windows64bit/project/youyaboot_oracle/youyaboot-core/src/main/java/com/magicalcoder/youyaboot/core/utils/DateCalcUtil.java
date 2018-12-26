package com.magicalcoder.youyaboot.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * qq:799374340
 * @author hdy
 * 2013-6-19下午2:25:12
 */
public class DateCalcUtil {
	static ThreadLocal<GregorianCalendar> local = new ThreadLocal<GregorianCalendar>() {
		protected GregorianCalendar initialValue() {
			return new GregorianCalendar();
		};
	};
	static final int[] d = new int[] { 1, 3, 5, 7, 8, 10, 12 };

	// 获取前一天 date:20101212 就会返回20101211
	public static int getBeforeOneDay(int date) {
		return getBeforeDay(date - 1);
	}

	//获得一天后的数据
	public static int getAfterOneDay(int v) {
		int day = v % 100;
		if (day <= 27) {// 快速算 27号以前直接返回+1
			return v + 1;
		}
		if (v % 10000 == 1231) {
			return v + 8870;
		}
		int f = (v % 10000) / 100;
		int dd = Arrays.binarySearch(d, f);
		if (dd < 0) {// 2 4 6 9 11
			if (f == 2) {
				return getVfrom2Month(v, day);
			} else {// 4 6 9 11月
				if (day < 30) {
					return v + 1;
				} else {// 30号20120430
					return v + 71;
				}
			}
		} else {// 1,3,5,7,8,10,12月
			if (day < 31) {
				return v + 1;
			} else {// 31号20120431
				return v + 70;
			}
		}
	}
	// 格式化数据
	private static int getBeforeDay(int v) {
		if (v % 100 == 0) {
			int f = (v % 10000) / 100;
			int dd = Arrays.binarySearch(d, f);
			if (dd < 0) {
				return v - 69;
			}
			if (dd >= 0) {
				if (f == 3) {
					int y = v / 10000;
					if (local.get().isLeapYear(y)) {// 闰年
						return v - 71;
					}
					return v - 72;
				} else if (f == 1) {
					return v - 8869;
				} else if (f == 8) {// 重构了下 如果是79960801前一天就不行了79960800
					return v - 69;
				}
				return v - 70;
			}
		}
		return v;
	}
	//获取2月的时间
	private static int getVfrom2Month(int v, int day) {
		// 2月
		if (day < 28) {
			return v + 1;
		} else {
			int y = v / 10000;
			if (local.get().isLeapYear(y)) {// 闰年20120229 20120301
				if (day == 29) {
					return v + 72;
				} else {// 28号
					return v + 1;
				}
			} else {// 28号
				if (day == 28) {// 平年
					return v + 73;
				}
			}
		}
		return v + 1;
	}
	// 日期字符串转成long型返回
	public static long parseDateStrToLong(String date, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		try {
			return sf.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0L;
		}
	}
    //日期
	public static Date changeDay(Date d, int offset) {
		if (d == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.set(Calendar.DAY_OF_YEAR,
				(calendar.get(Calendar.DAY_OF_YEAR) + offset));
		return calendar.getTime();
	}

	public static Date changeHour(Date d, int offset) {
		if (d == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.set(Calendar.HOUR_OF_DAY,
				(calendar.get(Calendar.HOUR_OF_DAY) + offset));
		return calendar.getTime();
	}

	public static Date changeMinute(Date d, int offset) {
		if (d == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.set(Calendar.MINUTE, (calendar.get(Calendar.MINUTE) + offset));
		return calendar.getTime();
	}

	public static Date changeSecond(Date d, int offset) {
		if (d == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.set(Calendar.SECOND, (calendar.get(Calendar.SECOND) + offset));
		return calendar.getTime();
	}
	
	// 获得两日期之间相距天数
	public static long compareWithDay(Date maxDate1, Date minDate2) {
		long quot = 0;
		quot = maxDate1.getTime() - minDate2.getTime();
		quot = quot / 1000 / 60 / 60 / 24;
		return quot;
	}

	// 获得两日期之间相距小时
	public static long compareWithHour(Date maxDate1, Date minDate2) {
		long quot = 0;
		quot = maxDate1.getTime() - minDate2.getTime();
		quot = quot / 1000 / 60 / 60;
		return quot;
	}

	// 获得两日期之间相距分钟
	public static long compareWithMinute(Date maxDate1, Date minDate2) {
		long quot = 0;
		quot = maxDate1.getTime() - minDate2.getTime();
		quot = quot / 1000 / 60;
		return quot;
	}

	// 获得两日期之间相距秒钟
	public static long compareWithSecond(Date maxDate1, Date minDate2) {
		long quot = 0;
		quot = maxDate1.getTime() - minDate2.getTime();
		quot = quot / 1000;
		return quot;
	}
    //获取现在到今天23：59：59间距多少秒
    public static int nowToTodayEndSeconds(){
        Date now = new Date();
        long between = compareWithSecond(DateFormatUtil.getDateEnd(now),now);
        return Integer.valueOf(between+"");
    }
}
