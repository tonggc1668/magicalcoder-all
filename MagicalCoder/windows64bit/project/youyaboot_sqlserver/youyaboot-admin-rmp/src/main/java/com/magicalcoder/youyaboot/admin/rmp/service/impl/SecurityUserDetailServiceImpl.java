package com.magicalcoder.youyaboot.admin.rmp.service.impl;

import com.magicalcoder.youyaboot.admin.rmp.constant.PermissionConstant;
import com.magicalcoder.youyaboot.admin.rmp.dto.*;
import com.magicalcoder.youyaboot.admin.rmp.model.*;
import com.magicalcoder.youyaboot.admin.rmp.service.*;
import com.magicalcoder.youyaboot.core.utils.CopyUtil;
import com.magicalcoder.youyaboot.core.utils.ListUtil;
import com.magicalcoder.youyaboot.core.utils.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * author: www.magicalcoder.com
 * date:2018/7/10
 * function:
 */
@Service
@Slf4j
public class SecurityUserDetailServiceImpl implements SecurityUserDetailService {
    @Resource
    private SysAdminUserService sysAdminUserService;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysRoleModulePermissionService sysRoleModulePermissionService;
    @Resource
    private SysPermissionService sysPermissionService;
    @Resource
    private SysModuleService sysModuleService;
    @Resource
    private SysModuleCategoryService sysModuleCategoryService;
    @Resource
    private SysGlobalPermitUrlService sysGlobalPermitUrlService;
    @Override
    public SysAdminUserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        SysAdminUser sysAdminUser = sysAdminUserService.selectOneModelWillThrowException(MapUtil.buildMap("username",username));
        SysAdminUserDto sysAdminUserDto = null;
        if(sysAdminUser!=null){
            sysAdminUserDto = new SysAdminUserDto();
            CopyUtil.copy(sysAdminUser,sysAdminUserDto);
            Long roleId = sysAdminUser.getRoleId();
            sysAdminUserDto.setSysRoleDto(getRoleDto(roleId));
            List<SysGlobalPermitUrl> globalUrls = sysGlobalPermitUrlService.getModelList(null);
            sysAdminUserDto.setSysGlobalPermitUrlList(globalUrls);
        }
        return sysAdminUserDto;
    }

    private SysRoleDto getRoleDto(Long roleId){
        List<SysRoleModulePermission> sysRoleModulePermissionList = sysRoleModulePermissionService.getModelList(MapUtil.buildMap("roleId",roleId));
        SysRole sysRole = sysRoleService.getModel(roleId);
        if(sysRole!=null){
            SysRoleDto sysRoleDto = new SysRoleDto();
            CopyUtil.copy(sysRole,sysRoleDto);
            Set<Long> moduleIds = moduleIds(sysRoleModulePermissionList);
            List<SysModuleDto> moduleDtoList = getModuleDtoList(moduleIds,sysRoleModulePermissionList);
            sysRoleDto.setSysModuleCategoryDtoList(getModuleCategoryDtoList(moduleDtoList));
            return sysRoleDto;
        }
        return null;
    }

    //获取分类列表 同时赋值子列表
    private List<SysModuleCategoryDto> getModuleCategoryDtoList(List<SysModuleDto> moduleDtoList){
        List<SysModuleCategoryDto> copyCategoryDtoList = null;
        if(ListUtil.isNotBlank(moduleDtoList)){
            Set<Long> categoryIds = new HashSet<>();
            for(SysModuleDto moduleDto:moduleDtoList){
                categoryIds.add(moduleDto.getModuleCategoryId());
            }
            List<SysModuleCategory> categoryList = sysModuleCategoryService.getModelInList(categoryIds);
            Collections.sort(categoryList, new Comparator<SysModuleCategory>() {
                @Override
                public int compare(SysModuleCategory o1, SysModuleCategory o2) {
                    return o1.getSortNum().compareTo(o2.getSortNum());
                }
            });
            if(ListUtil.isNotBlank(categoryList)){
                Map<Long,List<SysModuleDto>> listMap = MapUtil.listToListMap("moduleCategoryId",moduleDtoList);
                copyCategoryDtoList = CopyUtil.copy(categoryList,SysModuleCategoryDto.class);
                for(SysModuleCategoryDto copy:copyCategoryDtoList){
                    copy.setSysModuleDtoList(listMap.get(copy.getId()));
                }
            }
        }
        return copyCategoryDtoList;
    }

    private List<SysModuleDto> getModuleDtoList(Set<Long> moduleIds,List<SysRoleModulePermission> sysRoleModulePermissionList){
        List<SysModuleDto> moduleDtoList = new ArrayList<>(moduleIds.size());
        List<SysModule> moduleList = sysModuleService.getModelInList(moduleIds);
        if(moduleList!=null){
            Collections.sort(moduleList, new Comparator<SysModule>() {
                @Override
                public int compare(SysModule o1, SysModule o2) {
                    return o1.getSortNum().compareTo(o2.getSortNum());
                }
            });
            for(SysModule sysModule:moduleList){
                SysModuleDto sysModuleDto = new SysModuleDto();
                CopyUtil.copy(sysModule,sysModuleDto);
                Long moduleId = sysModule.getId();
                //当前模块有哪些权限
                List<SysPermissionDto> allPermissionDtoList = getAllPermissionDtoList(moduleId);
                if(ListUtil.isNotBlank(allPermissionDtoList)){
                    //当前角色下当前模块拥有哪些权限
                    Set<Long> permissionIds = permissionIds(moduleId,sysRoleModulePermissionList);
                    Map<Long,SysPermissionDto> permissionDtoMap = MapUtil.listToItemMap("id",allPermissionDtoList);
                    for(Long permissionId:permissionIds){
                        SysPermissionDto has = permissionDtoMap.get(permissionId);
                        if(has!=null){//标记下前端需要的有权限的状态
                            has.setHasPermission(true);
                        }
                    }
                    //前端要求的数据 有权限和无权限的都返回 用一个字段标记
                    sysModuleDto.setSysPermissionDtoList(allPermissionDtoList);
                    //后端要求的数据 仅放入当前角色有权限的数据 无权限的过滤干净
                    List<SysPermissionDto> backendList = filterPermissionList(PermissionConstant.Platform.BACKEND,PermissionConstant.Platform.FRONT_BACKEND,allPermissionDtoList);
                    for(int i=0;i<backendList.size();i++){
                        SysPermissionDto dto = backendList.get(i);
                        if(!dto.isHasPermission()){
                            backendList.remove(i--);
                        }
                    }
                    sysModuleDto.setBackendOwnPermissionDtoList(backendList);
                }

                moduleDtoList.add(sysModuleDto);
            }
        }
        return moduleDtoList;
    }

    //获取某个模块下配置的所有权限
    private List<SysPermissionDto> getAllPermissionDtoList(Long moduleId){
        List<SysPermission> permissionList = sysPermissionService.getModelList(MapUtil.buildMap("moduleId",moduleId));
        return CopyUtil.copy(permissionList,SysPermissionDto.class);
    }

    private Set<Long> moduleIds(List<SysRoleModulePermission> sysRoleModulePermissionList){
        Set<Long> moduleIds = new HashSet<>();//保证去重
        for(SysRoleModulePermission item:sysRoleModulePermissionList) {
            moduleIds.add(item.getModuleId());
        }
        return moduleIds;
    }
    private Set<Long> permissionIds(Long moduleId,List<SysRoleModulePermission> sysRoleModulePermissionList){
        Set<Long> permissionIds = new HashSet<>();//保证去重
        for(SysRoleModulePermission item:sysRoleModulePermissionList) {
            if(item.getModuleId().compareTo(moduleId)==0){
                permissionIds.add(item.getPermissionId());
            }
        }
        return permissionIds;
    }

    //主要是为了合并双端
    private List<SysPermissionDto> filterPermissionList(PermissionConstant.Platform platform, PermissionConstant.Platform orPlatform, List<SysPermissionDto> allList){
        if(ListUtil.isNotBlank(allList)){
            List<SysPermissionDto> backendList = new ArrayList<>();
            for(SysPermissionDto sysPermissionDto:allList){
                if(sysPermissionDto.getFilterPlatform().equals(platform.getValue()) || sysPermissionDto.getFilterPlatform().equals(orPlatform.getValue())){
                    backendList.add(sysPermissionDto);
                }
            }
            return backendList;
        }
        return null;
    }

}
