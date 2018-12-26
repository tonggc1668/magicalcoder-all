package com.magicalcoder.youyaboot.core.utils;

import java.util.List;

/**
 * qq:799374340
 * @author hdy
 * 2013-6-17上午9:48:59
 */
public class ListUtil {
	public static boolean isBlank(List ls){
		return ls==null || ls.isEmpty();
	}
	public static boolean isNotBlank(List ls){
		return !isBlank(ls);
	}
	public static String join(List ls,String split){
		if(isNotBlank(ls)){
			StringBuffer sb = new StringBuffer();
			for(Object item:ls){
				sb.append(item).append(split);
			}
			return StringUtil.deleteLastChar(sb.toString());
		}
		return null;
	}
}
