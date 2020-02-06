package com.mopon.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

	private static final CountDownLatch latch = new CountDownLatch(5);
	
	private static int data;
	
	
	public static void main(String[] args) {

		Thread workerThread = new Thread() {
			
			@Override
			public void run() {
				
				for(int i =1;i<10;i++) {
					
					data = i;
					latch.countDown();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
		};
		
		
		workerThread.start();
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("data==="+data);
	}

}
