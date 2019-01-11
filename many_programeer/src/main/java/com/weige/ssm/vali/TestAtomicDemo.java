package com.weige.ssm.vali;

public class TestAtomicDemo {
	public static void main(String[] args) {

		/*AtomicDemo ad = new AtomicDemo();

		for (int i = 0; i < 10; i++) {
			new Thread(ad).start();
		}*/
		Integer a = new Integer(10);
		Integer b = new Integer(10);
		System.out.println(a==b);
	}
}
