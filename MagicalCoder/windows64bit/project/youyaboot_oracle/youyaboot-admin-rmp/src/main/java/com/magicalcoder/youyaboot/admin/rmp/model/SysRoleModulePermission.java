package com.magicalcoder.youyaboot.admin.rmp.model;

import java.io.Serializable;

/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

public class SysRoleModulePermission implements Serializable{

    private Long id;//主键
    private Long roleId;//角色
    private Long moduleId;//模块
    private Long permissionId;//权限

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public Long getRoleId(){
        return roleId;
    }
    public void setRoleId(Long roleId){
        this.roleId = roleId;
    }
    public Long getModuleId(){
        return moduleId;
    }
    public void setModuleId(Long moduleId){
        this.moduleId = moduleId;
    }
    public Long getPermissionId(){
        return permissionId;
    }
    public void setPermissionId(Long permissionId){
        this.permissionId = permissionId;
    }

}
