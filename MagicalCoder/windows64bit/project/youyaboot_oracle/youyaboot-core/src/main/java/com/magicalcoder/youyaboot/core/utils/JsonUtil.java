package com.magicalcoder.youyaboot.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.sql.Date;
import java.util.List;


public class JsonUtil {

    /**
     * 递归设置子对象类 不支持数组
     * @param jsonString
     * @param pojoClass
     * @param <T>
     * @return
     */
    public static <T> T getObjectFromJSONString(String jsonString, Class<T> pojoClass) {
        return JSON.parseObject(jsonString,pojoClass);
    }

    public static String toJSONString(Object o){
        SerializeConfig config = new SerializeConfig();
        config.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
        SerializerFeature[] features = new SerializerFeature[]{SerializerFeature.WriteDateUseDateFormat,SerializerFeature.WriteMapNullValue};
        return JSON.toJSONString(o,config,features);
    }

    /**
     * 不支持递归设置子对象类
     * @param json
     * @param beanClazz
     * @param <T>
     * @return
     */
    public <T> List<T> getListFromJSONString(String json, Class<T> beanClazz){
        if(StringUtil.isBlank(json)){
            return null;
        }
        return JSON.parseArray(json, beanClazz);
    }
}
