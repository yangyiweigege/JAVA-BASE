package com.weige.ssm.thread.safe;

import java.awt.event.MouseWheelEvent;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读锁和写锁测试
 * 读读不上锁 读写上锁 写写上锁。
 * @author yangyiwei
 * @date 2018年10月25日
 * @time 下午2:17:04
 */
public class ReadWriteLockDemo {

	private static Lock lock = new ReentrantLock();

	/**
	 * 读写锁
	 */
	private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private static Lock readLock = readWriteLock.readLock();

	private static Lock writeLock = readWriteLock.writeLock();

	private static int value;

	public Object handleRead(Lock lock) {
		lock.lock(); // 模拟读操作
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return value;
	}

	public void handleWrite(Lock lock, int index) {
		lock.lock(); // 模拟写操作
		try {
			Thread.sleep(1000);
			value = index;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		ReadWriteLockDemo demo = new ReadWriteLockDemo();
		CountDownLatch countDownLatch = new CountDownLatch(20);
		for (int i = 0; i < 18; i++) {
			new Thread(() -> {
				demo.handleRead(readLock); //读锁
				countDownLatch.countDown();
				//demo.handleRead(lock);
			}).start();
		}
		
		for(int i = 0; i < 2; i++) {
			new Thread(() -> {
				demo.handleWrite(writeLock, new Random().nextInt()); //
				countDownLatch.countDown();
			}).start();
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();    //获取结束时间
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); 
	}

}
