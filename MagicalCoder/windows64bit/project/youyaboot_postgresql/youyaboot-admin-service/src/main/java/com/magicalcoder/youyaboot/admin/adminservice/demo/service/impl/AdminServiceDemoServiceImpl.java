package com.magicalcoder.youyaboot.admin.adminservice.demo.service.impl;

import com.magicalcoder.youyaboot.admin.adminservice.demo.mapper.AdminServiceDemoMapper;
import com.magicalcoder.youyaboot.admin.adminservice.demo.service.AdminServiceDemoService;
import com.magicalcoder.youyaboot.admin.model.MyDemo;
import com.magicalcoder.youyaboot.core.utils.MapUtil;
import com.magicalcoder.youyaboot.model.Goods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceDemoServiceImpl  implements AdminServiceDemoService {
    @Resource
    private AdminServiceDemoMapper adminServiceDemoMapper;

    @Override
    public MyDemo demoGoods(Long id) {
        return adminServiceDemoMapper.daoDemoGoods(MapUtil.buildMap("id",id));
    }
}
