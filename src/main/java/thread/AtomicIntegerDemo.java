package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger �������ͣ� �̰߳�ȫ�� ���ܱȼ�������ߣ��������
 * ���Ƶ���AtomicLong,AtomicBoolean AtomicReference
 * @author jwh
 *
 */
public class AtomicIntegerDemo {
	
	static AtomicInteger i = new AtomicInteger();
	
	public static class AddThread implements Runnable{

		public void run() {
			
			for (int j=0;j<10000;j++) {
				//��ǰֵ���� 1��������ֵ
				i.incrementAndGet();
			}
			
		}
		
	}
	
	
	public static void main(String[] args) {
		
		ExecutorService es = Executors.newFixedThreadPool(10);
		
		es.submit(new AddThread() );
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(i);
	}

}
