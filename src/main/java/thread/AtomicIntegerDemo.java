package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger 整数类型， 线程安全， 性能比加锁会更高，建议采用
 * 类似的有AtomicLong,AtomicBoolean AtomicReference
 * @author jwh
 *
 */
public class AtomicIntegerDemo {
	
	static AtomicInteger i = new AtomicInteger();
	
	public static class AddThread implements Runnable{

		public void run() {
			
			for (int j=0;j<10000;j++) {
				//当前值自增 1，返回新值
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
