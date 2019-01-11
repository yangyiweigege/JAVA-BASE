package com.weige.ssm.thread.safe;

import java.util.concurrent.Semaphore;

import org.junit.Test;

/**
 * 信号量相关测试
 * 
 * @author yangyiwei
 * @date 2018年10月22日
 * @time 下午3:23:53
 */
public class SemaphoreTest {
	

	/**
	 * 同时创建多条线程，但只允许5条工作
	 * @throws InterruptedException 
	 */
	@Test
	public static void main(String[] args) throws InterruptedException {
		Semaphore semaphore = new Semaphore(5);
		semaphore.acquire();
		semaphore.acquire();
		semaphore.acquire();
		semaphore.acquire();
		System.out.println("执行到这里");
		//semaphore.acquire();
		System.out.println("执行到这里2");
		for (int i = 0; i < 20; i++) {
			new Thread(() -> {
				try {
					
					if (semaphore.tryAcquire()) {
						System.out.println(Thread.currentThread().getId() + "：执行完毕");
						Thread.sleep(2000);
						semaphore.release();
					}
				
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();
		}

	}

}
