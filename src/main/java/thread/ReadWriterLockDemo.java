package thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * ��ͨ����synchronize��reentrantlock��ͬһʱ�����߳��Ǵ���ִ�еģ� 
 * ����д���������Ĺ����ǿ���������ܣ� ��ͬһʱ���ڶ���߳̿���ʵ�ֲ���ִ�У�
 * �ر��ǶԶ����д����£����������������������ƻ���������߳̽���ͬʱ�������磬
 * a1,a2,a3 ,��a1���ж�ʱ��a2,a3����Ҫ�ȴ������ֵȴ���Ȼ���������
 * ��д���������˷��ӵ���ء�
 * @author jwh
 *
 */
public class ReadWriterLockDemo {
	
	//��ͨ��
	private static Lock lock = new ReentrantLock();
	
	//��д������
	private static ReentrantReadWriteLock  readWriterLock = new ReentrantReadWriteLock();
	
	private static Lock readLock = readWriterLock.readLock();
	
	private static Lock writerLock = readWriterLock.writeLock();
	
	private static int value;

	
	/**
	 * ������
	 * @param lock
	 * @return
	 * @throws Exception
	 */
	public Object handleRead(Lock lock) throws Exception{
		
		try {
		lock.lock(); //��ȡ��
		Thread.sleep(2000); //ģ������������Ĳ�����ʱԽ�࣬��д�������ƾ�Խ����
		return value;
		}finally {
			lock.unlock(); //�ͷ���
		}
	}
	
	
	/**
	 * д����
	 * @param lock
	 * @param index
	 * @throws Exception
	 */
	public void handleWriter(Lock lock,int index)throws Exception{
		try {
			
			//��ȡ��
			lock.lock();
			Thread.sleep(1000); //ģ��д����
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
