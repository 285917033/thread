package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁类似 synchronize功能， 只是比他灵活好用
 *提供中断响应
 *提供锁申请限时
 *提供公平锁，通过队列形式进行排队，所以不会产生饥饿现象，需要维护一个队列(synchronize进行锁控制时，产生的锁就是非公平锁，公平锁耗性能，默认情况下所非公平锁，)
 * @author jwh
 * 
 * 1.lock获取锁，如果锁被占用，则等待
 * 2.lockInterruptibly();获取锁，但优先响应中断
 * 3.tryLock尝试获得锁，如果成功则返回true,失败返回false，该方法不等到，立即返回
 * 4.tryLock(long time,TimeUnit unit) 在给定的时间内尝试获取锁
 * 5.unlock()释放锁
 *
 */
public class ReentrantLockThread implements Runnable {
	
	ReentrantLock reentrantLock = new ReentrantLock();

	static int i=0;
	
	public void run() {

		for(int j=0;j<100000;j++) {
			
			reentrantLock.lock();
			
			try {
				
				i++;
			}finally {
				reentrantLock.unlock();
			}
		}
	}

	
	public static void main(String[] args) {
		
		ReentrantLockThread  rt = new ReentrantLockThread();
		Thread t = new Thread(rt);
		Thread t2 = new Thread(rt);
		t.start();
		t2.start();
		try {
			t.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(i);
	}
	
}
