package com.weige.ssm.thread.safe;

/**
 * 测试多线程环境下 long属性
 * 64位机器下 不会出现这个状况
 * @author yangyiwei
 * @date 2018年10月12日
 * @time 上午9:49:21
 */
public class MultiThreadLong {

	public static long t;

	public static class changeT implements Runnable {

		private long t0;

		public changeT(long t0) {
			this.t0 = t0;
		}

		@Override
		public void run() {
			while (true) {
				MultiThreadLong.t = t0;
				Thread.yield();
			}
		}

		public static class ReadT implements Runnable {

			@Override
			public void run() {

				while (true) {
					long temp = MultiThreadLong.t;
					//System.out.println("精确修改：" + temp);
					if (temp != 111L && temp != -999L & temp != 333L && temp != -444L) {
						System.out.println("未准确修改：" + temp);
						Thread.yield();
					}
				}

			}

		}

		public static void main(String[] args) {
			new Thread(new changeT(111L)).start();
			new Thread(new changeT(-999L)).start();
			new Thread(new changeT(333L)).start();
			new Thread(new changeT(-444L)).start();
			new Thread(new ReadT()).start();
			new Thread(() -> {}).start();

		}

	}
}
