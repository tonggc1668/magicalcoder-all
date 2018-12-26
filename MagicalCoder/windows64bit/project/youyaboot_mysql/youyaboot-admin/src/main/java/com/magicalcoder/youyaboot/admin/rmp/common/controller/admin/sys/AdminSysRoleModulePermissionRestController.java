package com.magicalcoder.youyaboot.admin.rmp.common.controller.admin.sys;

import com.magicalcoder.youyaboot.admin.rmp.model.SysRoleModulePermission;
import com.magicalcoder.youyaboot.admin.rmp.service.SysRoleModulePermissionService;
import com.magicalcoder.youyaboot.core.common.constant.PageConstant;
import com.magicalcoder.youyaboot.core.common.exception.BusinessException;
import com.magicalcoder.youyaboot.core.serialize.ResponseMsg;
import com.magicalcoder.youyaboot.core.service.CommonRestController;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;


/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

@RequestMapping("/admin/sys_role_module_permission_rest/")
@RestController
public class AdminSysRoleModulePermissionRestController extends CommonRestController<SysRoleModulePermission,Long> implements InitializingBean
{

    @Resource
    private SysRoleModulePermissionService sysRoleModulePermissionService;
    //分页查询
    @RequestMapping(value={"page"}, method={RequestMethod.GET})
    public ResponseMsg page(
        @RequestParam(required = false,value ="roleIdFirst")                            Long roleIdFirst ,
        @RequestParam(required = false,value ="moduleIdFirst")                            Long moduleIdFirst ,
        @RequestParam(required = false,value ="permissionIdFirst")                            Long permissionIdFirst ,
        @RequestParam int page,@RequestParam int limit,@RequestParam(required = false) String safeOrderBy)
    {
        limit = Math.min(limit, PageConstant.MAX_LIMIT);
        int start = (page - 1) * limit;
        Map<String,Object> query = new HashedMap();
        query.put("roleIdFirst",roleIdFirst);
        query.put("moduleIdFirst",moduleIdFirst);
        query.put("permissionIdFirst",permissionIdFirst);
        Integer count = sysRoleModulePermissionService.getModelListCount(query);
        query.put("start",start);
        query.put("limit",limit);
        query.put("safeOrderBy",safeOrderBy);
        return new ResponseMsg(count,sysRoleModulePermissionService.getModelList(query));
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        super.commonService = sysRoleModulePermissionService;
        super.primaryKey = "id";//硬编码此实体的主键名称
    }
}
