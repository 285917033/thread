package thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 为每一个线程分配不同的对象，需要在应用层面保证，ThreadLocal只是起到了简单的容器作用
 * @author jwh
 *
 */
public class TestThreadLocal implements Runnable {

	private static ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<SimpleDateFormat>();

	int i = 0;

	public TestThreadLocal(int i) {
		this.i = i;
	}

	public void run() {

		try {

			if (t1.get() == null) {
				t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			}

			Date d = t1.get().parse("2019-03-21 16:29:" + i % 60);

			System.out.println(i + ":" + d);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		ExecutorService es = Executors.newFixedThreadPool(20);

		for (int i = 0; i < 100; i++) {

			es.execute(new TestThreadLocal(i));
		}
	}

}
