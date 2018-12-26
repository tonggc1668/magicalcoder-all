package com.magicalcoder.youyaboot.admin.rmp.common.controller.admin.res;

import java.util.List;

/**
 * author:www.magicalcoder.com
 * date:2018/8/1
 * function:
 */
public class ModuleRes {
    private String title;
    private String icon;
    private String href;
    private boolean spread;
    private List<ModuleRes> children;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public List<ModuleRes> getChildren() {
        return children;
    }

    public void setChildren(List<ModuleRes> children) {
        this.children = children;
    }
}
