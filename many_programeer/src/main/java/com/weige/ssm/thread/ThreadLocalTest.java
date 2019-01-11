package com.weige.ssm.thread;

/**
 * 本地线程测试
 * 
 * @author yangyiwei
 * @date 2018年5月30日
 * @time 下午4:36:18
 */
public class ThreadLocalTest {
	ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
	ThreadLocal<String> stringLocal = new ThreadLocal<String>();

	public void set() {
		longLocal.set(Thread.currentThread().getId());
		stringLocal.set(Thread.currentThread().getName());
	}

	public long getLong() {
		return longLocal.get();
	}

	public String getString() {
		return stringLocal.get();
	}

	public static void main(String[] args) throws InterruptedException {
		final ThreadLocalTest test = new ThreadLocalTest();

		test.set();
		System.out.println(test.getLong());
		System.out.println(test.getString());

		Thread thread1 = new Thread() {
			public void run() {
				test.set();
				System.out.println(test.getLong());
				System.out.println(test.getString());
			};
		};
		thread1.start();
		thread1.join();//此线程执行完

		System.out.println(test.getLong());
		System.out.println(test.getString());
	}
}
