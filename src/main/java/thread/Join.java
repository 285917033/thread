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
		    a.join(); //表示 a线程加入主线程一起执行， a线程执行完之后，再执行主线程， 
			//类似串行执行
	    	//Thread.yield();  yield让出时间片，让其他的线程执行一会，然后自己再来执行
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(i);

	}

}
