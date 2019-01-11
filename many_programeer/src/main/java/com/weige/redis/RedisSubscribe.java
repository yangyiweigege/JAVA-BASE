package com.weige.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

/**
 * <pre>
 * 功       能:redis消息订阅 
 * 涉及版本: V3.0.0 
 * 创  建  者: yangyiwei
 * 日       期: 2018年2月9日 下午1:24:13
 * Q    Q: 2873824885
 * </pre>
 */
public class RedisSubscribe extends JedisPubSub {

	@Override
	public void unsubscribe() {
		super.unsubscribe();
	}

	@Override
	public void unsubscribe(String... channels) {
		super.unsubscribe(channels);
	}

	@Override
	public void subscribe(String... channels) {
		super.subscribe(channels);
	}

	@Override
	public void psubscribe(String... patterns) {
		super.psubscribe(patterns);
	}

	@Override
	public void punsubscribe() {
		super.punsubscribe();
	}

	@Override
	public void punsubscribe(String... patterns) {
		super.punsubscribe(patterns);
	}

	@Override
	public void onMessage(String channel, String message) {
		System.out.println("channel:" + channel + "receives message :" + message);
		this.unsubscribe();
	}

	@Override
	public void onPMessage(String pattern, String channel, String message) {

	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		System.out.println("channel:" + channel + "is been subscribed:" + subscribedChannels);
	}

	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {

	}

	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {

	}

	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		System.out.println("channel:" + channel + "is been unsubscribed:" + subscribedChannels);
	}

	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				Jedis jedis = null;
				JedisPool pool = null;
				JedisPoolConfig config = new JedisPoolConfig();// 连接至配置文件
				config.setMaxTotal(5);
				config.setMaxWaitMillis(500l * 500l);
				pool = new JedisPool(config, "192.168.10.148", 6379, 14400, "password");// 连接池
				jedis = pool.getResource();
				JedisPubSub jedisPubSub = new JedisPubSub() {
					@Override
					public void onMessage(String channel, String message) {
						super.onMessage(channel, message);
						if (channel.equals("yang")) {
							System.out.println("订阅的消息是:" + message);
						}
						
					}
				};
				jedis.subscribe(jedisPubSub, "JRedisChat" , "yang");
			}
		}).start();
		System.out.println("执行结果");
	}
}
