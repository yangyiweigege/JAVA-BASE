package com.weige.ssm.thread.safe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BadLockOneInteger implements Runnable {

	public static Integer i = 0;
	static public BadLockOneInteger instance = new BadLockOneInteger();

	@Override
	public void run() {
		for (int j = 0; j < 1000; j++) {
			synchronized (instance) {
				i++;
			}
		}
	}
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.submit(instance);
		executorService.submit(instance);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(i);
		executorService.shutdown();
	}

}
