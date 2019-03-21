package thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 计划任务，任务调度
 * @author jwh
 *
 */
public class ScheduleExecutorServiceDemo {
	public static void main(String[] args) {
		
		//调度线程池
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
		
		//如果前面任务未完成，则调度也不会启动
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
