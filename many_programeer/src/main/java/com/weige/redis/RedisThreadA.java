package com.weige.redis;

public class RedisThreadA implements Runnable {
	private RedisController redisController;

	public RedisThreadA(RedisController redisController) {
		this.redisController = redisController;
	}

	@Override
	public void run() {
		try {
			redisController.onlyOne("Thread A ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
