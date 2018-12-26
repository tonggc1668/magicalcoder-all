/**
 *
 */
package com.magicalcoder.youyaboot.web.common.interceptor;

import com.magicalcoder.youyaboot.core.cache.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后端判断拦截器
 * @author www.magicalcoder.com
 * @time 2017年6月26日-下午4:50:06
 */
@Component
@Slf4j
public class WebInterceptor extends HandlerInterceptorAdapter {

	@Resource
	private RedisUtil redisUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("utf-8");
        //做一些登录验证
        return true;
	}

}
