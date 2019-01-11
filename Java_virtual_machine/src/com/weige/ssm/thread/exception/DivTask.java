package com.weige.ssm.thread.exception;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 计算两个数的商 在此处有个地方容易采坑 线程池的submit方法会吃掉异常（callback的缘故） 可以改用exec
 * 
 * @author yangyiwei
 * @date 2018年11月10日
 * @time 下午1:39:12
 */
public class DivTask implements Runnable {

	int a, b;
	CountDownLatch countDownLatch;

	public DivTask(int a, int b, CountDownLatch countDownLatch) {
		this.a = a;
		this.b = b;
		this.countDownLatch = countDownLatch;
	}

	public DivTask(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public void run() {

		double re = a / b;
		System.out.println("结果:" + re);

		// countDownLatch.countDown();
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executorService = new TraceThreadPoolExecutor(10, 10,0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));
		CountDownLatch countDownLatch = new CountDownLatch(2);
		Future<String> future1 = executorService.submit(() -> {
			String aString ="abc";
			try {
				int i = 1 / 0;
			
				return aString;
				
			} finally {
				countDownLatch.countDown();
			}
			
		});
		Future<String> future2 = executorService.submit(() -> {
			String aString ="abc";
			try {
				int i = 1 / 0;
				return aString;
			} finally {
				countDownLatch.countDown();
			}
			
		});
		countDownLatch.await();
		System.out.println(future1.get());

		executorService.shutdown();
	}

}
