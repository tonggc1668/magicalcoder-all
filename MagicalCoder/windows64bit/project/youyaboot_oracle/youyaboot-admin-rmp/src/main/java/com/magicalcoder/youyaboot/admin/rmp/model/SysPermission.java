package com.magicalcoder.youyaboot.admin.rmp.model;

import java.io.Serializable;

/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

public class SysPermission implements Serializable{

    private Long id;//主键
    private String filterPlatform;//过滤端:front|backend
    private String permissionName;//过滤器名称:审核通过
    private String backendUrlReg;//后端的地址正则,校验当前请求url是否有权限,get|post统一会按照参数首字母排序
    private String frontDom;//前端dom获取
    private String frontAction;//前端处理方式hide|remove|disabled
    private Long moduleId;
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getFilterPlatform(){
        return filterPlatform;
    }
    public void setFilterPlatform(String filterPlatform){
        this.filterPlatform = filterPlatform;
    }
    public String getPermissionName(){
        return permissionName;
    }
    public void setPermissionName(String permissionName){
        this.permissionName = permissionName;
    }
    public String getBackendUrlReg(){
        return backendUrlReg;
    }
    public void setBackendUrlReg(String backendUrlReg){
        this.backendUrlReg = backendUrlReg;
    }
    public String getFrontDom(){
        return frontDom;
    }
    public void setFrontDom(String frontDom){
        this.frontDom = frontDom;
    }
    public String getFrontAction(){
        return frontAction;
    }
    public void setFrontAction(String frontAction){
        this.frontAction = frontAction;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }
}
