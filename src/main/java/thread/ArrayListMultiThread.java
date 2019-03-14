package thread;

import java.util.ArrayList;
import java.util.Vector;

/**
 * ArrayList非线程安全
 * vector线程安全
 * ,hashmap线程不安全，用ConcurrentHashMap 代替
 * hashtable线程安全
 * 
 * @author jwh
 *
 */
public class ArrayListMultiThread {

	static ArrayList<Integer> list = new ArrayList<Integer>();
	
	//Vector v = new Vector();
	
	public static class AddThread implements Runnable{

		public void run() {
			
			synchronized (list) {
				
				for(int i=0;i<1000000;i++) {
					list.add(i);
				}
			}
		}
		
		
	}
	
	public static void main(String[] args) {

		Thread t1 = new Thread(new AddThread());
		Thread t2 = new Thread(new AddThread());
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list.size());
	}

}
