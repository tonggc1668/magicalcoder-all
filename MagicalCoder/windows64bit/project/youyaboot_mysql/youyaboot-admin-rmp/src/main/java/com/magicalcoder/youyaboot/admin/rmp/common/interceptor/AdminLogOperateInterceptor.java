package com.magicalcoder.youyaboot.admin.rmp.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.magicalcoder.youyaboot.admin.rmp.dto.SysAdminUserDto;
import com.magicalcoder.youyaboot.admin.rmp.model.SysLogAdminOperate;
import com.magicalcoder.youyaboot.admin.rmp.service.SysLogAdminOperateService;
import com.magicalcoder.youyaboot.admin.rmp.util.AdminUtil;
import com.magicalcoder.youyaboot.admin.rmp.util.HttpReqUtil;
import com.magicalcoder.youyaboot.core.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 后端操作日志判断拦截器
 * @author www.magicalcoder.com
 */
@Component
@Slf4j
public class AdminLogOperateInterceptor extends HandlerInterceptorAdapter {

	@Resource
	private SysLogAdminOperateService sysLogAdminOperateService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("utf-8");
        //这里是为了记录用户的操作日志，至于最终用户有没有操作成功，是业务处理的问题，但是必须提前存好用户的操作行为
		String method = request.getMethod();
		if("post".equals(method.toLowerCase())){
			SysAdminUserDto sysAdminUserDto = AdminUtil.getAdmin();
			SysLogAdminOperate entity = new SysLogAdminOperate();
			entity.setAdminUserId(sysAdminUserDto.getId());
			if(StringUtil.isBlank(sysAdminUserDto.getRealName())){
				entity.setUserName(sysAdminUserDto.getUsername());
			}else {
				entity.setUserName(sysAdminUserDto.getRealName());
			}
			entity.setCreateTime(new Date());
			String body = JSON.toJSONString(request.getParameterMap());
			entity.setFormBody(body);
			String serverPath = request.getServletPath();
			entity.setUrl(serverPath);
			entity.setTableName(HttpReqUtil.moduleName(serverPath));
			entity.setPrimaryIdValue(request.getParameter("id"));//如果有就存id否则存null
			entity.setOperateType(HttpReqUtil.operateType(serverPath));
			sysLogAdminOperateService.insertModel(entity);
		}
        return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
