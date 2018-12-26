package com.magicalcoder.youyaboot.admin.rmp.common.handler;

import com.magicalcoder.youyaboot.admin.rmp.common.SecurityReturnCode;
import com.magicalcoder.youyaboot.core.serialize.JsonOutWriter;
import com.magicalcoder.youyaboot.core.serialize.KeyValuePair;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * author:www.magicalcoder.com
 * date:2018/7/16
 * function:登陆失败处理器
 */
public class MyLoginFailHandler extends JsonOutWriter implements AuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        toWebFail(httpServletResponse, new KeyValuePair(SecurityReturnCode.LOGIN_FAIL.getKey(),e.getMessage(),null));
    }
}
