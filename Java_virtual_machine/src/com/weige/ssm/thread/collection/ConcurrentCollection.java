package com.weige.ssm.thread.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * JUC下的线程安全类
 * @author yangyiwei
 * @date 2018年11月20日
 * @time 下午2:58:19
 */
public class ConcurrentCollection {
	
	public static void main(String[] args) {
		/*Map<Integer, Integer> map = new ConcurrentSkipListMap<Integer, Integer>();
		
		for (int i = 0; i < 30; i++) {
			map.put(i, i);
		}
		
		for (Map.Entry<Integer, Integer> item : map.entrySet()) {
			System.out.println(item.getKey() + "...." + item.getValue());
		}*/
		try {
			//ConcurrentSkipListMap<K, V>
			//ConcurrentHashMap<K, V>
			userCopyOnWriteArrayList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 单纯读取不会加锁
	 * 写入的时候是复制操作，会有加锁操作
	 * 适合读多写少的场景 实现了读写分离，最终一致性
	 * 缺点：复制操作 可能会频繁GC
	 */
	@Test
	public static void userCopyOnWriteArrayList() throws Exception{
		List<Integer> list = new /*CopyOnWrite*/ArrayList<>();
		ExecutorService executorService = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
		Semaphore semaphore = new Semaphore(1);
		CountDownLatch countDownLatch = new CountDownLatch(200);
		for (int i = 0; i < 30; i++) {
			
			semaphore.acquire();
			//System.out.println(i);
			/*Thread.sleep(100);*/
			final int count = i;
			executorService.submit(() -> {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				list.add(count);
			//	countDownLatch.countDown();
			});
			
			semaphore.release();
		}
		//countDownLatch.await();
		System.out.println("结果：" + list.size());
		executorService.shutdown();
	}

}
