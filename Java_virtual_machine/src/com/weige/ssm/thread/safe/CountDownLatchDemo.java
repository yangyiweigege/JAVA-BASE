package com.weige.ssm.thread.safe;

import java.util.Random;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时器
 * 
 * @author yangyiwei
 * @date 2018年10月29日
 * @time 上午10:06:29
 */
public class CountDownLatchDemo implements Runnable {

	static final CountDownLatch end = new CountDownLatch(10);//需要十条线程完成任务
	static final CountDownLatchDemo demo = new CountDownLatchDemo();

	@Override
	public void run() {
		try {
			Thread.sleep(new Random().nextInt(10) * 1000);
			System.out.println("check complete");
			end.countDown();//i--
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			exec.submit(demo);
		}
		//等待检查
		end.await();
		//发射火箭
		System.out.println("Fire !");
		exec.shutdown();
	}
	

}
