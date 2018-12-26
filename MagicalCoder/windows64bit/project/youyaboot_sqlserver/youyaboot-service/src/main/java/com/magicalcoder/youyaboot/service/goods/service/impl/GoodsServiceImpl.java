package com.magicalcoder.youyaboot.service.goods.service.impl;

import com.magicalcoder.youyaboot.service.goods.mapper.GoodsMapper;
import com.magicalcoder.youyaboot.service.goods.service.GoodsService;
import com.magicalcoder.youyaboot.model.Goods;
import com.magicalcoder.youyaboot.core.service.CommonServiceImpl;
import com.magicalcoder.youyaboot.core.utils.StringUtil;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.magicalcoder.youyaboot.core.utils.CopyUtil;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.math.*;
/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/

@Service
public class GoodsServiceImpl extends CommonServiceImpl<Goods,Long> implements GoodsService,InitializingBean{
    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public void afterPropertiesSet() {
        super.commonMapper = goodsMapper;
    }
}
