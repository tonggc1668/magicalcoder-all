package com.magicalcoder.youyaboot.admin.rmp.dto;


import com.magicalcoder.youyaboot.admin.rmp.model.SysRole;

import java.util.List;

public class SysRoleDto extends SysRole {
    private List<SysModuleCategoryDto> sysModuleCategoryDtoList;

    public List<SysModuleCategoryDto> getSysModuleCategoryDtoList() {
        return sysModuleCategoryDtoList;
    }

    public void setSysModuleCategoryDtoList(List<SysModuleCategoryDto> sysModuleCategoryDtoList) {
        this.sysModuleCategoryDtoList = sysModuleCategoryDtoList;
    }
}
