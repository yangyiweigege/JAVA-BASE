package com.weige.ssm.thread.group;

import java.util.Vector;

import org.junit.Test;

/**
 * java线程组测试
 * 
 * @author yangyiwei
 * @date 2018年10月16日
 * @time 上午10:13:49
 */
public class ThreadGroupName implements Runnable {

	private volatile int i = 0;

	/**
	 * 测试i++
	 * @throws InterruptedException
	 */
	@Test
	public void incrI() throws InterruptedException {
		Thread t1 = new Thread(() -> {
			for (int j = 0; j < 10000; j++) {
				i++;
			}
			System.out.println("卡在此处2");
		});
		Thread t2 = new Thread(() -> {
			for (int j = 0; j < 10000; j++) {
				i++;
			}
			System.out.println("卡在此处1");
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("执行完的结果:" + i);
	}

	/**
	 * 测试线程组
	 */
	@Test
	public void threadGroupTest() {
		ThreadGroup threadGroup = new ThreadGroup("PrintGroup");
		Thread threadOne = new Thread(threadGroup, new ThreadGroupName(), "T1");
		Thread threadTwo = new Thread(threadGroup, new ThreadGroupName(), "T2");
		threadOne.start();
		threadTwo.start();
		System.out.println(threadGroup.activeCount());
		threadGroup.list();
	}

	/**
	 * 测试线程优先
	 */
	public void testPriorityDemo() {

	}

	@Override
	public void run() {
		String groupAndName = Thread.currentThread().getThreadGroup().getName() + "-"
				+ Thread.currentThread().getName();
		while (true) {
			System.out.println("I am " + groupAndName);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
