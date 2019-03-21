package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * �̳߳ص���չ
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
				System.out.println("׼��ִ��"+((MyTask)r).name);
			}

			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				System.out.println("ִ�����"+((MyTask)r).name);
			}

			@Override
			protected void terminated() {
				System.out.println("�̳߳��˳�");
			}
			
			
		};
		
		for(int i=0;i<5;i++) {
			MyTask t = new MyTask("�߳�-"+i);
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
