package com.magicalcoder.youyaboot.admin.rmp.common.controller.admin;

import com.magicalcoder.youyaboot.admin.rmp.common.controller.admin.res.ModuleRes;
import com.magicalcoder.youyaboot.admin.rmp.common.controller.admin.res.RmpTreeRes;
import com.magicalcoder.youyaboot.admin.rmp.dto.*;
import com.magicalcoder.youyaboot.admin.rmp.model.*;
import com.magicalcoder.youyaboot.admin.rmp.service.*;
import com.magicalcoder.youyaboot.admin.rmp.util.AdminUtil;
import com.magicalcoder.youyaboot.core.serialize.ResponseMsg;
import com.magicalcoder.youyaboot.core.utils.ListUtil;
import com.magicalcoder.youyaboot.core.utils.MapUtil;
import com.magicalcoder.youyaboot.core.utils.Md5Util;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * author:www.magicalcoder.com
 * date:2018/7/31
 * function:
 */
@RestController
@RequestMapping(value = "/admin/rest_rmp/")
public class AdminRmpRestController {
    @Resource
    private SysModuleCategoryService sysModuleCategoryService;

    @RequestMapping("check_password")
    public ResponseMsg checkPassword(String password){
        boolean check = Md5Util.md5Encode(password).equals(AdminUtil.getAdmin().getPassword());
        return new ResponseMsg(check);
    }

    /**
     * 获取某个模块的权限
     * @return
     */
    @RequestMapping(value = "get_permission_list")
    public ResponseMsg getPermissionList(@RequestParam(required = false) String moduleName){
        SysModuleDto dto = sysModuleDto(moduleName);
        return new ResponseMsg(dto);
    }

    private SysModuleDto sysModuleDto(String moduleName){
        //1、获取用户
        SysAdminUserDto userDto = AdminUtil.getAdmin();
        //2、获取用户角色
        SysRoleDto sysRoleDto = userDto.getSysRoleDto();
        if (sysRoleDto != null){
            //3、获取一级菜单
            List<SysModuleCategoryDto> categoryDtoList = sysRoleDto.getSysModuleCategoryDtoList();
            if (ListUtil.isNotBlank(categoryDtoList)){
                for (SysModuleCategoryDto categoryDto : categoryDtoList) {
                    //4、获取二级菜单
                    List<SysModuleDto> sysModuleDtoList = categoryDto.getSysModuleDtoList();
                    if (ListUtil.isNotBlank(sysModuleDtoList)){
                        for (SysModuleDto sysModuleDto : sysModuleDtoList) {
                            //5、获取权限
                            if(sysModuleDto.getModuleName()!=null){
                                if (sysModuleDto.getModuleName().equals(moduleName)) {
                                    return sysModuleDto;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 构造左侧菜单栏
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "get_module_list")
    public ResponseMsg getModuleList(ModelMap modelMap){
        SysAdminUserDto adminUser = AdminUtil.getAdmin();
        SysRoleDto role = adminUser.getSysRoleDto();
        modelMap.addAttribute("categoryList",role.getSysModuleCategoryDtoList());
        return new ResponseMsg(buildModuleMap());
    }
    private Map<String,List<ModuleRes>> buildModuleMap(){
        Map<String,List<ModuleRes>> listMap = new HashMap<>();
        SysAdminUserDto adminUser = AdminUtil.getAdmin();
        SysRoleDto role = adminUser.getSysRoleDto();
        if(role!=null){
            List<SysModuleCategoryDto> moduleCategoryDtoList = role.getSysModuleCategoryDtoList();
            if(ListUtil.isNotBlank(moduleCategoryDtoList)){
                for(SysModuleCategoryDto categoryDto:moduleCategoryDtoList){
                    String id = String.valueOf(categoryDto.getId());
                    List<ModuleRes> moduleResList = listMap.get(id);
                    if(moduleResList==null){
                        moduleResList = new ArrayList<>();
                        listMap.put(id,moduleResList);
                    }
                    for(SysModuleDto sysModuleDto : categoryDto.getSysModuleDtoList()){
                        ModuleRes res = cover(sysModuleDto);
                        if(res!=null){
                            moduleResList.add(res);
                        }
                    }
                }
            }
        }
        return listMap;
    }

    private ModuleRes cover(SysModuleDto sysModuleDto){
        if(sysModuleDto!=null && (sysModuleDto.getIfShow()!=null && sysModuleDto.getIfShow())){
            ModuleRes res = new ModuleRes();
            res.setSpread(false);
            res.setHref(sysModuleDto.getModuleUrl());
            res.setIcon("&#xe634;");
            res.setTitle(sysModuleDto.getModuleTitle());
            return res;
        }
        return null;
    }

    @Resource
    private SysModuleService sysModuleService;
    @Resource
    private SysPermissionService sysPermissionService;
    @Resource
    private SysRoleModulePermissionService sysRoleModulePermissionService;

    private static final String modulePrefix = "module_";
    private static final String permissionPrefix = "permission_";
    private static final String categoryPrefix = "category_";

    @RequestMapping(value = "tree_data/{roleId}")
    public ResponseMsg rmpTreeData(@PathVariable Long roleId){
        List<SysModuleCategory> categoryList = sysModuleCategoryService.getModelList(MapUtil.buildMap("safeOrderBy","sort_num asc"));
        List<SysModule> modelList = sysModuleService.getModelListNotIgnore(MapUtil.buildMap("safeOrderBy","sort_num asc"),"id","moduleTitle","moduleCategoryId");
        List<SysPermission> permissionList = sysPermissionService.getModelListNotIgnore(null,"id","permissionName","moduleId");
        List<SysRoleModulePermission> rmpList = sysRoleModulePermissionService.getModelList(MapUtil.buildMap("roleId",roleId));
        List<RmpTreeRes> rmpTreeResList = new ArrayList<>();
        if(ListUtil.isNotBlank(categoryList)){
            //第一层
            for(SysModuleCategory category:categoryList){
                RmpTreeRes rmpTreeRes = new RmpTreeRes();
                rmpTreeRes.setName(category.getModuleCategoryName());
                rmpTreeRes.setValue(categoryPrefix+String.valueOf(category.getId()));
                Rmp rmp = moduleList(rmpList,category.getId(),modelList,permissionList);
                rmpTreeRes.setList(rmp.rmpTreeResList);
                rmpTreeRes.setChecked(rmp.checked);
                rmpTreeResList.add(rmpTreeRes);

            }
        }
        return new ResponseMsg(rmpTreeResList);
    }
    private Rmp moduleList(List<SysRoleModulePermission> rmpList,Long categoryId,List<SysModule> modelList,List<SysPermission> permissionList){
        List<RmpTreeRes> rmpTreeResList = new ArrayList<>();
        boolean categoryChecked = false;
        if(ListUtil.isNotBlank(modelList)){
            Map<Long,SysRoleModulePermission> roleModulePermissionMap = MapUtil.listToItemMap("moduleId",rmpList);
            for(SysModule module:modelList){
                if(module.getModuleCategoryId().compareTo(categoryId)==0){
                    RmpTreeRes rmpTreeRes = new RmpTreeRes();
                    rmpTreeRes.setName(module.getModuleTitle());
                    rmpTreeRes.setValue(modulePrefix+String.valueOf(module.getId()));
                    Rmp rmp = permissionList(rmpList,module.getId(),permissionList);
                    rmpTreeRes.setList(rmp.rmpTreeResList);
                    boolean checked = roleModulePermissionMap.containsKey(module.getId());
                    rmpTreeRes.setChecked(checked || rmp.checked);
                    rmpTreeResList.add(rmpTreeRes);
                    if(!categoryChecked){
                        if (rmpTreeRes.isChecked()){
                            categoryChecked = true;
                        }
                    }
                }
            }
        }
        return new Rmp(rmpTreeResList,categoryChecked);
    }
    private Rmp permissionList(List<SysRoleModulePermission> rmpList,Long moduleId,List<SysPermission> permissionList){
        List<RmpTreeRes> rmpTreeResList = new ArrayList<>();
        boolean moduleChecked = false;
        if(ListUtil.isNotBlank(permissionList)){
            Map<Long,SysRoleModulePermission> roleModulePermissionMap = MapUtil.listToItemMap("permissionId",rmpList);
            for(SysPermission permission:permissionList){
                if(permission.getModuleId().compareTo(moduleId)==0){
                    RmpTreeRes rmpTreeRes = new RmpTreeRes();
                    rmpTreeRes.setName(permission.getPermissionName());
                    rmpTreeRes.setValue(permissionPrefix+String.valueOf(permission.getId()));
                    boolean checked = roleModulePermissionMap.containsKey(permission.getId());
                    rmpTreeRes.setChecked(checked);
                    rmpTreeResList.add(rmpTreeRes);
                    if(!moduleChecked){
                        if (rmpTreeRes.isChecked()){
                            moduleChecked = true;
                        }
                    }
                }
            }
        }
        return new Rmp(rmpTreeResList,moduleChecked);
    }

    private static final class Rmp{
        public Rmp(List<RmpTreeRes> rmpTreeResList, boolean checked) {
            this.rmpTreeResList = rmpTreeResList;
            this.checked = checked;
        }

        private List<RmpTreeRes> rmpTreeResList;
        private boolean checked;

        public List<RmpTreeRes> getRmpTreeResList() {
            return rmpTreeResList;
        }

        public void setRmpTreeResList(List<RmpTreeRes> rmpTreeResList) {
            this.rmpTreeResList = rmpTreeResList;
        }

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }
    }

    @RequestMapping(value = "save_tree_data/{roleId}", method={RequestMethod.POST})
    public ResponseMsg saveRmp(@PathVariable Long roleId,@RequestParam(value = "ids[]",required = false) Set<String> ids){
        sysRoleModulePermissionService.deleteModel(MapUtil.buildMap("roleId",roleId));
        if(ids!=null && ids.size()>0){
            Map<String,String> map = new HashMap<>();
            for(String id:ids){
                map.put(id,null);
            }
            List<SysModule> modelList = sysModuleService.getModelListNotIgnore(null,"id");
            if(ListUtil.isNotBlank(modelList)){
                for(int i=0;i<modelList.size();i++){
                    Long id = modelList.get(i).getId();
                    if(!map.containsKey(modulePrefix+id)){
                        modelList.remove(i--);
                    }
                }
            }

            List<SysPermission> permissionList = sysPermissionService.getModelListNotIgnore(null,"id","moduleId");
            if(ListUtil.isNotBlank(permissionList)){
                for(int i=0;i<permissionList.size();i++){
                    Long id = permissionList.get(i).getId();
                    if(!map.containsKey(permissionPrefix+id)){
                        permissionList.remove(i--);
                    }
                }
            }
            //
            if(ListUtil.isNotBlank(modelList)){
                List<SysRoleModulePermission> entityList = new ArrayList<>();
                Map<Long,List<SysPermission>> permissionMap = MapUtil.listToListMap("moduleId",permissionList);
                for(SysModule module:modelList){
                    List<SysPermission> permissions = permissionMap.get(module.getId());
                    if(ListUtil.isNotBlank(permissions)){
                        for(SysPermission permission:permissions){
                            SysRoleModulePermission entity = new SysRoleModulePermission();
                            entity.setRoleId(roleId);
                            entity.setModuleId(module.getId());
                            entity.setPermissionId(permission.getId());
                            entityList.add(entity);
                        }
                    }else {//仅仅勾选了菜单 未选择权限
                        SysRoleModulePermission entity = new SysRoleModulePermission();
                        entity.setRoleId(roleId);
                        entity.setModuleId(module.getId());
                        entityList.add(entity);
                    }
                }
                if(ListUtil.isNotBlank(entityList)){
                    sysRoleModulePermissionService.batchInsertModel(entityList);
                }
            }
        }
        return new ResponseMsg();
    }

}
