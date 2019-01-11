package com.weige.ssm.thread;

public class MyTask extends Thread {
	private int taskNum;

	public MyTask(int num) {
		this.taskNum = num;
	}

	@Override
	public void run() {

		System.out.println("正在执行task " + taskNum);
	//	int i = 1 / 0;
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("task " + taskNum + "执行完毕");

	}

	public void show() {
		System.out.println("调用！！！");
	}

	public static void main(String[] args) {
		try {
			new Thread(new MyTask(10)).start();
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("异常");
			e.printStackTrace();
		}
		System.out.println("执行完成");
	}

}
