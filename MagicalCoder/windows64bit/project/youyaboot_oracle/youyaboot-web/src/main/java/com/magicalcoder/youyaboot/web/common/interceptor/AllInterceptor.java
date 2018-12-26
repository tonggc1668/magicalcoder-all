/**
 *
 */
package com.magicalcoder.youyaboot.web.common.interceptor;

import com.magicalcoder.youyaboot.web.common.interceptor.limit.RequestRateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 所有请求拦截器
 * @author www.magicalcoder.com
 * @time 2017年6月26日-下午4:50:06
 */
@Component
@Slf4j
public class AllInterceptor extends HandlerInterceptorAdapter {
	@Resource
	private RequestRateLimiter requestRateLimiter;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("utf-8");
        //限流判断
//		requestRateLimiter.aquire(request);
        return true;
	}
}
