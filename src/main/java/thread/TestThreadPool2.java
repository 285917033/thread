package thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 利用java的ThreadLocal实现 (避免实现锁的开销)
 * @author zgh
 *
 */
public class TestThreadPool2 implements Runnable {

	
	private final ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		};
	};
	
	
	int i=0;
	
	
	public TestThreadPool2(int i) {
		this.i = i;
	}

	public void run() {
		try {
				Date t =local.get().parse("2019-03-21 16:29:"+i%60);
				System.out.println(i+":"+t);
				
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		
		ExecutorService es = Executors.newFixedThreadPool(20);
		
		for(int i=0;i<100;i++) {
			
			es.execute(new TestThreadPool2(i));
		}
	}

}
