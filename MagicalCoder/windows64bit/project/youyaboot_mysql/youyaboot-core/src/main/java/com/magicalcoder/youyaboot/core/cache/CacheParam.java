package com.magicalcoder.youyaboot.core.cache;

import java.lang.annotation.*;

/**
 * author: www.magicalcoder.com
 * date:2018/4/4
 * function:
 * 缓存注解
 * 使用方法：在@service的方法前加入接口
 * 注意：1 仅支持普通类的返回 不支持方法Object Map类型返回
 */
@Target(ElementType.METHOD)//放在方法前面的注解 也要 放在 类CLASS 属性FIELD上的
@Retention(RetentionPolicy.RUNTIME)// 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Documented//该注解将被保护在javadoc中
public @interface CacheParam {
    //缓存前缀 如果设置了前缀则缓存key由自定义前缀+参数 否则采取类路径+方法+参数 比较长
    String prefix() default "";
    //失效时间 秒单位
    int expireSecond();
}
