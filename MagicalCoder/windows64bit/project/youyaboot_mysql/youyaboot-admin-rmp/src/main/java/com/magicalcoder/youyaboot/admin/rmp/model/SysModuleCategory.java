package com.magicalcoder.youyaboot.admin.rmp.model;

import java.io.Serializable;

/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

public class SysModuleCategory implements Serializable{

    private Long id;//主键
    private String moduleCategoryName;//模块名称
    private Integer sortNum;//排序

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getModuleCategoryName(){
        return moduleCategoryName;
    }
    public void setModuleCategoryName(String moduleCategoryName){
        this.moduleCategoryName = moduleCategoryName;
    }
    public Integer getSortNum(){
        return sortNum;
    }
    public void setSortNum(Integer sortNum){
        this.sortNum = sortNum;
    }

}
