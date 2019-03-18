package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ������ ReentrantLock ��Condition���һ��ʹ��
 * @author jwh
 *
 */
public class ReentrantLockOfCondition implements Runnable {
	
	//������
	public static ReentrantLock reentrantLock = new ReentrantLock();
	
	//condition
	public static Condition condition = reentrantLock.newCondition();

	public void run() {
		try {
			//��ȡ��
			reentrantLock.lock();
			condition.await(); //���õ�ǰ�߳̽���ȴ�
			
			System.out.println("�ȴ��������Ѿ�����");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			//�ͷ���
			reentrantLock.unlock();
		}
	}

	public static void main(String[] args) {

		ReentrantLockOfCondition rc = new ReentrantLockOfCondition();
		Thread t1 = new Thread(rc);
		t1.start();
		try {
			Thread.sleep(1000);
			//֪ͨ�߳�1ִ��
			reentrantLock.lock(); //��ȡ�߳���
			condition.signal(); //���ѵȴ��е��߳�
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("�ͷ���Դ");
			reentrantLock.unlock();//�ͷ���
			
		}
	}

}
