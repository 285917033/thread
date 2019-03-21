package thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * �ƻ������������
 * @author jwh
 *
 */
public class ScheduleExecutorServiceDemo {
	public static void main(String[] args) {
		
		//�����̳߳�
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
		
		//���ǰ������δ��ɣ������Ҳ��������
		ses.scheduleAtFixedRate(new Runnable() {

			public void run() {
				
				try {
					Thread.sleep(1000);
					System.out.println(System.currentTimeMillis()/1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}}, 0, 3, TimeUnit.SECONDS);
		
	}

}
