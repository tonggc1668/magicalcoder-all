package com.magicalcoder.youyaboot.admin.rmp.common.controller.admin.sys;

import com.magicalcoder.youyaboot.admin.rmp.common.SecurityReturnCode;
import com.magicalcoder.youyaboot.admin.rmp.model.SysAdminUser;
import com.magicalcoder.youyaboot.admin.rmp.service.SysAdminUserService;
import com.magicalcoder.youyaboot.core.common.constant.PageConstant;
import com.magicalcoder.youyaboot.core.common.exception.BusinessException;
import com.magicalcoder.youyaboot.core.serialize.ResponseMsg;
import com.magicalcoder.youyaboot.core.service.CommonRestController;
import com.magicalcoder.youyaboot.core.utils.ListUtil;
import com.magicalcoder.youyaboot.core.utils.Md5Util;
import com.magicalcoder.youyaboot.core.utils.StringUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
* 代码为自动生成 Created by www.magicalcoder.com
* 欢迎加入官方QQ群:323237052
*/

@RequestMapping("/admin/sys_admin_user_rest/")
@RestController
public class AdminSysAdminUserRestController extends CommonRestController<SysAdminUser,Long> implements InitializingBean
{

    @Resource
    private SysAdminUserService sysAdminUserService;
    @RequestMapping(value = "search")
    public ResponseMsg search(
        @RequestParam(required = false) String uniqueField,
        @RequestParam(required = false) Long uniqueValue,
        @RequestParam(required = false,defaultValue = "20") Integer limit,
        @RequestParam(required = false) String keyword
    ){
        limit = Math.min(PageConstant.MAX_LIMIT,limit);
        List<SysAdminUser> list = null;
        Map<String,Object> query = new HashedMap();
        query.put("limit",limit);
        if(uniqueValue!=null){//说明是来初始化的
            query.put(uniqueField,uniqueValue);
            list = sysAdminUserService.getModelList(query);
        }else {//正常搜索
            if(ListUtil.isBlank(list)){
                query.put("usernameFirst",keyword);
                list = sysAdminUserService.getModelList(query);
                query.remove("usernameFirst");
            }
        }
        return new ResponseMsg(list);
    }
    //分页查询
    @RequestMapping(value={"page"}, method={RequestMethod.GET})
    public ResponseMsg page(
        @RequestParam(required = false,value ="usernameFirst")                            String usernameFirst ,
        @RequestParam(required = false,value ="roleIdFirst")                            Long roleIdFirst ,
        @RequestParam int page,@RequestParam int limit,@RequestParam(required = false) String safeOrderBy)
    {
        limit = Math.min(limit, PageConstant.MAX_LIMIT);
        int start = (page - 1) * limit;
        Map<String,Object> query = new HashedMap();
        query.put("usernameFirst",coverBlankToNull(usernameFirst));
        query.put("roleIdFirst",roleIdFirst);
        Integer count = sysAdminUserService.getModelListCount(query);
        query.put("start",start);
        query.put("limit",limit);
        query.put("safeOrderBy",safeOrderBy);
        return new ResponseMsg(count,sysAdminUserService.getModelList(query));
    }

    @RequestMapping(value="save", method={RequestMethod.POST})
    public ResponseMsg save(@ModelAttribute SysAdminUser entity) {
        /*{保留我*/
        if(StringUtil.isBlank(entity.getPassword())){
            throw new BusinessException(SecurityReturnCode.PASSWORD_NULL);
        }
        if(!entity.getPassword().matches("[a-z0-9]{32}")){//非系统生成的密码进行加密
            entity.setPassword(Md5Util.md5Encode(entity.getPassword()));
        }
        /*}*/
        if(entity.getId()==null){
            this.sysAdminUserService.insertModel(entity);
        }else{
            this.sysAdminUserService.updateModel(entity);
        }
        return new ResponseMsg();
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        super.commonService = sysAdminUserService;
        super.primaryKey = "id";//硬编码此实体的主键名称
    }
}
