package com.mopon.thread;

/**
 * wait,object的方法，会让当前线程进行阻塞， 等待，释放当前锁，等待object方法的notify()或者notifyAll() 方法的唤醒
 * 而sleep，线程的方法， 在睡眠时不会释放当前锁，
 * 
 * @author zgh
 *
 */
public class TestWait {

	// 声明资源a
	private static volatile Object a = new Object();

	// 声明资源b
	private static volatile Object b = new Object();

	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {

				synchronized (a) {

					System.out.println("t1 获取到a的资源");

					synchronized (b) {

						System.out.println("t1 获取到b的资源");

						// 线程T1阻塞，并且释放获取到a的锁
						try {
							a.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}

			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					synchronized (a) {

						System.out.println("t2 get a lock");

						System.out.println("t2 try get b lock");

						synchronized (b) {
							System.out.println("t2  get b lock");
							System.out.println("t2  release a lock");
							a.wait();
						}

					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main over");

	}

}
