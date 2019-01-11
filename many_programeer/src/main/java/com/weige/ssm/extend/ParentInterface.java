package com.weige.ssm.extend;

public interface ParentInterface {
	
	public abstract void getName();

	public default void PrinterName() {
		
		System.out.println("这是这个接口");
	}
}
