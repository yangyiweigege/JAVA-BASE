package com.imooco.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * syno关键字测试
 * 
 * @author yangyiwei
 * @date 2018年11月14日
 * @time 下午7:28:11
 */
public class SynchronizedExample {

	// 修饰一个代码块
	public static synchronized void test1() {

		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(100);
				System.out.println("test1-----" + i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public  synchronized void test2() {
		 synchronized (SynchronizedExample.class) {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(100);
				System.out.println("test2S-----" + i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		 }
	}

	public static void main(String[] args) {
		SynchronizedExample synchronizedExample1 = new SynchronizedExample();
		SynchronizedExample synchronizedExample2 = new SynchronizedExample();
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(() -> {
			synchronizedExample1.test1();
		});
		executorService.execute(() -> {
			synchronizedExample2.test2();
		});
		executorService.shutdown();
	}
}
