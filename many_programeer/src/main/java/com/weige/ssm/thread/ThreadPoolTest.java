package com.weige.ssm.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = new ThreadPoolExecutor(5, 5, 200, TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(), new ThreadPoolExecutor.AbortPolicy());
		CountDownLatch countDownLatch = new CountDownLatch(2);
		
		Thread.sleep(2000);
		executorService.submit(() -> {
			System.out.println("执行一波....");
			countDownLatch.countDown();

		});
		
		Thread.sleep(2000);
		executorService.submit(() -> {
			System.out.println("执行一波....");
			countDownLatch.countDown();

		});

		countDownLatch.await(2000, TimeUnit.MILLISECONDS);
		System.out.println("执行完成===========");
		executorService.shutdown();
	}
}
