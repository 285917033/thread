package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DivTask implements Runnable {

	int a, b;

	public DivTask(int a, int b) {
		this.a = a;
		this.b = b;
	}

	public void run() {
		System.out.println(a / b);

	}

	public static void main(String[] args) {

		ExecutorService es = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0l, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>());
		
		for(int i=0;i<5;i++) {
			es.execute(new DivTask(100,i));
		}

	}

}
