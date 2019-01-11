package com.imooco.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * futureTask 实现了 RunableFutre接口 runnalbeFUTER接口继承自runnable和future 所以 可以Runable
 * run = new FutureTask();
 * 
 * @author yangyiwei
 * @date 2018年12月1日
 * @time 下午4:52:45
 */
public class FutureTaskTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/*
		 * FutureTask<String> future1 = new FutureTask<>(() -> {
		 * Thread.sleep(1000); return "done1"; }); FutureTask<String> future2 =
		 * new FutureTask<>(() -> { Thread.sleep(1000); return "done2"; }); new
		 * Thread(future1).start(); new Thread(future2).start();
		 * System.out.println(future1.get()); System.out.println(future2.get());
		 */

		ExecutorService executorService = new ThreadPoolExecutor(4, 6, 0L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(2), new ThreadPoolExecutor.DiscardOldestPolicy());
		for (int i = 0; i < 15; i++) {
			final int index = i;
			Future<String> futureTask = executorService.submit(() -> {
				try {
					Thread.sleep(2000);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return " 执行的坐标是:" + index;
			});
		}
		executorService.shutdown();
	}
}
