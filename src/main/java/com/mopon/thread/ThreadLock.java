package com.mopon.thread;

public class ThreadLock {

	private static final Object WAIT_OBJECT  =  new Object();
	
	
	public static void main(String[] args) {
		
		Thread a = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (ThreadLock.WAIT_OBJECT) {
					System.out.println("a....");
				}
				
			}
			
		});
		
		Thread b = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (ThreadLock.WAIT_OBJECT) {
					System.out.println("b....");
				}
				
			}
			
		});
		
		a.start();
		b.start();

	}

}
