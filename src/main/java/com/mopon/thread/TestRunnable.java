package com.mopon.thread;

public class TestRunnable  implements Runnable{

	private Integer index;
	
	
	
	public TestRunnable(Integer index) {
		this.index = index;
	}

   

	public Integer getIndex() {
		return index;
	}




	@Override
	public void run() {
	
		Thread t = Thread.currentThread();
		
		synchronized (t) {
			
			try {
				t.wait(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
