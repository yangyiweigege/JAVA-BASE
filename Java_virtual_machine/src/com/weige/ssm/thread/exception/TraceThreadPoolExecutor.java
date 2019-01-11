package com.weige.ssm.thread.exception;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池扩展
 * 
 * @author yangyiwei
 * @date 2018年11月10日
 * @time 下午2:29:29
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

	public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
	
	@Override
	public void execute(Runnable task) {
		super.execute(wrap(task, clientTrace(), Thread.currentThread().getName()));
	}
	
	@Override
	public Future<?> submit(Runnable task) {
		return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
	}
	
	@Override
	public <T> Future<T> submit(Callable<T> task) {
		return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));

	}
	
	/**
	 * 包装callable方法，出异常则抛出
	 * @param task
	 * @param clientStack
	 * @param clientThreadName
	 * @return
	 */
	private <T> Callable<T> wrap(final Callable<T> task, final Exception clientStack, String clientThreadName) {
		return () -> {
			try {
				T t = task.call();
				return t;
			} catch (Exception e) {
				clientStack.printStackTrace();
				e.printStackTrace();
				throw e;
			}
		};
	}
	/*@Override
	public Future<?> submit(Callable<?> task) {
		FutureTask<V>
		return super.submit(task);
	}*/
	
	private Exception clientTrace() {
		return new Exception("Client stack trace");
	}
	
	private Runnable wrap(final Runnable task, final Exception clientStack, String clientThreadName) {
		return () -> {
			try {
				task.run();
			} catch (Exception e) {
				clientStack.printStackTrace();
				e.printStackTrace();
				throw e;
			}
			
		};
	}
	
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		//System.out.println("准备执行...." + Thread.currentThread().getName());
	}
	
	@Override
	public void afterExecute(Runnable r, Throwable t) {
		//System.out.println("执行完毕:" + Thread.currentThread().getName());
	}
	
	@Override
	public void terminated() {
		System.out.println("线程池退出.....");
	}

}
