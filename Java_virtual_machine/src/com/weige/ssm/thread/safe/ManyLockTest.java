package com.weige.ssm.thread.safe;

import java.util.concurrent.locks.*;

import org.junit.Test;

/**
 * 测试juc包下的锁
 * 
 * @author yangyiwei
 * @date 2018年10月17日
 * @time 下午2:50:05
 */
public class ManyLockTest {

	public static ReentrantLock lock = new ReentrantLock();
	public static int i = 0;

	

	@Test
	public void testReentranLock() throws InterruptedException {
		Thread thread1 = new Thread(() -> {

			for (int j = 0; j < 10000; j++) {
				lock.lock();
				i++;
				//int k = 1 / 0;
				lock.unlock();
			}

		});

		Thread thread2 = new Thread(() -> {
			for (int j = 0; j < 10000; j++) {
				lock.lock();
				i++;
				lock.unlock();
			}
		});
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		System.out.println("i： " + i);
	}

	public static void main(String[] args) {
	
		ManyLockTest manyLockTest = new ManyLockTest();
		try {
			manyLockTest.testReentranLock();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
