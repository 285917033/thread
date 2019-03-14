package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ���������� synchronize���ܣ� ֻ�Ǳ���������
 *�ṩ�ж���Ӧ
 *�ṩ��������ʱ
 *�ṩ��ƽ����ͨ��������ʽ�����Ŷӣ����Բ����������������Ҫά��һ������(synchronize����������ʱ�������������Ƿǹ�ƽ������ƽ�������ܣ�Ĭ����������ǹ�ƽ����)
 * @author jwh
 * 
 * 1.lock��ȡ�����������ռ�ã���ȴ�
 * 2.lockInterruptibly();��ȡ������������Ӧ�ж�
 * 3.tryLock���Ի����������ɹ��򷵻�true,ʧ�ܷ���false���÷������ȵ�����������
 * 4.tryLock(long time,TimeUnit unit) �ڸ�����ʱ���ڳ��Ի�ȡ��
 * 5.unlock()�ͷ���
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
