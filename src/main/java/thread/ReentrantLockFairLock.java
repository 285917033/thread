package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 * 
 * @author jwh
 *
 */
public class ReentrantLockFairLock implements Runnable {

	// 默认情况下 为非公平锁，如果需要公平锁， 构造器传入true即可
	public static ReentrantLock reentrantlock = new ReentrantLock(true);

	public static void main(String[] args) {
		ReentrantLockFairLock rf =new ReentrantLockFairLock();
		Thread t1= new Thread(rf,"线程一...");
		Thread t2= new Thread(rf,"线程二>>>>>");
		t1.start();
		t2.start();
		
	}

	public void run() {
		while (true) {

			try {
				reentrantlock.lock();
				System.out.println(Thread.currentThread().getName() + "获取锁");
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
			reentrantlock.unlock();
			}
		}
	}

}
