package com.mopon.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * sleep�߳� ����ʱ�������ͷŵ�ǰ���� ��object��wait������һ���� wait���������ǰ�̣߳� Ȼ���ͷŵ�ǰ�̵߳�����ֻ�е�
 * ����object��notify���������ܻ����̣߳�Ȼ�����ִ�б�������̡߳�
 * 
 * @author zgh
 *
 */
public class TestSleep {

	//��ռ��
	private static final Lock lock = new ReentrantLock();
	
	
	public static void main(String[] args) {
		
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				
				//��ȡ��
				lock.lock();
				
				System.out.println("t sleep ");
				
				try {
					Thread.sleep(2000);
					System.out.println("t sleep awaked");

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					//�ͷ���
					lock.unlock();
				}
			}
			
		});
		
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				
				//��ȡ��
				lock.lock();
				
				System.out.println("t2 sleep ");
				
				try {
					Thread.sleep(2000);
					System.out.println("t2 sleep awaked");

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					//�ͷ���
					lock.unlock();
				}
			}
			
		});
		
		t.start();
		t2.start();

	}

}
