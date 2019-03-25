package thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ����ʱ�� CountDownLatch
 * Ӧ�ó������ڻ������ǰ��Ϊ�˱�֤����һʧ��������Ը����豸���������м��
 * ֻ�еȴ����м�����֮�󣬲��ܷ���
 * @author jwh
 *
 */
public class CountDownLatchDemo implements Runnable {

	//��ʾ��10���߳��������
	static final CountDownLatch end = new CountDownLatch(10);
	
	static final CountDownLatchDemo demo = new CountDownLatchDemo();
	
	
	public void run() {
		try {
			Thread.sleep(new Random().nextInt(10)*1000);
			System.out.println("������....");
			end.countDown(); //countDown��ʾһ���߳��Ѿ���������񣬵���ʱ�����Լ�1��
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		ExecutorService exe = Executors.newFixedThreadPool(10);
		for(int i=0;i<10;i++) {
			exe.submit(demo);
		}
		
		try {
			//Ҫ�����̵߳ȴ�����10���������ȫ����ɣ����̲߳���ִ��
			end.await();
			
			System.out.println("�Ѿ�׼����...��ʼ����");
			exe.shutdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
