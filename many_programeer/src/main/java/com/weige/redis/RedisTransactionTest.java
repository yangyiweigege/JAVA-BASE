package com.weige.redis;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class RedisTransactionTest {

	public static void main(String[] args) throws InterruptedException {
		Jedis jedis = RedisTemplate.getRedisInstance();
	
		Transaction transaction = jedis.multi();
		transaction.set("persons", "小屁孩");
		
		List<Object> list = transaction.exec();
		String[] string = new String[4];
		string[0] = "a";
		string[1] = "b";
		string[2] = "c";
		string[3] = "d";
		jedis.disconnect();
		Map<String, Object> map = new TreeMap<>();

	}
}
