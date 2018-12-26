package com.magicalcoder.youyaboot.core.cache;

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
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 缓存删除切面
 * @author www.magicalcoder.com
 * 2018-6-29
 */
@Aspect
@Component
@Slf4j
public class CacheDeleteAround implements InitializingBean {
	@Resource
	private RedisUtil redisUtil;

	//凡是方法上有此注解的将被拦截
	@Around("@annotation(com.magicalcoder.youyaboot.core.cache.CacheDeleteParam)")
	public Object around(ProceedingJoinPoint pj) throws Throwable{
		//目标方法名称
		MethodSignature signature = (MethodSignature)pj.getSignature();
		String targetMethodName = signature.getName();
		//目标类名称
		Class clazz = pj.getTarget().getClass();
		String targetClassName = clazz.getName();
		//目标方法参数
		Object[] args=pj.getArgs();
		//得到被CacheParam注解的方法名称
		String[] arr = findCacheParamTargetMethodName(clazz,targetMethodName,signature.getParameterTypes());
		String cacheParamKey = CacheKeyUtil.buildKey(arr[0],targetClassName,arr[1],args);

		String uuid = UUID.randomUUID().toString();
		String lockCacheKey = CacheKeyUtil.buildLockCacheKey(cacheParamKey);
		ReentrantLock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		try {
			lock.lock();
			while (!redisUtil.tryDistributeLock(lockCacheKey,uuid,2000)){//不必担心cpu100%
//				log.debug(Thread.currentThread().getName()+"获取锁失败等待200ms");
				condition.await(200, TimeUnit.MILLISECONDS);
//				log.debug(Thread.currentThread().getName()+"继续重新获取锁");
			}
//			log.debug(Thread.currentThread().getName()+"获取锁成功准备删除缓存");
			redisUtil.del(cacheParamKey);
//			log.debug(Thread.currentThread().getName()+"准备退出锁");
		}finally {
			redisUtil.releaseDistributeLock(lockCacheKey,uuid);
			lock.unlock();
		}

		return null;
	}

	private String[] findCacheParamTargetMethodName(Class<?> tClass,String targetMethodName,Class[] paramTypes){
		char[] methodNames = targetMethodName.toCharArray();
		int len = 0;
		for(char m:methodNames){
			if(m >= 'A' && m <= 'Z'){//找到第一个驼峰式大写字母
				break;
			}
			len++;
		}
		String cacheParamTargetMethodName;
		if(len == 0){
			throw new RuntimeException("CacheDeleteParam注解的方法名称不符合规范：方法首字母不能大写-参考xxxMethod");
		}else if(len == methodNames.length){
			throw new RuntimeException("CacheDeleteParam注解的方法名称不符合规范：未出现驼峰式大写字母-参考xxxMethod");
		}else {
			char[] cacheParamTargetMethodNames = new char[methodNames.length-len];
			System.arraycopy(methodNames,len,cacheParamTargetMethodNames,0,cacheParamTargetMethodNames.length);
			//首字母小写
			cacheParamTargetMethodNames[0] = (char)(cacheParamTargetMethodNames[0] + 32);
			cacheParamTargetMethodName = new String(cacheParamTargetMethodNames);
		}
		if(StringUtil.isBlank(cacheParamTargetMethodName)){
			throw new RuntimeException("CacheDeleteParam注解的方法名称未找到对应CacheParam注解的方法:null");
		}
		String prefix = null;
		try {
			Method method = tClass.getMethod(cacheParamTargetMethodName,paramTypes);
			CacheParam cacheParam = method.getAnnotation(CacheParam.class);
			if(cacheParam==null){
				log.info(cacheParamTargetMethodName+"未找到@CacheParam注解");
			}else {
				prefix = cacheParam.prefix();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new RuntimeException("CacheDeleteParam注解的方法名称未映射到对应CacheParam注解的方法:请检查"+tClass.getName()+"."+cacheParamTargetMethodName+"是否存在");
		}
		return new String[]{prefix,cacheParamTargetMethodName};
	}

    @Override
    public void afterPropertiesSet() {

    }

}
