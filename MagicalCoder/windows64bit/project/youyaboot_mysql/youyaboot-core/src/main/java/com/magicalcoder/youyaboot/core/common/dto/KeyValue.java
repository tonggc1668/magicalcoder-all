package com.magicalcoder.youyaboot.core.common.dto;

/**
 * author: www.magicalcoder.com
 * date:2018/4/2
 * function:
 */
public class KeyValue {
    private String key;
    private String value;

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
