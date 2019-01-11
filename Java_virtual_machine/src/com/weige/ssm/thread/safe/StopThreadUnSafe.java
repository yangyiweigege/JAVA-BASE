package com.weige.ssm.thread.safe;

/**
 * 使用stop方法强行终止线程 造成数据不一致情况
 * 
 * @author yangyiwei
 * @date 2018年10月12日
 * @time 下午2:59:14
 */
public class StopThreadUnSafe {
	public static User user = new User();

	public static class User {

		private String name;

		private int id;

		public User() {
			id = 0;
			name = "0";
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "User [name=" + name + ", id=" + id + "]";
		}

	}

	public static class ChangeObjectThread extends Thread {
		public void run() {
			while (true) {
				synchronized (user) {
					int id = (int) (System.currentTimeMillis() / 1000);
					user.setId(id);

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					user.setName(id + "");
				}
				Thread.yield();
			}
		}
	}

	public static class ReadObjectThread extends Thread {

		public void run() {
			while (true) {
				synchronized (user) {
					if (user.getId() != Integer.parseInt(user.getName())) {
						System.out.println(user.toString());
					}
					Thread.yield();
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {

		/// new ReadObjectThread().start();
		// while (true) {
		// Thread thread = new ChangeObjectThread();
		// thread.start();
		// Thread.sleep(150);
		// thread.stop();
		// }
		Thread thread = new Thread(() -> {
			while (true) {
				if (Thread.currentThread().isInterrupted()) {
					break;
				}
				try {
					Thread.sleep(1200);
					System.out.println("执行一波...");
				} catch (InterruptedException e) {
					//执行中断
					Thread.currentThread().interrupt();
					e.printStackTrace();
				}
				Thread.yield();
			}
		});
		thread.start();
		Thread.sleep(2000);
		thread.interrupt();
	}
}
