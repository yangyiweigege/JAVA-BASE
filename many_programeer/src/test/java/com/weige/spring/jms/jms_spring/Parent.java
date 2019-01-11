package com.weige.spring.jms.jms_spring;


class Parent {
	
	
	public Parent() {
		init();
	}
	
	
	public void init() {
		System.out.println("这是你爸爸的");
		//Son son = (Son) this;
	}

	public static void main(String[] args) {
		new Parent();
	}
}

class Son extends Parent {
	public Son() {
		
	}
	
	public void init() {
		System.out.println("这是你儿子的");
	}
	
}
