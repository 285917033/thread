package thread;

public class AccountingVol implements Runnable{

	
	static AccountingVol av= new AccountingVol();
	
	volatile static  int i=0;
	
	public synchronized static void increase() {
		
		i++;
	}
	
	

	public void run() {
		
		for(int j=0;j<10000;j++) {
			increase();
		}
	}
	public static void main(String[] args) {
		Thread t1 = new Thread(av);
		Thread t2 = new Thread(av);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(i);
	}

}
