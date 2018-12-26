package com.magicalcoder.youyaboot.admin.rmp.common.provider;

import com.magicalcoder.youyaboot.admin.rmp.constant.PermissionConstant;
import com.magicalcoder.youyaboot.admin.rmp.dto.SysAdminUserDto;
import com.magicalcoder.youyaboot.admin.rmp.service.SecurityUserDetailService;
import com.magicalcoder.youyaboot.core.cache.redis.RedisUtil;
import com.magicalcoder.youyaboot.core.utils.Md5Util;
import com.magicalcoder.youyaboot.core.utils.StringUtil;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * author:www.magicalcoder.com
 * date:2018/7/16
 * function:自定义验证代码 可以同时拿到用户名 密码
 * 此类不再使用 页面跳转比较适合，但是这种联合登陆写的太死了
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    @Resource
    private SecurityUserDetailService securityUserDetailService;
    @Resource
    private RedisUtil redisUtil;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        //password = realPassword|||sessionId|||验证码 我十分不理解 spring security 真是个无语的存在 随便一个小扩展 得写一大堆类 这里就不扩展了
        String password = (String)authentication.getCredentials();
        if(StringUtil.isBlank(username)){
            throw new BadCredentialsException("用户名不能为空");
        }
        String[] parr = password.split("\\|\\|\\|");
        password = parr[0].trim();//密码
        String sessionId = parr[1];//
        String code = parr[2].trim().toLowerCase();//验证码
        String storeCode = redisUtil.get(PermissionConstant.CODE_PREFIX+sessionId).toLowerCase();
        if(!code.equals(storeCode)){
            throw new BadCredentialsException("验证码错误");
        }
        if(StringUtil.isBlank(password)){
            throw new BadCredentialsException("密码不能为空");
        }
        SysAdminUserDto sysAdminUserDto = (SysAdminUserDto)securityUserDetailService.loadUserByUsername(username);
        if(sysAdminUserDto == null){
            throw new BadCredentialsException("暂未配置此账号，请联系管理员");
        }
        String md5Password = Md5Util.md5Encode(password);
        if(!md5Password.equals(sysAdminUserDto.getPassword())){
            throw new BadCredentialsException("用户名密码不一致");
        }
        if(!sysAdminUserDto.isEnabled()){
            throw new BadCredentialsException("此账号被禁用，请联系管理员");
        }
        return new UsernamePasswordAuthenticationToken(sysAdminUserDto,password,sysAdminUserDto.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
