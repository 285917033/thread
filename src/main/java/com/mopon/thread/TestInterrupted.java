package com.mopon.thread;
/**
 * ��һ���̴߳���˯��״̬ʱ����������һ���߳��ж����������ڵ���sleep�����׳��쳣
 * @author zgh
 *
 */
public class TestInterrupted {

	public static void main(String[] args) {
		
		Thread t =  new Thread(new Runnable() {

			@Override
			public void run() {
				
				System.out.println("start sleep");
				try {
					Thread.sleep(3000);
					System.out.println("end sleep");

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			 
			 
		 });
		 
		t.start();
		 try {
			Thread.sleep(1000);
			t.interrupt();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
