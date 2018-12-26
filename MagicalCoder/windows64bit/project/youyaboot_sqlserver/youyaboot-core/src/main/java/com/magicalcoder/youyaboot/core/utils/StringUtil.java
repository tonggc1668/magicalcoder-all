package com.magicalcoder.youyaboot.core.utils;


import org.apache.commons.lang.StringUtils;

import java.util.Random;

/**
 * qq:799374340
 * @author hdy
 * 2013-6-25下午1:39:28
 */
public class StringUtil {

    public static final String ASCII_LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String DIGITS = "0123456789";

	public static boolean isBlank(String str){
		if(str==null || "".equals(str.trim())){
			return true;
		}
		return false;
	}
	public static boolean isNotBlank(String str){
		if(str!=null && !"".equals(str.trim())){
			return true;
		}
		return false;
	}
    public static String deleteLastChar(String str){
        if(isBlank(str)) return str;
        return str.substring(0,str.length()-1);
    }

    public static String deleteLastChar(String str,int len){
        if(isBlank(str)) return str;
        return str.substring(0,str.length()-len);
    }
    public static String deleteBeforeChar(String str,int len){
        if(isBlank(str)) return str;
        return str.substring(len);
    }

    /**
     * 生成指定长度的随机字符串
     * @param length 目标长度
     * @return 产物
     */
    public static String getRandomString(int length) {
        return StringUtil.getRandomString(length, StringUtil.ASCII_LETTERS+StringUtil.DIGITS);
    }

    /**
     * 从给定的范围里生成指定长度的随机字符串
     * @param length 目标长度
     * @param population 候选字符
     * @return 产物
     */
    public static String getRandomString(int length, String population){
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(population.length());
            sb.append(population.substring(number, number+1));
        }
        return sb.toString();
    }

    public static Integer getUnicodeStringLength(String content) {
        if (StringUtils.isBlank(content)) {
            return 0;
        }
        return content.codePointCount(0, content.length());
    }
}
