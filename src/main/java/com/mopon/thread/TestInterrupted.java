package com.mopon.thread;
/**
 * 当一个线程处于睡眠状态时，如果另外的一个线程中断了他，会在调用sleep方法抛出异常
 * @author zgh
 *
 */
public class TestInterrupted {

	public static void main(String[] args) {
		
		Thread t =  new Thread(new Runnable() {

			@Override
			public void run() {
				
				System.out.println("start sleep");
				try {
					Thread.sleep(3000);
					System.out.println("end sleep");

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			 
			 
		 });
		 
		t.start();
		 try {
			Thread.sleep(1000);
			t.interrupt();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
