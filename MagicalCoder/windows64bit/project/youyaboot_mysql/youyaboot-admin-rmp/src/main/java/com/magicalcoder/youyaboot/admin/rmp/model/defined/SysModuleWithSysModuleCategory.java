package com.magicalcoder.youyaboot.admin.rmp.model.defined;

import com.magicalcoder.youyaboot.admin.rmp.model.SysModule;

import java.io.Serializable;

/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/
public class SysModuleWithSysModuleCategory extends SysModule  implements Serializable{
        private Long sysModuleCategoryId;
    private String moduleCategoryName;
    private Integer sysModuleCategorySortNum;
    public Long getSysModuleCategoryId(){
       return sysModuleCategoryId;
    }
    public void setSysModuleCategoryId(Long sysModuleCategoryId){
        this.sysModuleCategoryId = sysModuleCategoryId;
    }
    public String getModuleCategoryName(){
       return moduleCategoryName;
    }
    public void setModuleCategoryName(String moduleCategoryName){
        this.moduleCategoryName = moduleCategoryName;
    }
    public Integer getSysModuleCategorySortNum(){
       return sysModuleCategorySortNum;
    }
    public void setSysModuleCategorySortNum(Integer sysModuleCategorySortNum){
        this.sysModuleCategorySortNum = sysModuleCategorySortNum;
    }

}
