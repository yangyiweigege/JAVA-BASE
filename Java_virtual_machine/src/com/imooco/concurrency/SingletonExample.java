package com.imooco.concurrency;

public class SingletonExample {

	private SingletonExample() {

	}
	static {
		singletonExample = new SingletonExample();
	}
	private static SingletonExample singletonExample = null;

	public static SingletonExample getInstance() {
		return singletonExample;
	}

	public static void main(String[] args) {
		System.out.println(getInstance());
	}

}
