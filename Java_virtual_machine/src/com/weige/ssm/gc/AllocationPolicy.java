package com.weige.ssm.gc;

/**
 * 测试 java的内存回收机制
 * 
 * @author yangyiwei
 * @date 2018年11月14日
 * @time 下午5:20:11
 */
public class AllocationPolicy {

	private static final int _1MB = 1024 * 1024;

	public static void main(String[] args) {
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[13 * _1MB];
		allocation2= new byte[1 * _1MB];
	//	allocation3 = new byte[4 * _1MB];
	/*	allocation3 = new byte[2 * _1MB];
		allocation4 = new byte[2 * _1MB];
		allocation1 = new byte[1 * _1MB];*/
	/*	allocation1 = new byte[5 * _1MB];
		allocation1 = new byte[2 * _1MB];
		allocation1 = new byte[2 * _1MB];*/
		System.out.println("执行");
	}

}
