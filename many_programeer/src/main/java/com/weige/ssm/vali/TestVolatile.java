package com.weige.ssm.vali;

public class TestVolatile {

	public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();
		new Thread(td).start();

		// System.out.println(td.isFlag());
		while (!td.isFlag()) {
			System.out.println("########");
			//break;
		}
		System.out.println("结束");
	}
}
