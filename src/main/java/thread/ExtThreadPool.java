package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的扩展
 * 
 * @author jwh
 *
 */
public class ExtThreadPool {

	public static class MyTask implements Runnable {

		public String name;

		public MyTask(String name) {
			this.name = name;
		}

		public void run() {

			System.out.println("run...Thread id:" + Thread.currentThread().getId() + name);

		}

	}

	public static void main(String[] args) {
		
		ExecutorService es = new ThreadPoolExecutor(5, 5, 0l, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()) {

			@Override
			protected void beforeExecute(Thread t, Runnable r) {
				System.out.println("准备执行"+((MyTask)r).name);
			}

			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				System.out.println("执行完成"+((MyTask)r).name);
			}

			@Override
			protected void terminated() {
				System.out.println("线程池退出");
			}
			
			
		};
		
		for(int i=0;i<5;i++) {
			MyTask t = new MyTask("线程-"+i);
			es.execute(t);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		es.shutdown();

	}

}
