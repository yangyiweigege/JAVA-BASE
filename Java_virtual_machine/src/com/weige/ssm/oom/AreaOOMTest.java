package com.weige.ssm.oom;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 用来测试各个区域的oom
 * 
 * @author yangyiwei
 * @date 2018年10月9日
 * @time 上午9:55:54
 */
public class AreaOOMTest {


	public static void main(String[] args) {
		heapOOM();
	}

	/**
	 * 测试java内存模型中的堆区域溢出
	 */
	//@Test
	public static void heapOOM() {
		List<AreaOOMTest> list = new ArrayList<>();
		while (true) {
			list.add(new AreaOOMTest());
		}
	}

	/**
	 * 测试java内存模型中的栈溢出
	 */
	@Test
	public void stackOverFlow() {
		stackOverFlow();
	}

	/**
	 * 建立过多线程 导致OOM(每条线程栈2M 过多线程消耗完内存)
	 */
	// @Test
	public void threadOOM() {
		while (true) {
			new Thread(() -> {
				while (true) {
					/*
					 * try { Thread.sleep(2000); } catch (InterruptedException
					 * e) { // TODO Auto-generated catch block
					 * e.printStackTrace(); } System.out.println("执行线程....");
					 */
				}

			}).start();
		}
	}

	/**
	 * 方法区(常量池溢出oom)
	 */
	@Test
	public void runTimeConstantPoolOOM() {
		/*
		 * 此段代码在jdk1.7后 不会溢出 因为字符串常量池已经被挪到堆中 List<String> list = new
		 * ArrayList<>(); int i = 0; while (true) {
		 * list.add(String.valueOf(i++).intern()); }
		 */
		String stra = new StringBuilder("计算机").append("软件").toString();
		stra.intern();
		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern() == str1);
		String str2 = new StringBuilder("ja").append("va").toString();
		System.out.println(str2.intern() == str1);
	}

}
