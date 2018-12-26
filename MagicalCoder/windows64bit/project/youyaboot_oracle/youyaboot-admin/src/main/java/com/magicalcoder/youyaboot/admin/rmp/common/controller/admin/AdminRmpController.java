package com.magicalcoder.youyaboot.admin.rmp.common.controller.admin;

import com.magicalcoder.youyaboot.admin.rmp.dto.SysAdminUserDto;
import com.magicalcoder.youyaboot.admin.rmp.dto.SysRoleDto;
import com.magicalcoder.youyaboot.admin.rmp.util.AdminUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * author:www.magicalcoder.com
 * date:2018/7/31
 * function:
 */
@Controller
@RequestMapping(value = "/admin/")
public class AdminRmpController {

    @RequestMapping(value = "rmp/index")
    public String index(HttpServletRequest request,ModelMap modelMap){
        SysAdminUserDto adminUser = AdminUtil.getAdmin();
        SysRoleDto role = adminUser.getSysRoleDto();
        modelMap.addAttribute("categoryList",role.getSysModuleCategoryDtoList());
        return "index";
    }

    @RequestMapping(value = "rmp/main")
    public String main(){
        return "main";
    }

    //映射所有界面 这里统一路径功能一做，几乎就实现了前后端分离了 不再允许java端拼接大量参数给界面
    @RequestMapping(value = "page/{tableName}/{page}")
    public String mapping(@PathVariable String tableName,@PathVariable String page){
        String name = tableName.replaceAll("_","");
        return name +"/"+ name +"-"+ page;
    }

}
