package com.mopon.thread;

/**
 * 唤醒notify ，object的 wait()方法
 * 
 * @author zgh
 *
 */
public class TestNotify {

	// 创建资源
	private static volatile Object a = new Object();
	

	public static void main(String[] args) {

		Thread t = new Thread(new Runnable() {
			

			@Override
			public void run() {

				synchronized (a) {

					System.out.println("t get a resource...");

					try {
						System.out.println("t wait start");
						a.wait();
						System.out.println("t wait end....");

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {

				synchronized (a) {
					System.out.println("t2 get a resource...");

					try {
						System.out.println("t2 wait start");
						a.wait();
						System.out.println("t2 wait end....");

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		});
		
		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				
				synchronized (a) {
					System.out.println("t3 begin notify");
					//a.notify();
					a.notifyAll();
				}
				
			}
			
		});
		
		t.start();
		t2.start();
		try {
			Thread.sleep(1000);
			t3.start();
			t.join();
			t2.join();
			t3.join();
			System.out.println("main over");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
