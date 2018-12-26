package com.magicalcoder.youyaboot.core.cache;

import com.alibaba.fastjson.JSON;
import com.magicalcoder.youyaboot.core.cache.redis.RedisUtil;
import com.magicalcoder.youyaboot.core.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 缓存切面
 * @author www.magicalcoder.com
 * 2018-4-4
 */
@Aspect
@Component
@Slf4j
public class CacheAround implements InitializingBean {
	@Resource
	private RedisUtil redisUtil;

	public static String buildKey(String targetClassName, String targetMethodName, Object ... args){
		StringBuilder cacheKey = new StringBuilder();
		if(StringUtil.isNotBlank(targetClassName)){
			cacheKey.append(targetClassName);
		}
		if(StringUtil.isNotBlank(targetMethodName)){
			cacheKey.append(".").append(targetMethodName);
		}
		cacheKey.append("(");
		if(args!=null && args.length>0){
			cacheKey.append(JSON.toJSONString(args));
		}
		cacheKey.append(")");
		return cacheKey.toString()+"";
	}

	//凡是方法上有此注解的将被拦截
	@Around("@annotation(com.magicalcoder.youyaboot.core.cache.CacheParam)")
	public Object around(ProceedingJoinPoint pj) throws Throwable{
		//目标方法名称
		MethodSignature signature = (MethodSignature)pj.getSignature();
		String targetMethodName = signature.getName();
		//目标类名称
		Class clazz = pj.getTarget().getClass();
		String targetClassName = clazz.getName();
		//目标方法参数
		Object[] args=pj.getArgs();
		//获取实现类方法
		Method method = clazz.getMethod(signature.getName(),signature.getParameterTypes());
		Class<?> returnType = method.getReturnType();
		CacheParam cacheParam = method.getAnnotation(CacheParam.class);
		int expireSecond = cacheParam.expireSecond();
		if(expireSecond<=0){//没有设置缓存 设置缓存错误
			return pj.proceed();//直接执行完方法返回
		}else{
			String cacheKey = cacheParam.prefix();
			if(StringUtil.isBlank(cacheKey)){
				cacheKey= buildKey(targetClassName, targetMethodName, args);
			}else {
				cacheKey = buildKey(cacheParam.prefix(),null,args);
			}
			return dealCache(pj, cacheKey, expireSecond,returnType);
		}
	}

	private Object dealCache(ProceedingJoinPoint pj, String cacheKey, int expireSecond,Class<?> clazz){
		Object cacheObj;
		boolean cacheServerException=false;
		try{
			cacheObj = redisUtil.getObj(cacheKey,clazz);
			if(cacheObj!=null){
//				log.debug("get data from cache key="+cacheKey);
				return cacheObj;
			}
		}catch(Exception e){
			e.printStackTrace();
//			log.error("缓存服务器异常...");
			cacheServerException=true;
		}
		Object returnObject=null;
		//直接调用目标方法
		try {
			if(!cacheServerException){
				//Integer 缓存范围 -128,127 则返回同一个对象 所以基于此特性 尽可能实现多锁 最大并发255
//				Integer lockObj = cacheKey.hashCode() % 128;//-127,127 hashcode可能为负数
//				synchronized(lockObj){// lockObj不能用 如果开源jar中随便锁一个int 这里就被锁了 忽略锁吧 加锁性能更差
//					log.debug(cacheKey+"进入锁" + lockObj);
					/*cacheObj = redisUtil.getObj(cacheKey,clazz);
					if(cacheObj!=null){
//						log.debug(cacheKey+"退出锁-取缓存"+lockObj);
						return cacheObj;
					}*/
					returnObject = pj.proceed();
					redisUtil.setexObj(cacheKey, expireSecond, returnObject);
//					log.debug(cacheKey+"退出锁"+lockObj);
//				}
			}else {
				returnObject=pj.proceed();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return returnObject;
	}

    @Override
    public void afterPropertiesSet() {

    }

}
