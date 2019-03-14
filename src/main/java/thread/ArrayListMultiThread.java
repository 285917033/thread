package thread;

import java.util.ArrayList;
import java.util.Vector;

/**
 * ArrayList���̰߳�ȫ
 * vector�̰߳�ȫ
 * ,hashmap�̲߳���ȫ����ConcurrentHashMap ����
 * hashtable�̰߳�ȫ
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
