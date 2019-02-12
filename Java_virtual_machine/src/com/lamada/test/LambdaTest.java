package com.lamada.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: lambda表达式测试
 * @Author: 杨乙伟
 * @Date Created in 2019-01-17 20:19:45
 */
public class LambdaTest {


     void getCglib() {

     }

     public static  void main(String[] args) throws InterruptedException {
          ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
          Lock writeLock = reentrantReadWriteLock.writeLock();
          Lock readLock = reentrantReadWriteLock.readLock();
          ExecutorService executorService = new ThreadPoolExecutor(5,5,0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardPolicy()){

               @Override
               protected void afterExecute(Runnable r, Throwable t) {
                    System.out.println("任务执行完成");
               }

          };
          executorService.submit(() -> {
               readLock.lock();
               System.out.println("开始读取....");
               try {
                    Thread.sleep(2000);
               } catch (InterruptedException e) {
                    e.printStackTrace();
               }
               readLock.unlock();
          });
          Thread.sleep(30);
          executorService.submit(() -> {
               writeLock.lock();
               System.out.println("开始写入....");
               try {
                    Thread.sleep(2000);
               } catch (InterruptedException e) {
                    e.printStackTrace();
               }
               //readLock.unlock();
          });
          executorService.shutdown();

     }



}
