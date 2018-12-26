package com.magicalcoder.youyaboot.core.common.dto;

/**
 * Created by magicalcoder.com.com on 2016/3/16.
 */
public class InputSelectShowDto {
    private String selectValue;//下拉展示值
    private Object hiddenId;//隐藏设定值

    public String getSelectValue() {
        return selectValue;
    }

    public void setSelectValue(String selectValue) {
        this.selectValue = selectValue;
    }

    public Object getHiddenId() {
        return hiddenId;
    }

    public void setHiddenId(Object hiddenId) {
        this.hiddenId = hiddenId;
    }

    public InputSelectShowDto(String selectValue, Object hiddenId) {
        this.selectValue = selectValue;
        this.hiddenId = hiddenId;
    }
    public InputSelectShowDto( Object hiddenId,String selectValue) {
        this.selectValue = selectValue;
        this.hiddenId = hiddenId;
    }

}
