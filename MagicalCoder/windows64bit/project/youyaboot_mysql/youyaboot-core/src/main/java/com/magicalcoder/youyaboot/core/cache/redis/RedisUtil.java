package com.magicalcoder.youyaboot.core.cache.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis工具类
 *
 * @author www.magicalcoder.com
 * @date 2017-12-21
 */
@Component
public class RedisUtil extends RedisDefinedUtil{

	//为每个项目提供一个单独的前缀来区分 如果不填写默认给youyaboot.
	@Value("${spring.redis.keyPrefix:youyaboot.}")
	private String keyPrefix;

	/**
	 * 设置key-value对
	 *
	 * @param key
	 * @param value
	 */
	public boolean set(String key, String value) {
		return super.set(keyPrefix,key,value);
	}


	/**
	 * 设置key-value对，并设置过期时间（单位：s）
	 *
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public boolean setEx(String key, String value, int seconds) {
		return super.setEx(keyPrefix,key,value,seconds);
	}

	/**
	 * 根据key查询value
	 *
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return super.get(keyPrefix,key);
	}

	/**
	 * 删除key-value对
	 *
	 * @param key
	 */
	public void del(String key) {
		super.del(keyPrefix,key);
	}

	/**
	 * 对key对应的值减一
	 *
	 * @param key
	 * @return
	 */
	public long decr(String key) {
		return super.decr(keyPrefix,key);
	}

	/**
	 * 对key对应的值减num
	 *
	 * @param key
	 * @return
	 */
	public long decrBy(String key, long num) {
		return super.decrBy(keyPrefix,key,num);
	}

	/**
	 * 对key对应的值加num
	 *
	 * @param key
	 * @return
	 */
	public long incrBy(String key, long num) {
		return super.incrBy(keyPrefix,key,num);
	}
	/**
	* @Title: hGetAll
	* @Description: 获取redis hash结构对应key的值
	* @param key
	* @return Map<String,String>
	* @throws
	*/
	public Map<String,String> hgetAll(String key) {
		return super.hgetAll(keyPrefix,key);
	}
	/**
	* @Title: hSet
	* @Description: 存入redis hash结构
	* @param  key
	* @param  field
	* @param  value
	* @return
	* @throws
	*/
	public void hset(String key, String field, String value) {
		super.hset(keyPrefix,key,field,value);
	}

	/**
	* @Title: hGet
	* @Description: 获取对应hash下key的数据
	* @param  key
	* @param  field
	* @return String value
	* @throws
	*/
	public String hget(String key, String field){
		return super.hget(keyPrefix,key,field);
	}

	/**
	* @Title: hDel
	* @Description: redis hash结构删除key下对应field的值
	* @param  key
	* @param  fields
	* @return
	* @throws
	*/
	public void hdel(String key, String... fields){
		super.hdel(keyPrefix,key,fields);
	}

	/**
	 * @Title: HExists
	 * @Description: redis hash结构：查看哈希表 key 中，给定域 field 是否存在。
	 * @param  key
	 * @param  field
	 * @return boolean
	 * @throws
	 */
	public boolean hexists(String key, String field){
		return super.hexists(keyPrefix,key,field);
	}

	/**
	 * @Title: HExists
	 * @Description: redis hash结构：获取所有hash表的field
	 * @param  key
	 * @return boolean
	 * @throws
	 */
	public Set<String> hkeys(String key){
		return super.hkeys(keyPrefix,key);
	}

	public Long hincrby(String key, String field, Long count){
		return super.hincrby(keyPrefix,key,field,count);
	}

	/**
	 * Set集合添加数据
	 * @param key
	 * @param val
	 */
	public void sadd(String key, String val) {
		super.sadd(keyPrefix,key,val);
	}

	/**
	 * 设置
	 * @param key
	 * @param val
	 * @return
	 */
	public Long setnx(String key, String val) {
		return super.setnx(keyPrefix,key,val);
	}

	/**
	 * 设置过期时间
	 * @param key
	 * @param sec
	 */
	public void expire(String key, int sec) {
		super.expire(keyPrefix,key,sec);
	}

	/**
	 * 把java对象存入redis
	 * @author www.magicalcoder.com
	 * @param key
	 * @param obj
	 * @param expireSecond 过期时间
	 * @param <T>
	 */
    public <T> void setexObj(String key,int expireSecond,T obj){
		super.setexObj(keyPrefix,key,expireSecond,obj);
	}

	/**
	 * 从redis获取java对象
	 * @param key
	 * @param tClass
	 * @param <T>
	 * @return
	 */
	public <T> T getObj(String key,Class<T> tClass){
		return super.getObj(keyPrefix,key,tClass);
	}

	/**
	 * 是否存在key
	 * @param key
	 * @return
	 */
	public boolean exist(String key){
		return super.exist(keyPrefix,key);
	}

	/**
	 * redis简单分布式锁 最主要是一个命令执行
	 * @param key
	 * @param uuid UUID.randomUUID().toString() 解锁使用
	 * @param milliTime 毫秒
	 * @return
	 */
	public boolean tryDistributeLock(String key, String uuid, int milliTime){
		return super.tryDistributeLock(keyPrefix,key,uuid,milliTime);
	}

	/**
	 * 释放锁
	 * @param key
	 * @param uuid
	 * @return
	 */
	public void releaseDistributeLock(String key,String uuid){
		super.releaseDistributeLock(keyPrefix,key,uuid);
	}

	/**
	 * 获取指定键对应所有值的集合
	 * @param key
	 * @return
	 */
	public Set getValSet(String key){
		return super.getValSet(keyPrefix,key);
	}
	/**
	 * 获取指定键及对应条数值的集合
	 * @param key
	 * @param count
	 * @return
	 */
	public List<String> srandmber(String key,int count){
		return super.srandmber(keyPrefix,key,count);
	}


	/**
	 * 移除集合中的成员
	 * @param key
	 * @param value
	 * @return
	 */
	public void removeMem(String key,String value){
		super.removeMem(keyPrefix,key,value);
	}

	/**
	 * 在制定expireSeconds时间内 key自增的最大
	 * redis 执行lua脚本竟然不支持 >= <= 所以要改成> 或 == 或< 分开判断
	 * @param key
	 * @param expireSeconds
	 * @param maxTime
	 * @return
	 */
	public boolean tryIncr(String key,int expireSeconds,int maxTime){
		return super.tryIncr(keyPrefix,key,expireSeconds,maxTime);
	}

}
