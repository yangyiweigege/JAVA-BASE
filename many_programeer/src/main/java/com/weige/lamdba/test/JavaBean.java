package com.weige.lamdba.test;

public class JavaBean<T> extends BaseModel<T>{
	
	private String aString ="abcd";
	
	public void add() {
		aString = aString + "1";
		
		
	}
	
	public String get() {
		return aString;
	}
	
	
	public static void main(String[] args) {
		JavaBean<String> javaBean = new JavaBean<>();
		new Thread(() -> {
			javaBean.add();
		}).start();
		new Thread(() -> {
			javaBean.add();
		}).start();
		new Thread(() -> {
			javaBean.add();
		}).start();
		new Thread(() -> {
			javaBean.add();
		}).start();
		new Thread(() -> {
			javaBean.add();
		}).start();
	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(javaBean.get());
	}

}
