package com.weige.redis;

import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <pre>
 * 功       能: jedis连接redis数据库
 * 涉及版本: V3.0.0 
 * 创  建  者: yangyiwei
 * 日       期: 2017年12月8日 下午1:08:01
 * Q    Q: 2873824885
 * </pre>
 */
public class RedisTemplate {
	
	private static JedisPool pool = null;
	
	static {
		JedisPoolConfig config = new JedisPoolConfig();// 连接至配置文件
		config.setMaxTotal(50);
		config.setMaxWaitMillis(500l * 500l);
		pool = new JedisPool(config, "192.168.9.128", 6379, 14400, "password");// 连接池
	}

	public static Jedis getRedisInstance() {
		Jedis jedis = pool.getResource();
		return jedis;
	}
	
	public  static void releaseJedis(Jedis jedis) {
		pool.returnResource(jedis);
	}

	public static void main(String[] args) {
		List<String> list = pool.getResource().brpop(10, "aa", "bb");
		System.out.println(list.toString());
	}
	
	/**
	 * 连接池
	 */
	@Test
	public void demo1() {
		Jedis jedis = null;
		JedisPool pool = null;
		try {
			JedisPoolConfig config = new JedisPoolConfig();// 连接至配置文件
			config.setMaxTotal(5);
			config.setMaxWaitMillis(500l * 500l);
			pool = new JedisPool(config, "192.168.10.148", 6379, 14400, "password");// 连接池
			jedis = pool.getResource();
			jedis.select(15);
			List<String> list = jedis.brpop(20, "rediss");
			System.out.println(list.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (pool != null) {
				pool.close();
			}
			if (jedis != null) {
				jedis.close();
			}
		}

	}
}
