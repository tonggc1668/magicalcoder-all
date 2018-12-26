package com.magicalcoder.youyaboot.admin.rmp.common.controller.admin.res;

import java.util.List;

/**
 * author:www.magicalcoder.com
 * date:2018/9/3
 * function:
 */
public class RmpTreeRes {
    private String name;
    private String value;
    private boolean checked;
    private List<RmpTreeRes> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<RmpTreeRes> getList() {
        return list;
    }

    public void setList(List<RmpTreeRes> list) {
        this.list = list;
    }
}
