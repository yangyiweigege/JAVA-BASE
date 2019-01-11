package com.imooco.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

@NotThreadSafe
public class ConcurrencyTest {

	// 请求总数
	public static int clientTotal = 10;

	// 同时并发请求
	public static int threadTotal = 1;

	public static int count = 0;

	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		/*final*/ Semaphore semaphore = new Semaphore(threadTotal);
		/*final */CountDownLatch countDownLatch = new CountDownLatch(10);
		for (int i = 0; i < clientTotal; i++) {

			executorService.submit(() -> {
				try {
					semaphore.acquire();
					add();
					Thread.sleep(200);
					countDownLatch.countDown(); //线程计数 比如十个线程 全部完成 才往后走
					semaphore.release();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		
		executorService.shutdown();
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("count:" + count);
	}

	public static void add() {
		count++;
	}
}
