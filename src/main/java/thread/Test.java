package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test extends Thread {

	private  int  i =100;
	
	@Override
	public void run() {
		
		i--;
		System.out.println(i);
		System.out.println(Thread.currentThread().getName());
	}
	
	
	
	public static void main(String[] args) {
		
		ExecutorService  es = Executors.newFixedThreadPool(20);
		
		for(int i=0;i<30;i++) {
		es.submit(new Test());
		
		}
		es.shutdown();

	}

}
