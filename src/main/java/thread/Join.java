package thread;

public class Join {

	public volatile static int i=0;
	
	public static class AddThread extends Thread{
		@Override
		public void run() {
			for(i=1;i<10000;i++);
		}
	}
	
	public static void main(String[] args) {
		
		AddThread a = new AddThread();
		a.start();
	    try {
		    a.join(); //��ʾ a�̼߳������߳�һ��ִ�У� a�߳�ִ����֮����ִ�����̣߳� 
			//���ƴ���ִ��
	    	//Thread.yield();  yield�ó�ʱ��Ƭ�����������߳�ִ��һ�ᣬȻ���Լ�����ִ��
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(i);

	}

}
