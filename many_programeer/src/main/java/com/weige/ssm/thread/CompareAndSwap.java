package com.weige.ssm.thread;

/**
 * 模拟cas
 * 
 * @author yangyiwei
 * @date 2018年6月29日
 * @time 上午11:31:15
 */
public class CompareAndSwap {

	private volatile int value;

	// 获取内存值
	public int get() {
		return value;
	}

	// 无论更新成功与否,都返回修改之前的内存值
	public int compareAndSwap(int expectedValue, int newValue) {
		// 获取旧值
		int oldValue = value; //两条都是10
                                   //
		if (oldValue == expectedValue) {
			this.value = newValue;
		}

		// 返回修改之前的值
		return oldValue;
	}

	// 判断是否设置成功
	public boolean compareAndSet(int expectedValue, int newValue) {
		return expectedValue == compareAndSwap(expectedValue, newValue);
	}

	public static void main(String[] args) {
		final CompareAndSwap cas = new CompareAndSwap();

		for (int i = 0; i < 50; i++) {
			// 创建10个线程,模拟多线程环境
			new Thread(() -> {
					int expectedValue = cas.get(); //两条线程 获取的旧职都是5 新职 1 10

					boolean b = cas.compareAndSet(expectedValue, (int) (Math.random() * 100));
					System.out.println(b);
				}
			).start();
		}
	}
}
