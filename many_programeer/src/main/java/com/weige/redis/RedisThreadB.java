package com.weige.redis;

public class RedisThreadB implements Runnable {
	private RedisController redisController;

	public RedisThreadB(RedisController redisController) {
		this.redisController = redisController;
	}

	@Override
	public void run() {

		try {
			redisController.onlyOne("Thread B ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
