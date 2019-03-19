package thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 普通锁，synchronize，reentrantlock，同一时间多个线程是串行执行的， 
 * 而读写分离锁核心功能是可以提高性能， 在同一时间内多个线程可以实现并行执行，
 * 特别是对多读少写情况下，读读操作不会对数据造成破坏，，多个线程进行同时读，例如，
 * a1,a2,a3 ,当a1进行读时，a2,a3则需要等待，这种等待显然不合理，因此
 * 读写锁分离有了发挥的余地。
 * @author jwh
 *
 */
public class ReadWriterLockDemo {
	
	//普通锁
	private static Lock lock = new ReentrantLock();
	
	//读写分离锁
	private static ReentrantReadWriteLock  readWriterLock = new ReentrantReadWriteLock();
	
	private static Lock readLock = readWriterLock.readLock();
	
	private static Lock writerLock = readWriterLock.writeLock();
	
	private static int value;

	
	/**
	 * 读操作
	 * @param lock
	 * @return
	 * @throws Exception
	 */
	public Object handleRead(Lock lock) throws Exception{
		
		try {
		lock.lock(); //获取锁
		Thread.sleep(2000); //模拟读操作，读的操作耗时越多，读写锁的优势就越明显
		return value;
		}finally {
			lock.unlock(); //释放锁
		}
	}
	
	
	/**
	 * 写操作
	 * @param lock
	 * @param index
	 * @throws Exception
	 */
	public void handleWriter(Lock lock,int index)throws Exception{
		try {
			
			//获取锁
			lock.lock();
			Thread.sleep(1000); //模拟写操作
			value=index;
		}finally {
			
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {

		final ReadWriterLockDemo demo = new ReadWriterLockDemo();
		Runnable readRunable = new Runnable() {

			public void run() {
				try {
				//System.out.println(	"result="+demo.handleRead(readLock));
					System.out.println(	"result="+demo.handleRead(lock));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}};
			
			Runnable writeRunnable  = new Runnable() {

				public void run() {
					
					try {
						//demo.handleWriter(writerLock, new Random().nextInt(10));
						demo.handleWriter(lock, new Random().nextInt(10));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}};
				
				for(int i=0;i<18;i++) {
					new Thread(readRunable).start();
				}
				
				for(int i=18;i<20;i++) {
					new Thread(writeRunnable).start();
				}
	}

}
