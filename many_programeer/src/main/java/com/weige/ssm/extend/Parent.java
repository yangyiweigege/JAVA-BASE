package com.weige.ssm.extend;

import java.lang.reflect.Method;

public class Parent {

	private String name = "parent";

	public String getName() {
		System.out.println("当前类:" + this.getClass().getName());
		Son son = (Son)this;
		System.out.println(son.getName1());
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void showType() {
		System.out.println("类型" + this.getClass().getName());
	}

	public static void main(String[] args) {
		Parent parent = new Parent ();
		Class<?> class1 = parent.getClass();
	
		System.out.println(parent.getName());
	}

}
