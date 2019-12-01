package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Ïß³Ì³Ø
 * 
 * @author jwh
 *
 */
public class ThreadPoolDeam {
	
	

	public static class MyTask implements Runnable {

		public void run() {

			System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
		
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public static void main(String[] args) {
		MyTask mt = new MyTask();
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		for(int i=0;i<10;i++) {
			executorService.submit(mt);
		}
	}

}
