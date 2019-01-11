package com.imooco.concurrency;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

public class StampLockTest {

	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(3);
		StampedLock stampedLock = new StampedLock();
		ExecutorService executorService = Executors.newCachedThreadPool();
		Set<Integer> set = new HashSet<>();
		executorService.submit(()->{
			//long count = stampedLock.writeLock();
			for (int i = 0; i < 5000; i++) {
				set.add(i);
			}
			//stampedLock.unlock(count);
			countDownLatch.countDown();
		} );
		executorService.submit(()->{
			//long count = stampedLock.writeLock();
			for (int i = 0; i < 5000; i++) {
				set.add(i);
			}
			//stampedLock.unlock(count);
			countDownLatch.countDown();
		} );
		executorService.submit(()->{
			//long count = stampedLock.writeLock();
			for (int i = 0; i < 5000; i++) {
				set.add(i);
			}
			//stampedLock.unlock(count);
			countDownLatch.countDown();
		} );
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(set.size());
		System.out.println("====");
		for (Integer integer : set) {
			System.out.println(integer);
		}
		executorService.shutdown();
	
		
	}

}
