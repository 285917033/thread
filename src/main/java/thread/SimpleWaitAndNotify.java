package thread;
/**
 * 1.wait() 对象的方法，时对象进入等待队列中， 等待其他的对象唤醒
 * 2.notify() 对象的方法,随机唤醒等待队列中 的某一个对象，notifyAll()唤醒等待队列中的所有对象
 * 当被调用wait方法后，必须要等待调用notify方法之后才能执行
 * 不管是 执行wait方法和notify方法之后 ，都会释放object监视器， 
 * 
 * 3.Thread.sleep(), 
 * wait()方法与sleep方法的区别，他们都可以让线程等待若干时间，除了wait方法可以被唤醒外，
 * 另外一个原因是，wait方法会释放目标对象的锁，而sleep方法不会释放任何资源
 * @author jwh
 *
 */
public class SimpleWaitAndNotify {
	
	final static Object object = new Object();
	
	
	public static class T1 extends Thread{
		@Override
		public void run() {
			synchronized (object) {
				System.out.println(System.currentTimeMillis()+":T1 start");
				try {
					System.out.println(System.currentTimeMillis()+"T1 等待");
					object.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(System.currentTimeMillis()+":T1 end");

				
			}
			
			
		}
	}
	

	public static class T2 extends Thread{
		@Override
		public void run() {
			synchronized (object) {
				System.out.println(System.currentTimeMillis()+":T2 start" );
				System.out.println(System.currentTimeMillis()+"T2 唤醒");
				object.notify();
				System.out.println(System.currentTimeMillis()+":T2 end");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}
		}
	}
	

	public static void main(String[] args) {

		T1 t1 = new T1();
		T2 t2 = new T2();
		
		t1.start();
		t2.start();
	}

}
