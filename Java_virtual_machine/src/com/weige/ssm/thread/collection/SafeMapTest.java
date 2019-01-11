package com.weige.ssm.thread.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SafeMapTest {
	
	
	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(2);
		Map<String, Integer> map = new HashMap();
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 2; i++) {
			executorService.execute(() -> {
				for (int k = 0; k < 10000; k++) {
					map.put(k + "", k);
				}
				countDownLatch.countDown();
			});
		}
		
	//	executorService.shutdown();
		//executorService.submit(() ->{});
		try {
			countDownLatch.await();
			System.out.println("执行完毕" + map.size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
