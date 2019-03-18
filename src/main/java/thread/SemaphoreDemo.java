package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 
 * ，synchronized,reentrantlock一次只允许一个线程访问一个资源，
 * 而信号量， semaphore指定多少个线程可以同时访问一个资源
 * @author jwh
 *
 */
public class SemaphoreDemo implements Runnable{
	
	//表示同时5个线程可以同时访问一个资源
   final Semaphore semaphore = new Semaphore(5);
	

	public void run() {
		
		try {
			//申请一个准入的许可
			semaphore.acquire();
			//模拟操作耗时
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getId()+"完成");
			semaphore.release(); //释放资源
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		
		//声明20个线程
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		final SemaphoreDemo demo = new SemaphoreDemo();
		
		for(int i=0;i<20;i++) {
			executorService.submit(demo);
		}
		
		
	}
}
