package thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时器 CountDownLatch
 * 应用场景：在火箭发射前，为了保证万无一失，经常会对各种设备，仪器进行检查
 * 只有等待所有检查完毕之后，才能发射
 * @author jwh
 *
 */
public class CountDownLatchDemo implements Runnable {

	//表示有10个线程完成任务
	static final CountDownLatch end = new CountDownLatch(10);
	
	static final CountDownLatchDemo demo = new CountDownLatchDemo();
	
	
	public void run() {
		try {
			Thread.sleep(new Random().nextInt(10)*1000);
			System.out.println("检查完成....");
			end.countDown(); //countDown表示一个线程已经完成了任务，倒计时器可以减1了
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
			//要求主线程等待所有10个检查任务全部完成，主线程才能执行
			end.await();
			
			System.out.println("已经准备好...开始工作");
			exe.shutdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
