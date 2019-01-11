package com.weige.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPublish {
	public static void main(String[] args) {
		Jedis jedis = null;
		JedisPool pool = null;
		JedisPoolConfig config = new JedisPoolConfig();// 连接至配置文件
		config.setMaxTotal(5);
		config.setMaxWaitMillis(500l * 500l);
		pool = new JedisPool(config, "192.168.10.148", 6379, 14400, "password");// 连接池
		jedis = pool.getResource();
		jedis.publish("yang","第二条信息"); 
		jedis.publish("JRedisChat","第一条信息");  
		jedis.close();
		pool.close();
	}
}
