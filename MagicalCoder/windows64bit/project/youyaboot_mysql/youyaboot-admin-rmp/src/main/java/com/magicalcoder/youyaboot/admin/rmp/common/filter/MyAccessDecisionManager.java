package com.magicalcoder.youyaboot.admin.rmp.common.filter;

import com.magicalcoder.youyaboot.admin.rmp.common.SortUrlBuilder;
import com.magicalcoder.youyaboot.admin.rmp.dto.SysAdminUserDto;
import com.magicalcoder.youyaboot.admin.rmp.dto.SysPermissionDto;
import com.magicalcoder.youyaboot.admin.rmp.model.SysGlobalPermitUrl;
import com.magicalcoder.youyaboot.admin.rmp.service.SysGlobalPermitUrlService;
import com.magicalcoder.youyaboot.core.serialize.JsonOutWriter;
import com.magicalcoder.youyaboot.core.utils.ListUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * author: www.magicalcoder.com
 * date:2018/7/11
 * function:权限判断
 */
@Service
public class MyAccessDecisionManager extends JsonOutWriter implements AccessDecisionManager {
    @Value("${magicalcoder.superUserName}")
    private String superUserName;
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        SysAdminUserDto sysAdminUserDto = (SysAdminUserDto)authentication.getPrincipal();
        if(sysAdminUserDto.getUsername().equals(superUserName)){//超级管理员无需验证权限
            return;
        }
        if(!sysAdminUserDto.isEnabled()){
            throw new AccessDeniedException("账号被禁用,请联系管理员");
        }
        FilterInvocation filterInvocation = (FilterInvocation)o;
        //相当地址
        HttpServletRequest request = filterInvocation.getHttpRequest();
        String servletPath = request.getServletPath();
        Map<String,String[]> reqMap = request.getParameterMap();
        SortUrlBuilder builder = new SortUrlBuilder(servletPath,reqMap);
        if(!hasPermission(sysAdminUserDto,builder.buildSortUrl(),request)){
            throw new AccessDeniedException("暂无权限,请联系管理员");//这个异常一定不能少 否则代码会继续路由到controller
        }
    }
    //为什么静态资源还是会进来呢
    private boolean hasPermission(SysAdminUserDto sysAdminUserDto,String reqUrl,HttpServletRequest request){
        if(sysAdminUserDto!=null){
            //优先匹配全局地址正则配置 所有角色都有此权限
            List<SysGlobalPermitUrl> globalUrls = sysAdminUserDto.getSysGlobalPermitUrlList();
            if(ListUtil.isNotBlank(globalUrls)){
                for(SysGlobalPermitUrl globalPermitUrl:globalUrls){
                    if(matches(reqUrl,globalPermitUrl.getBackendUrlReg())){
                        return true;
                    }
                }
            }
            //再细化匹配实际配置的后端地址权限
            Collection<SysPermissionDto> permissionDtoList = (Collection<SysPermissionDto>) sysAdminUserDto.findAllBackendPermissionList();
            if(permissionDtoList!=null){
                Iterator<SysPermissionDto> iterator = permissionDtoList.iterator();
                while (iterator.hasNext()){
                    SysPermissionDto permissionDto  = iterator.next();
                    if(matches(reqUrl,permissionDto.getBackendUrlReg())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean matches(String source,String target){
        if(source.startsWith("/")){//配置时如果忘记前缀 也兼容
            if(!target.startsWith("/")){
                target = "/"+target;
            }
        }
        if(source.equals(target)){
            return true;
        }
        if(source.matches(target)){
            return true;
        }
        return false;
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
