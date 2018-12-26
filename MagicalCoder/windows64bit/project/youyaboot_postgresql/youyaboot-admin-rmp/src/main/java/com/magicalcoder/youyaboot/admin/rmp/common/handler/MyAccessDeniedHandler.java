package com.magicalcoder.youyaboot.admin.rmp.common.handler;

import com.magicalcoder.youyaboot.admin.rmp.common.SecurityReturnCode;
import com.magicalcoder.youyaboot.core.serialize.JsonOutWriter;
import com.magicalcoder.youyaboot.core.serialize.KeyValuePair;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * author:www.magicalcoder.com
 * date:2018/7/17
 * function: 接收AccessDeniedException 业务自己抛出的异常 这里能处理
 */
public class MyAccessDeniedHandler extends JsonOutWriter implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        KeyValuePair keyValuePair = new KeyValuePair(SecurityReturnCode.NO_PERMISSION.getKey(),e.getMessage(),null);
        toWebFail(httpServletResponse,keyValuePair);
    }
}
