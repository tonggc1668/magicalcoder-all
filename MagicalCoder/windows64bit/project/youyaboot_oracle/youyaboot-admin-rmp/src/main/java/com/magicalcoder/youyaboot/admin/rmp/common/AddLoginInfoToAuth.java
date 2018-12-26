package com.magicalcoder.youyaboot.admin.rmp.common;

import com.magicalcoder.youyaboot.admin.rmp.dto.SysAdminUserDto;
import com.magicalcoder.youyaboot.admin.rmp.service.SecurityUserDetailService;
import com.magicalcoder.youyaboot.core.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * author:www.magicalcoder.com
 * date:2018/7/18
 * function:主动添加登陆信息
 */
@Component
public class AddLoginInfoToAuth {
    @Resource
    private SecurityUserDetailService securityUserDetailService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public void login(String username){
        if(StringUtil.isBlank(username) ){
            throw new BadCredentialsException("用户名不能为空");
        }
        SysAdminUserDto sysAdminUserDto = (SysAdminUserDto)securityUserDetailService.loadUserByUsername(username);
        if(sysAdminUserDto == null){
            throw new BadCredentialsException("管理员暂时未配置此用户名，请联系管理员");
        }
        if(!sysAdminUserDto.isEnabled()){
            throw new BadCredentialsException("此账号被禁用，请联系管理员");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(sysAdminUserDto,sysAdminUserDto.getPassword(), sysAdminUserDto.getAuthorities());
//        authenticationManager.authenticate(token);//不要打开 否则会重复loadUserByUsername
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    public void logout(){
        SecurityContextHolder.clearContext();
    }


}
