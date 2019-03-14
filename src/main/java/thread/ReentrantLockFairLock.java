package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ��ƽ��
 * 
 * @author jwh
 *
 */
public class ReentrantLockFairLock implements Runnable {

	// Ĭ������� Ϊ�ǹ�ƽ���������Ҫ��ƽ���� ����������true����
	public static ReentrantLock reentrantlock = new ReentrantLock(true);

	public static void main(String[] args) {
		ReentrantLockFairLock rf =new ReentrantLockFairLock();
		Thread t1= new Thread(rf,"�߳�һ...");
		Thread t2= new Thread(rf,"�̶߳�>>>>>");
		t1.start();
		t2.start();
		
	}

	public void run() {
		while (true) {

			try {
				reentrantlock.lock();
				System.out.println(Thread.currentThread().getName() + "��ȡ��");
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
			reentrantlock.unlock();
			}
		}
	}

}
