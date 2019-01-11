package com.weige.ssm.thread.safe;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过reentranlock的可中断锁 和限时等待锁 解除死锁
 * @author yangyiwei
 * @date 2018年10月19日
 * @time 上午9:56:35
 */
public class IntDeadLock implements Runnable {

	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();
	int lock;

	/**
	 * 控制枷锁顺序
	 * 
	 * @param lock
	 */
	public IntDeadLock(int lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			if (lock == 1) {
				lock1.lock();
				System.out.println("我是锁1");

				Thread.sleep(5000);
				lock2.lock();
				System.out.println("锁住另外一个2");
			} else {
				lock2.lock();
				System.out.println("我是锁2");
				Thread.sleep(5000);
				System.out.println("锁住另外一个1");
				lock1.lock();
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (lock1.isHeldByCurrentThread()) {
				lock1.unlock();
			}
			if (lock2.isHeldByCurrentThread()) {
				lock2.unlock();
			}

		}
		System.out.println(Thread.currentThread().getId() + ":线程退出");
	}

	/**
	 * 等待可中断锁
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		IntDeadLock r1 = new IntDeadLock(1);
		IntDeadLock r2 = new IntDeadLock(2);
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		Thread.sleep(1000);
	//	t2.interrupt();
		//timeLock();
	}
	
}
