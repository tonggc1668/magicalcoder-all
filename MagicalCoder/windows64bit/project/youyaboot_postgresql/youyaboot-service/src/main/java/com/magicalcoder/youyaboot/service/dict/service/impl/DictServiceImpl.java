package com.magicalcoder.youyaboot.service.dict.service.impl;

import com.magicalcoder.youyaboot.service.dict.mapper.DictMapper;
import com.magicalcoder.youyaboot.service.dict.service.DictService;
import com.magicalcoder.youyaboot.model.Dict;
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
public class DictServiceImpl extends CommonServiceImpl<Dict,Long> implements DictService,InitializingBean{
    @Resource
    private DictMapper dictMapper;

    @Override
    public void afterPropertiesSet() {
        super.commonMapper = dictMapper;
    }
}
