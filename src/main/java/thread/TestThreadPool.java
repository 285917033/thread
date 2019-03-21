package thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadPool implements Runnable {

	
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	int i=0;
	
	
	public TestThreadPool(int i) {
		this.i = i;
	}


	public void run() {
		
		try {
			
			synchronized (sdf) {
				
				Date t = sdf.parse("2019-03-21 16:29:"+i%60);
				System.out.println(i+":"+t);
				
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		
		ExecutorService es = Executors.newFixedThreadPool(20);
		
		for(int i=0;i<100;i++) {
			
			es.execute(new TestThreadPool(i));
		}
	}

}
