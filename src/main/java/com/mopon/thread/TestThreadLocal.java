package com.mopon.thread;
/**
 * 多线程中， 每个线程保存一份自己的本地变量，
 * @author zgh
 *
 */
public class TestThreadLocal {

	
	static void print(String str) {
		
		System.out.println(str+":"+localVar.get());
		localVar.remove();
	}
	
	
	static ThreadLocal<String>  localVar = new ThreadLocal<String>();
	
	public static void main(String[] args) {
		 
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				
				localVar.set("t local var ");
				
				print("t thread");
				
				System.out.println("t after local var is "+localVar.get());
				
			}
			
			
		});
		

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				
				localVar.set("t2 local var ");
				
				print("t2 thread");
				
				System.out.println("t2 after local var is "+localVar.get());
				
			}
			
			
		});
		
		t.start();
		t2.start();

	}

}
