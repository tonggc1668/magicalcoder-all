package com.magicalcoder.youyaboot.core.cache.redis;

import com.magicalcoder.youyaboot.core.protobuf.ProtostuffUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * redis工具类
 *
 * @author www.magicalcoder.com
 * @date 2017-12-21
 */
@Component
public class RedisDefinedUtil {

	@Autowired
	private JedisPool jedisPool;

	//包装key
	private String wrapKey(String keyPrefix,String key){
		return new StringBuilder(keyPrefix).append(key).toString();
	}

	/**
	 * 设置key-value对
	 *
	 * @param key
	 * @param value
	 */
	public boolean set(String keyPrefix,String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(wrapKey(keyPrefix,key), value);
			return true;
		} finally {
			// 返还到连接池
			closeJedis(jedis);
		}
	}


	/**
	 * 设置key-value对，并设置过期时间（单位：s）
	 *
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public boolean setEx(String keyPrefix,String key, String value, int seconds) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.setex(wrapKey(keyPrefix,key), seconds, value);
			return true;
		} finally {
			// 返还到连接池
			closeJedis(jedis);
		}
	}

	/**
	 * 根据key查询value
	 *
	 * @param key
	 * @return
	 */
	public String get(String keyPrefix,String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.get(wrapKey(keyPrefix,key));
		} finally {
			// 返还到连接池
			closeJedis(jedis);
		}
	}

	/**
	 * 删除key-value对
	 *
	 * @param key
	 */
	public void del(String keyPrefix,String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(wrapKey(keyPrefix,key));
		} finally {
			// 返还到连接池
			closeJedis(jedis);
		}
	}

	/**
	 * 对key对应的值减一
	 *
	 * @param key
	 * @return
	 */
	public long decr(String keyPrefix,String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.decr(wrapKey(keyPrefix,key));
		} finally {
			// 返还到连接池
			closeJedis(jedis);
		}
	}

	/**
	 * 对key对应的值减num
	 *
	 * @param key
	 * @return
	 */
	public long decrBy(String keyPrefix,String key, long num) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.decrBy(wrapKey(keyPrefix,key), num);
		} finally {
			// 返还到连接池
			closeJedis(jedis);
		}
	}

	/**
	 * 对key对应的值加num
	 *
	 * @param key
	 * @return
	 */
	public long incrBy(String keyPrefix,String key, long num) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.incrBy(wrapKey(keyPrefix,key), num);
		} finally {
			// 返还到连接池
			closeJedis(jedis);
		}
	}
	/**
	* @Title: hGetAll
	* @Description: 获取redis hash结构对应key的值
	* @param key
	* @return Map<String,String>
	* @throws
	*/
	public Map<String,String> hgetAll(String keyPrefix,String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hgetAll(wrapKey(keyPrefix,key));
		} finally {
			// 返还到连接池
			closeJedis(jedis);
		}
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
	public void hset(String keyPrefix,String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.hset(wrapKey(keyPrefix,key), field, value);
		} finally {
			// 返还到连接池
			closeJedis(jedis);
		}
	}

	/**
	* @Title: hGet
	* @Description: 获取对应hash下key的数据
	* @param  key
	* @param  field
	* @return String value
	* @throws
	*/
	public String hget(String keyPrefix,String key, String field){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			String value = jedis.hget(wrapKey(keyPrefix,key), field);
			return value;
		}finally {
			closeJedis(jedis);
		}
	}

	/**
	* @Title: hDel
	* @Description: redis hash结构删除key下对应field的值
	* @param  key
	* @param  fields
	* @return
	* @throws
	*/
	public void hdel(String keyPrefix,String key, String... fields){
		Jedis jedis = null;
		key = wrapKey(keyPrefix,key);
		try{
			jedis = jedisPool.getResource();
			if(fields.length == 0){
				jedis.hdel(key);
			}else {
				for (String field : fields) {
					jedis.hdel(key, field);
				}
			}
		}finally {
			closeJedis(jedis);
		}
	}

	/**
	 * @Title: HExists
	 * @Description: redis hash结构：查看哈希表 key 中，给定域 field 是否存在。
	 * @param  key
	 * @param  field
	 * @return boolean
	 * @throws
	 */
	public boolean hexists(String keyPrefix,String key, String field){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			return jedis.hexists(wrapKey(keyPrefix,key), field);
		}finally {
			closeJedis(jedis);
		}
	}

	/**
	 * @Title: HExists
	 * @Description: redis hash结构：获取所有hash表的field
	 * @param  key
	 * @return boolean
	 * @throws
	 */
	public Set<String> hkeys(String keyPrefix,String key){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
		    return jedis.hkeys(wrapKey(keyPrefix,key));
		}finally {
			closeJedis(jedis);
		}
	}

	public Long hincrby(String keyPrefix,String key, String field, Long count){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			return jedis.hincrBy(wrapKey(keyPrefix,key), field, count);
		}finally {
			closeJedis(jedis);
		}
	}

	/**
	 * Set集合添加数据
	 * @param key
	 * @param val
	 */
	public void sadd(String keyPrefix,String key, String val) {
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			jedis.sadd(wrapKey(keyPrefix,key), val);
		}finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 设置
	 * @param key
	 * @param val
	 * @return
	 */
	public Long setnx(String keyPrefix,String key, String val) {
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			return jedis.setnx(wrapKey(keyPrefix,key), val);
		} catch (Exception e) {
			return 0l;
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 设置过期时间
	 * @param key
	 * @param sec
	 */
	public void expire(String keyPrefix,String key, int sec) {
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			jedis.expire(wrapKey(keyPrefix,key), sec);
		}finally {
			closeJedis(jedis);
		}
	}
	private void closeJedis(Jedis jedis){
	    if(jedis != null){
	       jedis.close();
        }
    }

	/**
	 * 把java对象存入redis
	 * @author www.magicalcoder.com
	 * @param key
	 * @param obj
	 * @param expireSecond 过期时间
	 * @param <T>
	 */
    public <T> void setexObj(String keyPrefix,String key,int expireSecond,T obj){
		Jedis jedis = null;
		key = wrapKey(keyPrefix,key);
		try{
			jedis = jedisPool.getResource();
			if(obj == null){
				jedis.del(key);
				return;
			}
			byte[] bytes = ProtostuffUtil.serialize(obj);//序列化
			jedis.setex(key.getBytes("UTF-8"),expireSecond,bytes);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 从redis获取java对象
	 * @param key
	 * @param tClass
	 * @param <T>
	 * @return
	 */
	public <T> T getObj(String keyPrefix,String key,Class<T> tClass){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			byte[] bytes = jedis.get(wrapKey(keyPrefix,key).getBytes("UTF-8"));
			if(bytes!=null){
				return ProtostuffUtil.deserialize(bytes);//序列化
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			closeJedis(jedis);
		}
		return null;
	}

	/**
	 * 是否存在key
	 * @param key
	 * @return
	 */
	public boolean exist(String keyPrefix,String key){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			return jedis.exists(wrapKey(keyPrefix,key));
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * redis简单分布式锁 最主要是一个命令执行
	 * @param key
	 * @param uuid UUID.randomUUID().toString() 解锁使用
	 * @param milliTime 毫秒
	 * @return
	 */
	public boolean tryDistributeLock(String keyPrefix,String key, String uuid, int milliTime){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			String result = jedis.set(wrapKey(keyPrefix,key),uuid,"NX","PX",milliTime);
//			System.out.println(jedis.ttl(key));
//			jedis.expire(key,10);
//			System.out.println(jedis.ttl(key));
			if ("OK".equals(result)) {
				return true;
			}
		} finally {
			closeJedis(jedis);
		}
		return false;
	}

	/**
	 * 释放锁
	 * @param key
	 * @param uuid
	 * @return
	 */
	public void releaseDistributeLock(String keyPrefix,String key,String uuid){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			String redisScript = "if redis.call('get', KEYS[1]) == ARGV[1] then redis.call('del', KEYS[1]) end";
			jedis.eval(redisScript,Collections.singletonList(wrapKey(keyPrefix,key)),Collections.singletonList(uuid));
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 获取指定键对应所有值的集合
	 * @param key
	 * @return
	 */
	public Set getValSet(String keyPrefix,String key){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Set<String> valueSet = jedis.smembers(wrapKey(keyPrefix,key));
			return valueSet;
		}finally {
			closeJedis(jedis);
		}
	}
	/**
	 * 获取指定键及对应条数值的集合
	 * @param key
	 * @param count
	 * @return
	 */
	public List<String> srandmber(String keyPrefix,String key,int count){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			List<String> valueSet = jedis.srandmember(wrapKey(keyPrefix,key),count);
			return valueSet;
		}finally {
			closeJedis(jedis);
		}
	}


	/**
	 * 移除集合中的成员
	 * @param key
	 * @param value
	 * @return
	 */
	public void removeMem(String keyPrefix,String key,String value){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.srem(wrapKey(keyPrefix,key),value);
		}finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 在制定expireSeconds时间内 key自增的最大
	 * redis 执行lua脚本竟然不支持 >= <= 所以要改成> 或 == 或< 分开判断
	 * @param key
	 * @param expireSeconds
	 * @param maxTime
	 * @return
	 */
	public boolean tryIncr(String keyPrefix,String key,int expireSeconds,int maxTime){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			StringBuilder redisScript = new StringBuilder("local current;");
			redisScript.append(" current = redis.call('incr',KEYS[1]); ");
			redisScript.append("  local curNo = tonumber(current);");
			redisScript.append("  local maxTime = tonumber(ARGV[2]);");
			redisScript.append( " if curNo > 1 then ");
			redisScript.append( " 	if curNo < maxTime then ");
			redisScript.append( "     	return 2; ");
			redisScript.append( " 	elseif curNo == maxTime then ");
			redisScript.append( "     	return 3; ");
			redisScript.append( "	else ");
			redisScript.append( "		return -1; ");//超标
			redisScript.append( "   end ");
			redisScript.append( " else");
			redisScript.append(" 	redis.call('expire',KEYS[1],ARGV[1]); ");
			redisScript.append( "     return 1; ");
			redisScript.append( " end ");
			List<String> args = new ArrayList<>(2);
			args.add(String.valueOf(expireSeconds));
			args.add(String.valueOf(maxTime));
			Object ret = jedis.eval(redisScript.toString(),Collections.singletonList(wrapKey(keyPrefix,key)),args);
			Integer value = Integer.valueOf(ret.toString());
			if(value<0){
				return false;
			}
			return true;
		} finally {
			closeJedis(jedis);
		}
	}

}
