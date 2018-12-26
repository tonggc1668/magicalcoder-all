package com.magicalcoder.youyaboot.admin.rmp.model.defined;

import com.magicalcoder.youyaboot.admin.rmp.model.SysPermission;

import java.io.Serializable;

/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/
public class SysPermissionWithSysModule extends SysPermission  implements Serializable{
        private Long sysModuleId;
    private String moduleName;
    private String moduleUrl;
    private Long moduleCategoryId;
    private Integer sortNum;
    private String moduleTitle;
    private String ifShow;
    public Long getSysModuleId(){
       return sysModuleId;
    }
    public void setSysModuleId(Long sysModuleId){
        this.sysModuleId = sysModuleId;
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
    public String getIfShow(){
       return ifShow;
    }
    public void setIfShow(String ifShow){
        this.ifShow = ifShow;
    }

}
