package com.magicalcoder.youyaboot.core.common.dto;

/**
 * Created by magicalcoder.com.com on 2017/2/26.
 */
public class Select2ShowDto {
    private String text;//下拉展示值
    private Object id;//隐藏设定值

    public Select2ShowDto() {
    }

    public Select2ShowDto(Object id, String text) {
        this.text = text;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
