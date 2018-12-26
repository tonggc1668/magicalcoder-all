package com.magicalcoder.youyaboot.admin.rmp.model;

import java.io.Serializable;

/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

public class SysModule implements Serializable{

    private Long id;//主键
    private String moduleName;//模块唯一键
    private String moduleUrl;//模块URL
    private Long moduleCategoryId;//模块分类
    private Integer sortNum;//排序
    private String moduleTitle;//模块标题
    private Boolean ifShow;//是否显示

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getModuleName(){
        return moduleName;
    }
    public void setModuleName(String moduleName){
        this.moduleName = moduleName;
    }
    public String getModuleUrl(){
        return moduleUrl;
    }
    public void setModuleUrl(String moduleUrl){
        this.moduleUrl = moduleUrl;
    }
    public Long getModuleCategoryId(){
        return moduleCategoryId;
    }
    public void setModuleCategoryId(Long moduleCategoryId){
        this.moduleCategoryId = moduleCategoryId;
    }
    public Integer getSortNum(){
        return sortNum;
    }
    public void setSortNum(Integer sortNum){
        this.sortNum = sortNum;
    }
    public String getModuleTitle(){
        return moduleTitle;
    }
    public void setModuleTitle(String moduleTitle){
        this.moduleTitle = moduleTitle;
    }
    public Boolean getIfShow(){
        return ifShow;
    }
    public void setIfShow(Boolean ifShow){
        this.ifShow = ifShow;
    }

}
