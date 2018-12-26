package com.magicalcoder.youyaboot.core.cache;

import com.alibaba.fastjson.JSON;
import com.magicalcoder.youyaboot.core.utils.StringUtil;

/**
 * author: www.magicalcoder.com
 * date:2018/6/29
 * function:
 */
class CacheKeyUtil {

    protected static String buildKey(String prefix,String targetClassName, String targetMethodName, Object ... args){
        String cacheKey = prefix;
        if(StringUtil.isBlank(cacheKey)){
            cacheKey= CacheKeyUtil.buildKey(targetClassName, targetMethodName, args);
        }else {
            cacheKey = CacheKeyUtil.buildKey(prefix,null,args);
        }
        return cacheKey;
    }

    private static String buildKey(String targetClassName, String targetMethodName, Object ... args){
        StringBuilder cacheKey = new StringBuilder();
        if(StringUtil.isNotBlank(targetClassName)){
            cacheKey.append(targetClassName);
        }
        if(StringUtil.isNotBlank(targetMethodName)){
            cacheKey.append(".").append(targetMethodName);
        }
        cacheKey.append("(");
        if(args!=null && args.length>0){
            cacheKey.append(JSON.toJSONString(args));
        }
        cacheKey.append(")");
        return cacheKey.toString()+"";
    }

    protected static String buildLockCacheKey(String cacheKey){
        return "_lock"+ cacheKey;
    }
}
