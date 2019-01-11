package com.weige.redis;

public class BuyThread implements Runnable{
	
	private RedisController redisController;
	
	public BuyThread(RedisController redisController) {
		this.redisController = redisController;
	}
	
	public BuyThread() {
		
	}

	@Override
	public void run() {
		redisController.buyGoods();
	}
	
	public static void main(String[] args) {
		RedisController redisController = new RedisController();
		for (int i = 0; i < 50; i++) {
			new Thread(new BuyThread(redisController)).start();
		}
		
	}
	

}
