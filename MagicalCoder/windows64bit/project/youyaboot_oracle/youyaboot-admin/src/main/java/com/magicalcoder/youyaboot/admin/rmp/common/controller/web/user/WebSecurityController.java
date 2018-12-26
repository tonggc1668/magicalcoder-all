package com.magicalcoder.youyaboot.admin.rmp.common.controller.web.user;

import com.magicalcoder.youyaboot.admin.rmp.constant.PermissionConstant;
import com.magicalcoder.youyaboot.core.cache.redis.RedisUtil;
import com.magicalcoder.youyaboot.core.identifyingcode.CreateIdentifyingCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * author: www.magicalcoder.com
 * date:2018/7/10
 * function:
 */
@Controller
public class WebSecurityController {
    @RequestMapping(value = "/")
    public String index(HttpServletRequest request, ModelMap modelMap){
        String sessionId = request.getSession().getId();
        modelMap.addAttribute("sessionId",sessionId);
        return "login";
    }
    @Resource
    private RedisUtil redisUtil;

    //验证码
    @Resource
    private CreateIdentifyingCode createIdentifyingCode;
    @RequestMapping(value = "/web/code")
    public void code(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String random =createIdentifyingCode.create(response);
        String sessionId = request.getSession().getId();
        redisUtil.set(PermissionConstant.CODE_PREFIX+sessionId,random);
    }

}
