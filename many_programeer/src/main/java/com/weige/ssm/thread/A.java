package com.weige.ssm.thread;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class A {

	public void show() {
		System.out.println(this.getClass().getName());
		System.out.println(getClass().getName());
	}

	public synchronized void lock() {
		try {
			System.out.println("我进来了 获取到了锁");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static synchronized void staticLock1() {
		try {
			System.out.println("我进来了 获取到了静态锁1");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static synchronized void staticLock2() {
		try {
			System.out.println("我进来了 获取到了静态锁2");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String string ="b, ,";
		String[] reStrings = string.split(",");
		for (String string2 : reStrings) {
			System.out.println("当前数据:" + string2);
		}
	}

}
