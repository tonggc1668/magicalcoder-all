package com.magicalcoder.youyaboot.core.service;

import com.magicalcoder.youyaboot.core.common.exception.BusinessException;
import com.magicalcoder.youyaboot.core.serialize.ResponseMsg;
import com.magicalcoder.youyaboot.core.utils.ReflectUtil;
import com.magicalcoder.youyaboot.core.utils.StringUtil;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.Set;

/**
 *
 * date:2018/9/7
 * function: 封装controller的一些通用方法
 */
public class CommonRestController<E,P> {
    public ICommonService<E,P> commonService;

    public String primaryKey;//主键名称

    //完全为了解决layui的where条件不支持动态改变值，会记住上一次的参数值
    public String coverBlankToNull(String input){
        if("".equals(input)){
            return null;
        }
        return input;
    }

    @RequestMapping(value="get/{id}", method={RequestMethod.GET})
    public ResponseMsg get(@PathVariable P id) {
        return new ResponseMsg(this.commonService.getModel(id));
    }

    @RequestMapping(value="save", method={RequestMethod.POST})
    public ResponseMsg save(@ModelAttribute E entity) {
        ReflectUtil<P,E> util = new ReflectUtil<>();
        P id = util.getBeanValue(entity,primaryKey);
        if(id instanceof String){
            if(StringUtil.isBlank((String)id)){
                id = null;
            }
        }
        if(id==null){
            /*autoSetCreateTime(entity);
            autoSetUpdateTime(entity);*/
            this.commonService.insertModel(entity);
        }else{
            E dbEntity = this.commonService.getModelNotIgnore(id,primaryKey);
            if(dbEntity==null){//
                /*autoSetCreateTime(entity);
                autoSetUpdateTime(entity);*/
                this.commonService.insertModel(entity);
            }else {
                /*autoSetUpdateTime(entity);*/
                this.commonService.updateModel(entity);
            }
        }
        return new ResponseMsg();
    }

    private void autoSetCreateTime(E entity){
        ReflectUtil<Timestamp,E> timeReflect = new ReflectUtil<>();
        try {//尝试下更新创建时间
            timeReflect.setBeanValue(entity,Timestamp.class,"createTime",new Timestamp(System.currentTimeMillis()));
        }catch (Exception e){
            //忽略日志
        }
    }
    private void autoSetUpdateTime(E entity){
        ReflectUtil<Timestamp,E> timeReflect = new ReflectUtil<>();
        try {//尝试下更新更改时间
            timeReflect.setBeanValue(entity,Timestamp.class,"updateTime",new Timestamp(System.currentTimeMillis()));
        }catch (Exception e){
            //忽略异常 有些场景没有时间字段 没必要报错
        }
    }

    //虽然id会智能赋值但是
    @RequestMapping(value="update/{magicalCoderId}", method={RequestMethod.POST})
    public ResponseMsg update(@PathVariable P magicalCoderId,@ModelAttribute E entity) {
        if(magicalCoderId==null){
            throw new BusinessException(null);
        }
//设置主键
        Class<P> clazz = (Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        ReflectUtil<P,E> util = new ReflectUtil<>();
        util.setBeanValue(entity,clazz,primaryKey,magicalCoderId);
        this.commonService.updateModelWithoutNull(entity);
        return new ResponseMsg();
    }

    @RequestMapping(value="delete/{id}", method={RequestMethod.POST})
    public ResponseMsg delete(@PathVariable P id) {
        this.commonService.deleteModel(id);
        return new ResponseMsg();
    }

    @RequestMapping(value="batch_delete" ,method={RequestMethod.POST})
    public ResponseMsg batchDelete(@RequestParam(value = "ids[]") Set<P> ids) {//ids是前端写死的入参 请不要改动
        this.commonService.deleteModel(ids);
        return new ResponseMsg();
    }

}
