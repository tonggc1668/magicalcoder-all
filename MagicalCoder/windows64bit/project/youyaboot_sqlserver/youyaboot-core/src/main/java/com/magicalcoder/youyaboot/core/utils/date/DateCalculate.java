package com.magicalcoder.youyaboot.core.utils.date;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by www.magicalcoder.com on 14-3-31.
 * 799374340@qq.com
 */
public class DateCalculate {
    private static DateCalculate instance = new DateCalculate();
    public static DateCalculate get(){
        return instance;
    }
    /** 注意格里历和儒略历交接时的日期差别 */
    private static transient int gregorianCutoverYear = 1582;

    /** 闰年中每月天数 */
    private static final int[] DAYS_P_MONTH_LY=
            {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    /** 平年中每月天数 */
    private static final int[] DAYS_P_MONTH_CY=
            {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    /** 代表数组里的年、月、日 */
    private static final int Y = 0, M = 1, D = 2;

    /** 参与运算用 */
    private int[] ymd = null;

    /**
     * 检查传入的参数是否合法的日期
     * @param date
     * @throws IllegalArgumentException
     */
    public static void validate(String date)throws IllegalArgumentException{

        int[] ymd = splitYMD( date );

        if( ymd[M] == 0 || ymd[M] > 12 ){
            throw new IllegalArgumentException("月份数值错误");
        }

        if( true == isLeapYear( ymd[0] ) ){
            if( ymd[D] == 0 || ymd[D] > DAYS_P_MONTH_LY[ymd[M] -1 ] ){
                throw new IllegalArgumentException("日期数值错误");
            }
        }else{
            if( ymd[D] == 0 || ymd[D] > DAYS_P_MONTH_CY[ymd[M] -1 ] ){
                throw new IllegalArgumentException("日期数值错误");
            }
        }
    }

    /**
     * 检查传入的参数代表的年份是否为闰年
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        return year >= gregorianCutoverYear ?
                ((year%4 == 0) && ((year%100 != 0) ||
                        (year%400 == 0))) : // Gregorian
                (year%4 == 0); // Julian
    }

    /**
     * 日期加1天，注意这里没有考虑儒略历和格里历交接时相差的10天
     * @param year
     * @param month
     * @param day
     * @return
     */
    private int[] addOneDay(int year, int month, int day){
        if(isLeapYear( year )){
            day++;
            if( day > DAYS_P_MONTH_LY[month -1 ] ){
                month++;
                if(month > 12){
                    year++;
                    month = 1;
                }
                day = 1;
            }
        }else{
            day++;
            if( day > DAYS_P_MONTH_CY[month -1 ] ){
                month++;
                if(month > 12){
                    year++;
                    month = 1;
                }
                day = 1;
            }
        }
        int[] ymd = {year, month, day};
        return ymd;
    }

    /**
     * 以循环的方式计算日期加法
     * @param date
     * @param days
     * @return
     */
    public String addDaysByLoop(String date, int days){
        validate(date);
        int[] ymd = splitYMD( date );
        for(int i = 0; i < days; i++){
            ymd = addOneDay(ymd[Y], ymd[M], ymd[D]);
        }
        return formatYear(ymd[Y])+
                formatMonthDay(ymd[M])+
                formatMonthDay(ymd[D]);
    }

    /**
     * 日期减1天，注意这里没有考虑儒略历和格里历交接时相差的10天
     * @param year
     * @param month
     * @param day
     * @return
     */
    private int[] reduceOneDay(int year, int month, int day){
        if(isLeapYear( year )){
            day--;
            if( day <= 0 ){
                month--;
                if(month < 1){
                    year--;
                    month = 12;
                }
                day = DAYS_P_MONTH_LY[month -1 ];
            }
        }else{
            day--;
            if( day <= 0 ){
                month--;
                if(month < 1){
                    year--;
                    month = 12;
                }
                day = DAYS_P_MONTH_CY[month -1 ];
            }
        }
        int[] ymd = {year, month, day};
        return ymd;
    }

    /**
     * 以循环的方式计算日期减法
     * @param date
     * @param days
     * @return
     */
    public String reduceDaysByLoop(String date, int days){
        validate(date);
        int[] ymd = splitYMD( date );
        for(int i = 0; i < days; i++){
            ymd = reduceOneDay(ymd[Y], ymd[M], ymd[D]);
        }
        return formatYear(ymd[Y])+
                formatMonthDay(ymd[M])+
                formatMonthDay(ymd[D]);
    }

    /**
     * 指定日期加上指定的天数的操作
     * @param date
     * @param days
     * @return
     * @throws IllegalArgumentException
     */
    public Integer addDays(Date date, int days)
            throws IllegalArgumentException{
        return addDays(formatDate(date), days);
    }

    /**
     * 指定日期加上指定的天数的操作
     * @param date
     * @param days
     * @return
     * @throws IllegalArgumentException
     */
    public Integer addDays(String date, int days)
            throws IllegalArgumentException{

        validate(date);
        ymd = splitYMD( date );

        if( isLeapYear( ymd[Y] ) ){
            ymd[D] += days;
            if( ymd[D] > DAYS_P_MONTH_LY[ymd[M] -1 ] ){
                ymd[M] ++;
                ymd[D] = ymd[D] - DAYS_P_MONTH_LY[ymd[M] -1-1 ];
                if(ymd[M] > 12){
                    ymd[M] -= 12;
                    ymd[Y]++;
                }
                if( ymd[D] > DAYS_P_MONTH_LY[ymd[M] -1 ] ){
                    addDays(formatYear(ymd[Y])+
                            formatMonthDay(ymd[M])+
                            formatMonthDay(DAYS_P_MONTH_LY[ymd[M] -1 ]),
                            ymd[D] - DAYS_P_MONTH_LY[ymd[M] -1 ]);
                }
            }
        }else{
            ymd[D] += days;
            if( ymd[D] > DAYS_P_MONTH_CY[ymd[M] -1 ] ){
                ymd[M] ++;
                ymd[D] = ymd[D] - DAYS_P_MONTH_CY[ymd[M] -1-1 ];
                if(ymd[M] > 12){
                    ymd[M] -= 12;
                    ymd[Y]++;
                }
                if( ymd[D] > DAYS_P_MONTH_CY[ymd[M] -1 ] ){
                    addDays(formatYear(ymd[Y])+
                            formatMonthDay(ymd[M])+
                            formatMonthDay(DAYS_P_MONTH_CY[ymd[M] -1 ]),
                            ymd[D] - DAYS_P_MONTH_CY[ymd[M] -1 ]);
                }
            }
        }
        return  Integer.parseInt(formatYear(ymd[Y])+
                formatMonthDay(ymd[M])+
                formatMonthDay(ymd[D]));
    }

    /**
     * 指定日期减去指定的天数的操作
     * @param date
     * @param days
     * @return
     * @throws IllegalArgumentException
     */
    public Integer reduceDays(Date date, int days)
            throws IllegalArgumentException{
        return reduceDays(formatDate(date), days);
    }

    /**
     * 指定日期减去指定的天数的操作
     * @param date
     * @param days
     * @return
     * @throws IllegalArgumentException
     */
    public Integer reduceDays(String date, int days)
            throws IllegalArgumentException{

        validate(date);
        ymd = splitYMD( date );

        if( isLeapYear( ymd[Y] ) ){
            ymd[D] -= days;
            if( ymd[D] <= 0 ){
                ymd[M] --;
                if(ymd[M] < 1){
                    ymd[M] += 12;
                    ymd[Y]--;
                }
                ymd[D] = ymd[D] + DAYS_P_MONTH_LY[ ymd[M]-1 ];
                if( ymd[D] <= 0 ){
                    reduceDays(formatYear(ymd[Y])+
                            formatMonthDay(ymd[M])+
                            formatMonthDay( 1 ),
                            abs( ymd[D] - 1 ));
                }
            }
        }else{
            ymd[D] -= days;
            if( ymd[D] <= 0 ){
                ymd[M] --;
                if(ymd[M] < 1){
                    ymd[M] += 12;
                    ymd[Y]--;
                }
                ymd[D] = ymd[D] + DAYS_P_MONTH_CY[ ymd[M]-1 ];
                if( ymd[D] <= 0 ){
                    reduceDays(formatYear(ymd[Y])+
                            formatMonthDay(ymd[M])+
                            formatMonthDay(1),
                            abs( ymd[D] - 1 ));
                }
            }
        }
        return  Integer.valueOf(formatYear(ymd[Y])+
                formatMonthDay(ymd[M])+
                formatMonthDay(ymd[D]));
    }

    /**
     * 格式化一个日期字符串
     * @param date
     * @return
     */
    public static String formatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format( date );
    }

    /**
     * 将代表日期的字符串分割为代表年月日的整形数组
     * @param date
     * @return
     */
    public static int[] splitYMD(String date){
        int[] ymd = {0, 0, 0};
        ymd[Y] = Integer.parseInt(date.substring(0, 4));
        ymd[M] = Integer.parseInt(date.substring(4, 6));
        ymd[D] = Integer.parseInt(date.substring(6, 8));
        return ymd;
    }

    /**
     * 将不足两位的月份或日期补足为两位
     * @param decimal
     * @return
     */
    public static String formatMonthDay(int decimal){
        DecimalFormat df = new DecimalFormat("00");
        return df.format( decimal );
    }

    /**
     * 将不足四位的年份补足为四位
     * @param decimal
     * @return
     */
    public static String formatYear(int decimal){
        DecimalFormat df = new DecimalFormat("0000");
        return df.format( decimal );
    }

    /**
     * 取绝对值操作
     * @param num
     * @return
     */
    public static int abs(int num){
        return (num > 0) ? num : -num;
    }

    /**
     * 测试用main函数
     * @param args
     */
    public static void main( String[] args ) throws Exception{
        System.out.println(("20121112-20121212").substring(13,17));;
    }
}
