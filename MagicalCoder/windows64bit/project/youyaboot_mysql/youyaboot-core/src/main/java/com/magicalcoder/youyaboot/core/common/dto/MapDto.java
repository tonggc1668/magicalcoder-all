package com.magicalcoder.youyaboot.core.common.dto;

/**
 * Created by magicalcoder.com.com on 2016/8/4.
 */
public class MapDto {
    private String key;
    private Object value;

    public MapDto(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
