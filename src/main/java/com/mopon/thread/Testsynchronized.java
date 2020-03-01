package com.mopon.thread;

/**
 * 
 * /**
 * 这个类的class对象进行检查。
 
public static synchronized void doSomething() {

}

/**
 * 对这个类的实例化对象进行检查
 
public synchronized void doOtherthing() {

}

 * 
 * @author zgh
 *
 */
public class Testsynchronized implements Runnable{

	private Integer value;
	
	private static Integer NOWVALUE;
	
	public Testsynchronized(Integer value) {
		
		this.value = value;
	}

	private  void doSomething() {
		
		synchronized (this) {
			NOWVALUE = this.value;
			System.out.println("now value = " + NOWVALUE);
		}
	}

	@Override
	public void run() {
		Thread currentThread = Thread.currentThread();
        Long id = currentThread.getId();
        System.out.println("id = " + id);
		doSomething();
	}
	public static void main(String[] args) {
		Thread t = new Thread( new Testsynchronized(1));
		Thread t2 = new Thread( new Testsynchronized(5));
		t.start();
		t2.start();
		
	}

}
