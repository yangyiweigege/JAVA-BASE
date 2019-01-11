package com.weige.ssm.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.activemq.console.Main;

public class ReadWriteLockTest {
	private static Lock lock = new ReentrantLock();

	/**
	 * 读写锁
	 */
	private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private static Lock readLock = readWriteLock.readLock();

	private static Lock writeLock = readWriteLock.writeLock();

	private static int value;

	public static void main(String[] args) throws InterruptedException {
		List<Integer> list = new ArrayList();
				list.add(1);
		
		for (int i = 0 ; i < 100; i++) {
			
			new Thread(() -> {
				list.get(list.size() - 1);
			}).start();
			new Thread(() -> {
				list.add(1);
			}).start();
		}
		
		
			
		
	
	}

	public void testWriteAndRead() {
		new Thread(() -> {
			writeLock.lock();
			System.out.println("吸入..");
			writeLock.unlock();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}).start();
		for (int i = 0; i < 5; i++) {
			final int k = i;
			new Thread(() -> {
				readLock.lock();
				try {
					System.out.println("执行完毕" + k);
					Thread.sleep(k * 1000);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				readLock.unlock();
			}).start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writeLock.lock();
		System.out.println("哈哈哈");
		writeLock.unlock();
	}
}
