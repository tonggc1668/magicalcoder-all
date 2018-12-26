package com.magicalcoder.youyaboot.core.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by magicalcoder.com.com on 2016/9/30.
 * E 对象类型
 * K 属性类型
 */
public class ReflectUtil<K, E> {


    public void setBeanValue(E obj, Class<K> clazz, String field, K newValue){
        try {
            String name = "set"+firstCharUp(field);
            Method method = obj.getClass().getMethod(name,clazz);
            method.setAccessible(true);//提供速度 由于JDK的安全检查耗时较多.所以通过setAccessible(true)的方式关闭安全检查就可以达到提升反射速度的目的
            method.invoke(obj,newValue);
            method.setAccessible(false);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public K getBeanValue(E obj, String field){
        try {
            String name = "get"+firstCharUp(field);
            Method method = obj.getClass().getMethod(name);
            method.setAccessible(true);//提供速度 由于JDK的安全检查耗时较多.所以通过setAccessible(true)的方式关闭安全检查就可以达到提升反射速度的目的
            Object value = method.invoke(obj);
            method.setAccessible(false);
            return (K)value;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

    }

    private static String firstCharUp(String str){
        return str.substring(0,1).toUpperCase()+str.substring(1);
    }

}
