package com.weige.ssm.thread.safe;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 不同线程池的使用
 * 
 * @author yangyiwei
 * @date 2018年10月29日
 * @time 上午10:59:42
 */
public class ManyOfThreadPoolUse {

	public static class MyTask implements Runnable {

		public String name;

		public MyTask(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * 1.有界队列 blockarray 当线程任务数量小于核心数量 则先创建至核心任务 如果》核心任务数 则进入等待队列 若超过等待队伍》阻塞队列
	 * 则创建最大线程数 如果还无法满足 则拒绝执行 2.直接提交队列 synochronizeQueue ，只要有新的任务过来 就会创建新的线程来处理
	 * 需要设置很大的maxSize 否则容易拒绝执行 3.linkQueen 无界队列 当线程数超过核心线程数， 则所有的任务都被提交到队列。
	 * fixpool 也是无解队列 但是核心线程数自定义 singlepool 也是无解队列 但是coreSize =1 CachepOOL
	 * 采用直接提交队列 maxSize很大
	 */
	public static void main(String[] args) {
		// 测试有界队列 bloackQueenPool();
		// 测试无界队列
		// LinkQueenPool();
		// synochronizeDQueenPool();
		// rejectExecPolicty();
		// System.out.println("Cpu数量:" +
		// Runtime.getRuntime().availableProcessors());
		// expandThreadPool();
		// System.out.println("执行结束....");
		// synochronizeDQueenPool();
		//defineThreadFactory();
		userSchuedle();
	}

	public static void userSchuedle() {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
		//executorService.schedule(() -> {System.out.println("执行");},3, TimeUnit.SECONDS);
	
		executorService.scheduleAtFixedRate(() -> {System.out.println("执行");},1,3,TimeUnit.SECONDS);
		//executorService.shutdown();
	}

	/**
	 * 扩展线程池
	 */
	public static void expandThreadPool() {
		CountDownLatch countDownLatch = new CountDownLatch(10);
		ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MICROSECONDS,
				new LinkedBlockingQueue<Runnable>(10), new ThreadPoolExecutor.CallerRunsPolicy()) {
			@Override
			protected void beforeExecute(Thread t, Runnable r) {
				System.out.println("准备执行...." + t.getName());
			}

			@Override
			public void afterExecute(Runnable r, Throwable t) {
				System.out.println("执行完毕:" + r);
			}

			@Override
			public void terminated() {
				System.out.println("线程池退出.....");
			}
		};
		for (int i = 0; i < 10; i++) {
			executorService.submit(() -> {
				System.out.println("我是线程.。。");

				try {
					Thread.sleep(5000);
					countDownLatch.countDown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		try {
			countDownLatch.await();
			executorService.shutdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 自定义线程池
	 */
	public static void defineThreadFactory() {
		// 最大线程数不会起作用 队列到达核心线程数以后 都会进入无界队列 直到内存耗尽
		ExecutorService executorService = new ThreadPoolExecutor(5, 5, 2000, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(2),
				/*
				 * new ThreadFactory() {
				 * 
				 * @Override public Thread newThread(Runnable r) { Thread thread
				 * = new Thread(r); //thread.setDaemon(true);
				 * System.out.println("create thread....." + thread); return
				 * thread; } }
				 */
				(Runnable r) -> {
					Thread thread = new Thread(r, "pool-thread-i");
					// thread.setDaemon(true);
					System.out.println("create thread....." + thread.getName());
					return thread;
				}, new RejectedExecutionHandler() {

					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						System.out.println(r.getClass().getName() + " is card");
					}
				});
		for (int i = 0; i < 10; i++) {
			executorService.submit(() -> {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(System.currentTimeMillis() + ":Thread Id" + Thread.currentThread().getId());

			});
		}
		// executorService.shutdown();
	}

	/**
	 * 拒绝执行策略(自定义)
	 */
	public static void rejectExecPolicty() {
		// abortPolicy 执行中断 后续请求不会处理 callerRunsPolicy 继续请求
		// discardOldest丢弃无法处理的请求 discardPolicy丢弃无法处理的请求
		// 也可以自己扩展线程池出错信息
		ExecutorService executorService = new ThreadPoolExecutor(1, 3, 2000, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(3), new RejectedExecutionHandler() {

					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						System.out.println(r.getClass().getName() + " is card");
					}
				});
		for (int i = 0; i < 10; i++) {
			executorService.submit(() -> {
				System.out.println(System.currentTimeMillis() + ":Thread Id" + Thread.currentThread().getId());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		executorService.shutdown();
	}

	/**
	 * newFixPool 采用无界队列
	 */
	public static void newFixPoolThread() {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		// ExecutorService executorService = Executors.newCachedThreadPool();
		// 采用直接提交队列
		for (int i = 0; i < 10; i++) {
			executorService.submit(() -> {
				System.out.println(System.currentTimeMillis() + ":Thread Id" + Thread.currentThread().getId());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		executorService.shutdown();
	}

	/**
	 * 采用有界队列
	 */
	public static void bloackQueenPool() {
		// 有界队列
		ExecutorService executorService = new ThreadPoolExecutor(1, 3, 2000, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(3));
		for (int i = 0; i < 10; i++) {
			executorService.submit(() -> {
				System.out.println(System.currentTimeMillis() + ":Thread Id" + Thread.currentThread().getId());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		executorService.shutdown();
	}

	/**
	 * 采用无界队列
	 */
	public static void LinkQueenPool() {
		// 最大线程数不会起作用 队列到达核心线程数以后 都会进入无界队列 直到内存耗尽
		ExecutorService executorService = new ThreadPoolExecutor(1, 3, 2000, TimeUnit.SECONDS,
				new LinkedBlockingQueue<>());
		for (int i = 0; i < 10; i++) {
			executorService.submit(() -> {
				System.out.println(System.currentTimeMillis() + ":Thread Id" + Thread.currentThread().getId());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		executorService.shutdown();
	}

	/**
	 * 采用直接提交队列方式
	 */
	public static void synochronizeDQueenPool() {
		// 直接提交队列，提交任务 则直接创建新线程 直到达到MAXtHREAD,然后抛出异常挂掉
		ExecutorService executorService = new ThreadPoolExecutor(1, 5, 2000, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>(), new ThreadPoolExecutor.AbortPolicy());
		for (int i = 0; i < 6; i++) {
			executorService.submit(() -> {
				System.out.println(System.currentTimeMillis() + ":Thread Id" + Thread.currentThread().getId());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			});
		}
		executorService.shutdown();
		executorService.submit(() -> {
			System.out.println(System.currentTimeMillis() + ":Thread Id" + Thread.currentThread().getId());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		});
		System.out.println("执行结束");
	}
}
