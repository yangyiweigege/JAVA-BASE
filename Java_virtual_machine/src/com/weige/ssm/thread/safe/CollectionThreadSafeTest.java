package com.weige.ssm.thread.safe;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 测试各种集合线程安全
 * 
 * @author yangyiwei
 * @date 2018年10月16日
 * @time 下午2:00:12
 */
public class CollectionThreadSafeTest {

	private static Integer start = new Integer(1);

	/**
	 * 错误的使用Integer作为锁 导致出错
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void userIntegerAsLock() throws InterruptedException {

		Thread thread1 = new Thread(() -> {

			for (int j = 0; j < 10000; j++) {
				synchronized (start) {
					start++;
				}

			}

		});

		Thread thread2 = new Thread(() -> {
			for (int j = 0; j < 10000; j++) {
				synchronized (start) {
					start++;
				}
			}
		});
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		System.out.println("i： " + start);

	}

	/**
	 * 并发状态下 hashmap出错
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void hashMapMultiThread() throws InterruptedException {
		Map<String, Object> hashMap = new HashMap<String, Object>();

		Thread thread1 = new Thread(() -> {

			for (int i = 0; i < 10000; i = i + 2) {
				hashMap.put(i + "", i);
			}

		});

		Thread thread2 = new Thread(() -> {
			for (int i = 0; i < 10000; i = i + 2) {
				hashMap.put(i + "", i);
			}
		});
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		System.out.println(hashMap.size()); // 容量将会
	}

}
