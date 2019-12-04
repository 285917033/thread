package com.mopon.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * sleep线程 休眠时，不会释放当前锁， 与object的wait方法不一样， wait方法会挂起当前线程， 然后释放当前线程的锁，只有当
 * 调用object的notify方法，才能唤醒线程，然后继续执行被挂起的线程。
 * 
 * @author zgh
 *
 */
public class TestSleep {

	//独占锁
	private static final Lock lock = new ReentrantLock();
	
	
	public static void main(String[] args) {
		
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				
				//获取锁
				lock.lock();
				
				System.out.println("t sleep ");
				
				try {
					Thread.sleep(2000);
					System.out.println("t sleep awaked");

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					//释放锁
					lock.unlock();
				}
			}
			
		});
		
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				
				//获取锁
				lock.lock();
				
				System.out.println("t2 sleep ");
				
				try {
					Thread.sleep(2000);
					System.out.println("t2 sleep awaked");

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					//释放锁
					lock.unlock();
				}
			}
			
		});
		
		t.start();
		t2.start();

	}

}
