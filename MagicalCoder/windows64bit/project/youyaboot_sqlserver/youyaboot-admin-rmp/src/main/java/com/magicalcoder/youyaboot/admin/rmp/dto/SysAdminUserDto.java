package com.magicalcoder.youyaboot.admin.rmp.dto;

import com.magicalcoder.youyaboot.admin.rmp.model.SysAdminUser;
import com.magicalcoder.youyaboot.admin.rmp.model.SysGlobalPermitUrl;
import com.magicalcoder.youyaboot.core.utils.ListUtil;
import com.magicalcoder.youyaboot.core.utils.MapUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 由于采取分布式session，存入redis 当此类被修改 就会触发jdk反序列化失败，除非redis下的session失效
 * 解决办法：自定义序列化 TODO
 */
public class SysAdminUserDto extends SysAdminUser implements UserDetails {
    private SysRoleDto sysRoleDto;
    List<SysGlobalPermitUrl> sysGlobalPermitUrlList;//全局配置
    public SysRoleDto getSysRoleDto() {
        return sysRoleDto;
    }

    public void setSysRoleDto(SysRoleDto sysRoleDto) {
        this.sysRoleDto = sysRoleDto;
    }
    //所以

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SysPermissionDto> auths = new ArrayList<>();
        return auths;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return getEnabled()==null ? false : getEnabled();
    }

    //仅仅获取指定moduleName下的所有权限(tableName)
    public Collection<? extends GrantedAuthority> findAllBackendPermissionList() {
        List<SysPermissionDto> auths = new ArrayList<>();
        if(sysRoleDto!=null){
            List<SysModuleCategoryDto> categoryDtoList = sysRoleDto.getSysModuleCategoryDtoList();
            if(ListUtil.isNotBlank(categoryDtoList)){
                for(SysModuleCategoryDto categoryDto:categoryDtoList){
                    List<SysModuleDto> moduleDtoList = categoryDto.getSysModuleDtoList();
                    if(ListUtil.isNotBlank(moduleDtoList)){
                        for(SysModuleDto sysModuleDto:moduleDtoList){
                            if(ListUtil.isNotBlank(sysModuleDto.getBackendOwnPermissionDtoList())){
                                auths.addAll(sysModuleDto.getBackendOwnPermissionDtoList());
                            }
                        }
                    }
                }
            }

        }
        //去重
        Map<Long,SysPermissionDto> authMap = MapUtil.listToItemMap("id",auths);
        return authMap.values();
    }

    public List<SysGlobalPermitUrl> getSysGlobalPermitUrlList() {
        return sysGlobalPermitUrlList;
    }

    public void setSysGlobalPermitUrlList(List<SysGlobalPermitUrl> sysGlobalPermitUrlList) {
        this.sysGlobalPermitUrlList = sysGlobalPermitUrlList;
    }
}
