package com.magicalcoder.youyaboot.admin.rmp.common.point;

import com.magicalcoder.youyaboot.admin.rmp.common.SecurityReturnCode;
import com.magicalcoder.youyaboot.core.serialize.JsonOutWriter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * author:www.magicalcoder.com
 * date:2018/7/17
 * function: 自定义一下 当用户登陆超时出错 框架捕获了匿名用户访问接口后 调用这里 提示用户重新登录即可
 */
public class MyLoginExpiredAuthenticationEntryPoint extends JsonOutWriter implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        KeyValuePair keyValuePair = new KeyValuePair(SecurityReturnCode.RE_LOGIN.getKey(),authException.getMessage(),null);
        toWebFail(response,SecurityReturnCode.RE_LOGIN);
    }
}
