package com.magicalcoder.youyaboot.core.utils;

import java.math.BigDecimal;

/**
 * Created by magicalcoder.com.com on 2016/9/26.
 */
public class BigDecimalUtil {

    /**
     * 保留2位小数
     * @param num
     * @return
     */
    public static BigDecimal setTwoScale(BigDecimal num){
        return num.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    /**
     * 保留n位小数
     * @param num
     * @param newScale 小数点位数
     * @return
     */
    public static BigDecimal setScale(BigDecimal num,int newScale){
        return num.setScale(newScale, BigDecimal.ROUND_HALF_UP);
    }
}
