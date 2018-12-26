package com.magicalcoder.youyaboot.core.cache;

import java.lang.annotation.*;

/**
 * author: www.magicalcoder.com
 * date:2018/4/4
 * function:
 * 缓存自动清理注解
 * 使用方法：在@service的方法前加入接口
 * 被注解的方法名称规则：xxx+必须是跟要清除的CacheParam同类下 CacheParam注解的方法首字母大写(入参一致)
 */
@Target(ElementType.METHOD)//放在方法前面的注解 也要 放在 类CLASS 属性FIELD上的
@Retention(RetentionPolicy.RUNTIME)// 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Documented//该注解将被保护在javadoc中
public @interface CacheDeleteParam {
}
