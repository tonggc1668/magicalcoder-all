package com.magicalcoder.youyaboot.admin.rmp.dto;


import com.magicalcoder.youyaboot.admin.rmp.model.SysModule;

import java.util.List;
import java.util.Map;

public class SysModuleDto extends SysModule {
    private List<SysPermissionDto> backendOwnPermissionDtoList;//后端此模块下的角色拥有的权限列表
    private List<SysPermissionDto> sysPermissionDtoList;//前端此模块下的所有权限列表(拥有 不拥有 都返回 通过hspPermission区分)

    public List<SysPermissionDto> getBackendOwnPermissionDtoList() {
        return backendOwnPermissionDtoList;
    }

    public void setBackendOwnPermissionDtoList(List<SysPermissionDto> backendOwnPermissionDtoList) {
        this.backendOwnPermissionDtoList = backendOwnPermissionDtoList;
    }

    public List<SysPermissionDto> getSysPermissionDtoList() {
        return sysPermissionDtoList;
    }

    public void setSysPermissionDtoList(List<SysPermissionDto> sysPermissionDtoList) {
        this.sysPermissionDtoList = sysPermissionDtoList;
    }

}
