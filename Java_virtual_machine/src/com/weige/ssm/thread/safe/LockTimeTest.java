package com.weige.ssm.thread.safe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

/**
 * 限时等待锁
 */
public class LockTimeTest {

	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();

	public static ReentrantLock fairLoack = new ReentrantLock(true);

	/**
	 * 限时等待锁
	 */
	@Test
	public static  void timeLock() {
		new Thread(() -> {
			try {
				if (lock1.tryLock( )) {
					System.out.println("我是其中一条线程.取得了锁");
					Thread.sleep(3000);
				} else {
					System.out.println("我失败了..");
					lock1.unlock();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				if (lock1.isHeldByCurrentThread()) {
			
					
				}
			}
		}).start();
		new Thread(() -> {
			try {
				if (lock1.tryLock(  )) {
					System.out.println("我是其中一条线程.取得了锁");
					Thread.sleep(6000);
				} else {
					System.out.println("无法取得锁...");
		
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				if (lock1.isHeldByCurrentThread()) {
					lock1.unlock();
				}
			}

		}).start();
	}

	/**
	 * 实现公平锁测试, 默认情况下 synochronize和reenlock都是非公平锁
	 */
	@Test
	public void fairLockTest() {
		new Thread(() -> {

			while (true) {
				fairLoack.lock();
				System.out.println(Thread.currentThread().getName() + "获得锁了");
				fairLoack.unlock();
			}

		}, "Thread--1").start();
		new Thread(() -> {

			while (true) {
				fairLoack.lock();
				System.out.println(Thread.currentThread().getName() + "获得锁了");
				fairLoack.unlock();
			}

		}, "Thread--2").start();
	}

	/**
	 * 使用condition变量
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		timeLock();
		/*Condition condition = lock1.newCondition();
		new Thread(() -> {
			lock1.lock();
			try {
				condition.await();
				System.out.println("Thread is going....继续执行");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				lock1.unlock();
			}
		}).start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock1.lock();
		condition.signal(); //通知另外一条线程继续执行
		lock1.unlock();
*/
	}

}
