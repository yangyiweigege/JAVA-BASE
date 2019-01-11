package com.weige.ssm.thread.group;

import java.util.HashSet;

/**
 * java守护线程
 * 
 * @author yangyiwei
 * @date 2018年10月16日
 * @time 上午10:27:14
 */
public class DaemonDemo {

	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			while (true) {
				System.out.println("I am alive");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.setDaemon(true);
		t.start(); // 守护线程中 一定要先设置为守护线程 再start 否则有异常
		try {
			Thread.sleep(5000);
			System.out.println("主程序退出线程...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
