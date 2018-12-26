package com.magicalcoder.youyaboot.admin.rmp.common.handler;

import com.magicalcoder.youyaboot.core.serialize.JsonOutWriter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * author:www.magicalcoder.com
 * date:2018/7/16
 * function:登陆成功处理器
 */
public class MyLoginSuccessHandler extends JsonOutWriter implements AuthenticationSuccessHandler{
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//        SysAdminUserDto adminUser = (SysAdminUserDto) authentication.getPrincipal();
//        adminUser.setPassword(null);
        toWebSuccess(httpServletResponse);
    }
}
