package com.weige.ssm.gc;

/**
 * java垃圾回收机制测试
 * @author yangyiwei
 * @date 2018年10月9日
 * @time 下午5:37:20
 */
public class JavaGCTest {

	public Object instance = null;
	/**
	 * 
	 */
	
	private byte[] bigSize = new byte[2 * _1mb];
	
	private static final int _1mb = 1024 * 1024;
	
	
	
	/**
	 * 通过此结果 可以判定 hotspot采用的是引用不可达算法 
	 */
	public static void referenceCountGc() {
		JavaGCTest gcOne = new JavaGCTest();
		JavaGCTest gcTwo = new JavaGCTest();
		gcOne.instance = gcTwo;
		gcTwo.instance = gcOne;
		gcOne = null;
		gcTwo = null;
		System.gc();
	}
	
	public static void main(String[] args) {
		referenceCountGc();
	}
}
