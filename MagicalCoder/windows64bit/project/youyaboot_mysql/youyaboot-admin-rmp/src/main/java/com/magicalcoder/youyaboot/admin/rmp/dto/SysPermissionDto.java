package com.magicalcoder.youyaboot.admin.rmp.dto;

import com.magicalcoder.youyaboot.admin.rmp.model.SysPermission;
import org.springframework.security.core.GrantedAuthority;

public class SysPermissionDto extends SysPermission implements GrantedAuthority {

    private boolean hasPermission;//是否拥有此权限

    @Override
    public String getAuthority() {
        return getPermissionName();
    }

    public boolean isHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(boolean hasPermission) {
        this.hasPermission = hasPermission;
    }
}
