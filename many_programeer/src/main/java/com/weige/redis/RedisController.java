package com.weige.redis;
import redis.clients.jedis.Jedis;

public class RedisController {

	
	public RedisController() {
		
	}
	
	/**
	 *使用redis模拟多线程并发秒杀（防止超卖）
	 * </pre>
	 */
	public void buyGoods() {
		Jedis jedis = RedisTemplate.getRedisInstance();
		jedis.select(15);
		long num = jedis.incr("num");
		if (num < 20) {
			System.out.println(Thread.currentThread().getName() + "购买成功!");
			jedis.lpush("num_length", num+"" + "线程名:" + Thread.currentThread().getName());
		} else {
			System.out.println(Thread.currentThread().getName() + "购买失败!");
		}
		RedisTemplate.releaseJedis(jedis);
	}
	
	/**
	 *  使用redis模拟分布式锁
	 * 
	 */
	public void onlyOne(String name) throws InterruptedException {
		/*Jedis jedis = 
		jedis.select(15);
		while (true) { //没有锁 则一直等待 模拟分布式锁
			if (jedis.setnx("lock", "1") == 1) {//如果获得锁，则执行
				jedis.expire("lock", 20);
				break;
			} else {
				Thread.sleep(1000);
			}
		}
		System.out.println(Thread.currentThread().getName() +  " 获得了锁 并且顺利执行~~~~~~");
		jedis.del("lock");*/
		
		Jedis jedis = RedisTemplate.getRedisInstance();
		jedis.select(15);
		while (true) { //没有锁 则一直等待 模拟分布式锁
			if (jedis.setnx("lock", "1") == 1) {//如果获得锁，则执行
				jedis.expire("lock", 20);
				break;
			} else {
				try {
					System.out.println("没有获得锁，等待了，，，老铁");
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(Thread.currentThread().getName() +  " 获得了锁 并且顺利执行~~~~~~");
		jedis.del("lock");
		jedis.disconnect();
		
	}
	
	
	public static void main(String[] args) {
		/*RedisController redisController = new RedisController();
		for (int i = 0; i < 10; i++) {
			new Thread(new RedisThreadA(redisController)).start();
			new Thread(new RedisThreadB(redisController)).start();
		}*/
		Jedis jedis = RedisTemplate.getRedisInstance();
		String uuid = "456";
		System.out.println("人员数量:" + jedis.incr("online_count"));;// 人数++
		jedis.sadd("online_people", uuid);// 所有访客存入set集合
		jedis.sadd("no_match_people", uuid);// 未匹配成功的用户
		jedis.hset("match_peer", uuid, "noPeer");// 匹配的伙伴 一开始 为自身 + nopeer
		jedis.disconnect();
	}
}
