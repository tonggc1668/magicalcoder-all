package com.magicalcoder.youyaboot.core.identifyingcode;

/**
 * Created by www.magicalcoder.com on 2015/8/5.
 * 799374340@qq.com
 */
public interface IdentifyingCodeConstant {

    String CHECK_CODE_SESSION_KEY = "CheckCodeKey";


    String CHINA = "ch";
    String NUMBER = "n";
    String ENGLISH = "l";
    String NUMBER_ENGLISH = "nl";

    //使用类型 session memcache方式
    int STORE_TYPE_SESSION  = 0;//session
    int STORE_TYPE_CACHE_SERVER  = 1;//系统默认缓存服务器


}
