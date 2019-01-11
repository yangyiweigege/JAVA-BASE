package com.weige.ssm.thread.safe;

/**
 * 测试vilodate可见性
 * 
 * @author yangyiwei
 * @date 2018年10月12日
 * @time 下午5:05:11
 */
public class NoVisibility extends Thread {

	public static void main(String[] args) {
		boolean read = true;
		new Thread(() -> {
			while (read) {
				;
			}
			System.out.println("退出");
		}).start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// read = false;
	}
}
