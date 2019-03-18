package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 
 * ��synchronized,reentrantlockһ��ֻ����һ���̷߳���һ����Դ��
 * ���ź����� semaphoreָ�����ٸ��߳̿���ͬʱ����һ����Դ
 * @author jwh
 *
 */
public class SemaphoreDemo implements Runnable{
	
	//��ʾͬʱ5���߳̿���ͬʱ����һ����Դ
   final Semaphore semaphore = new Semaphore(5);
	

	public void run() {
		
		try {
			//����һ��׼������
			semaphore.acquire();
			//ģ�������ʱ
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getId()+"���");
			semaphore.release(); //�ͷ���Դ
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		
		//����20���߳�
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		final SemaphoreDemo demo = new SemaphoreDemo();
		
		for(int i=0;i<20;i++) {
			executorService.submit(demo);
		}
		
		
	}
}
