package com.weige.ssm.thread.safe;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;

public class FutureTaskTest {
	
	private static int i = 0;
	
	private static Semaphore semaphore = new Semaphore(1);
	
	public static void main(String[] args)  throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int j = 0; j <5; j++) {  
			Future<String> future = executorService.submit(() -> {
				try {
					semaphore.acquire();
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int k = 0; k < 1000; k++) {
					i++;
				}
				semaphore.release();
				return "目前i是：" + 10086;
			});
			//System.out.println(future.get());
		}
		System.out.println("结束了");
		executorService.shutdown();
		//Thread.sleep(5000);
		
		
		long start = System.currentTimeMillis();
		Callable<String> callable = new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "凉菜做好了";
			}
		};
		FutureTask<String> ft1 = new FutureTask<String>(callable);
		new Thread(ft1).start();
		
		Callable<String> callable2 = new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				return "包子准备完毕";
			}
		};
		FutureTask<String> ft2 = new FutureTask<String>(callable2);
		ft2.run();
		new Thread(ft2).start();
		System.out.println(ft1.get());
		System.out.println(ft2.get());
	}

}
