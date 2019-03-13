package thread;
/**
 * 1.wait() ����ķ�����ʱ�������ȴ������У� �ȴ������Ķ�����
 * 2.notify() ����ķ���,������ѵȴ������� ��ĳһ������notifyAll()���ѵȴ������е����ж���
 * ��������wait�����󣬱���Ҫ�ȴ�����notify����֮�����ִ��
 * ������ ִ��wait������notify����֮�� �������ͷ�object�������� 
 * 
 * 3.Thread.sleep(), 
 * wait()������sleep�������������Ƕ��������̵߳ȴ�����ʱ�䣬����wait�������Ա������⣬
 * ����һ��ԭ���ǣ�wait�������ͷ�Ŀ������������sleep���������ͷ��κ���Դ
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
					System.out.println(System.currentTimeMillis()+"T1 �ȴ�");
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
				System.out.println(System.currentTimeMillis()+"T2 ����");
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
