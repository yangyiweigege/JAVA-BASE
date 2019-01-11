package com.weige.ssm.invoke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
public class Car implements Run{

	@Override
	@ProxyMethod("runnning======")
	public void runnning() {
		System.out.println("快速开车....");
	}

	@Override
	@ProxyMethod("runnning")
	public void walking() {
		System.out.println("慢慢开车...不慌");
	}
	
	public int showType(int a) {
		System.out.println("hahah");
		return a;
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Run car = new Car();
		Method method = car.getClass().getMethod("runnning");
		ProxyMethod proxyMethod = method.getAnnotation(ProxyMethod.class);
		System.out.println(proxyMethod.value());
	}

}
