package com.weige.ssm.thread;

import java.util.concurrent.locks.ReentrantLock;

public class DCLManyThread {

	private static DCLManyThread dclManyThread;
	private static ReentrantLock reentrantLock = new ReentrantLock();

	private DCLManyThread() {

	}

	public String show() {
		return "hash地址:" + this.hashCode();
	}

	public static DCLManyThread getInstance() {
		if (dclManyThread == null) {
			reentrantLock.lock();
			System.out.println("一起进来");
			if (dclManyThread == null) {

				dclManyThread = new DCLManyThread();
				System.out.println("地址：" + dclManyThread.hashCode());
			}
			reentrantLock.unlock();
		}
		return dclManyThread;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(() -> {
				DCLManyThread.getInstance().hashCode();
			}).start();
		}
	}
}
