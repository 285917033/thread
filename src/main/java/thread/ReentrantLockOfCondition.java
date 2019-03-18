package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁 ReentrantLock 与Condition结合一起使用
 * @author jwh
 *
 */
public class ReentrantLockOfCondition implements Runnable {
	
	//重入锁
	public static ReentrantLock reentrantLock = new ReentrantLock();
	
	//condition
	public static Condition condition = reentrantLock.newCondition();

	public void run() {
		try {
			//获取锁
			reentrantLock.lock();
			condition.await(); //设置当前线程进入等待
			
			System.out.println("等待结束，已经唤醒");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			//释放锁
			reentrantLock.unlock();
		}
	}

	public static void main(String[] args) {

		ReentrantLockOfCondition rc = new ReentrantLockOfCondition();
		Thread t1 = new Thread(rc);
		t1.start();
		try {
			Thread.sleep(1000);
			//通知线程1执行
			reentrantLock.lock(); //获取线程锁
			condition.signal(); //唤醒等待中的线程
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("释放资源");
			reentrantLock.unlock();//释放锁
			
		}
	}

}
