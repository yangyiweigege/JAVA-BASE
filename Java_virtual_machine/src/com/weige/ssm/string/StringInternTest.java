package com.weige.ssm.string;

public class StringInternTest {

	public static void main(String[] args) {
		String s1 = new String("1") + new String("1");

		String s2 = "11";
		s1.intern();
		System.out.println(s1 == s2);

		String string = "abc";
		System.out.println(string.substring(0, string.length()));

		StringInternTest stringInternTest = new StringInternTest();
		new Thread(() -> {
			stringInternTest.exec2();
		}).start();
		new Thread(() -> {
			stringInternTest.exec1();
		}).start();
	}

	public synchronized  void exec1() {
		try {
			System.out.println("执行1");
			Thread.sleep(2000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized static void exec2() {
		try {
			System.out.println("执行2");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
