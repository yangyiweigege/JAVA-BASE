package com.weige.ssm.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogProxy implements InvocationHandler {
	
	private Object object;
	
	public LogProxy() {
	}
	
	public LogProxy(Object object) {
		this.object = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("我就是要"
				+ "代理一下..." + proxy.getClass().getName());
		System.out.println(method.getName());
		ProxyMethod proxyMethod = method.getAnnotation(ProxyMethod.class);
		//if (proxyMethod != null) {
			System.out.println("方法上的注释..." + proxyMethod.value());
		//}
		
		return method.invoke(object, args);
	}
	
	
	public static void main(String[] args) throws Exception{ 
	
		Run car = new Car();
		/*Method method = car.getClass().getMethod("runnning");
		System.out.println(method.getAnnotation(ProxyMethod.class).value());*/
		LogProxy logProxy = new LogProxy(car);
		Run run = (Run) Proxy.newProxyInstance(car.getClass().getClassLoader(), car.getClass().getInterfaces(), logProxy);
		run.runnning();
	}

}
