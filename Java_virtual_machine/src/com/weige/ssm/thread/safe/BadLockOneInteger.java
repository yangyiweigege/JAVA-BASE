package com.weige.ssm.thread.safe;

import java.util.concurrent.*;

public class BadLockOneInteger implements Runnable {

    public static Integer i = 0;
    static public BadLockOneInteger instance = new BadLockOneInteger();

    @Override
    public void run() {
        for (int j = 0; j < 1000; j++) {
            synchronized (instance) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = new ThreadPoolExecutor(4, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), new ThreadPoolExecutor.DiscardPolicy());
        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("输出1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("输出2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("输出3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
      /*  FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });*/
        Future<?> future = executorService.submit(() -> {
            System.out.println("输出4");
            try {
                int i = 1 / 0;
            } catch (Exception e) {
                e.printStackTrace();
            }

            //return "hello world";
        });
        /*String result = future.get();
        System.out.println("输出结果....");
        System.out.println(result);*/
        executorService.shutdown();
    }

}
