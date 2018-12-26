package com.magicalcoder.youyaboot.admin.rmp.util;

import com.magicalcoder.youyaboot.admin.rmp.dto.SysAdminUserDto;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * author:www.magicalcoder.com
 * date:2018/7/17
 * function:
 */
public class AdminUtil {

    public static SysAdminUserDto getAdmin(){
        SysAdminUserDto sysAdminUserDto = (SysAdminUserDto)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return sysAdminUserDto;
    }

    public static Long getAdminUid(){
        return getAdmin().getId();
    }

}
