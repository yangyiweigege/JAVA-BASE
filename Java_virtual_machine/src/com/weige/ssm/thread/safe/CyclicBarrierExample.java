package com.weige.ssm.thread.safe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 主要作用协调线程 可以让线程之间相互等待 而countdownlauch 主要为等待所有线程执行完成 往后执行
 * 
 * @author yangyiwei
 * @date 2018年11月30日
 * @time 下午7:22:57
 */
public class CyclicBarrierExample {

	public static void main(String[] args) throws InterruptedException {
		testCountDownLauch();
	}

	public static void testCycliecBarrier() throws InterruptedException {
		CyclicBarrier barrier = new CyclicBarrier(5, () -> {
			System.out.println("到达某个屏障点，执行该代码");
		});

		ExecutorService executorService = Executors.newCachedThreadPool();
		Thread.sleep(5000);
		for (int i = 0; i < 5; i++) {
			final int threadNum = i;
			
			executorService.submit(() -> {
				try {
					// Thread.sleep(1000 * threadNum);
					System.out.println("准备执行...." + threadNum);
					barrier.await(3000, TimeUnit.MILLISECONDS);
					System.out.println("完成了...." + threadNum);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		executorService.shutdown();
	}

	public static void testCountDownLauch() throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		CountDownLatch countDownLatch = new CountDownLatch(5);
		System.out.println("开始执行.");
		Thread.sleep(4000);
		for (int i = 0; i < 5; i++) {
			final int threadNum = i;
		//	Thread.sleep(1000);
			executorService.submit(() -> {
				try {
					 Thread.sleep(2000);
					System.out.println("准备执行...." + threadNum);
					countDownLatch.countDown();
					//System.out.println("完成了...." + threadNum);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		countDownLatch.await(2000, TimeUnit.MILLISECONDS);
		System.out.println("执行完毕。。。");
		executorService.shutdown();
	}
}
