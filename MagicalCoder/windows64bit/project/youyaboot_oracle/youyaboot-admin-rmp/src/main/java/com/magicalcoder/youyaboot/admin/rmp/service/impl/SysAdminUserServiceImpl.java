
package com.magicalcoder.youyaboot.admin.rmp.service.impl;

import com.magicalcoder.youyaboot.admin.rmp.mapper.SysAdminUserMapper;
import com.magicalcoder.youyaboot.admin.rmp.model.SysAdminUser;
import com.magicalcoder.youyaboot.admin.rmp.service.SysAdminUserService;
import com.magicalcoder.youyaboot.core.service.CommonServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

@Service
public class SysAdminUserServiceImpl extends CommonServiceImpl<SysAdminUser,Long> implements SysAdminUserService,InitializingBean{
    @Resource
    private SysAdminUserMapper sysAdminUserMapper;



    @Override
    public void afterPropertiesSet() {
        super.commonMapper = sysAdminUserMapper;
    }
}
