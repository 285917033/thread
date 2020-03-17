package com.mopon.thread;

import java.util.concurrent.LinkedTransferQueue;

/**
 *  * ģ�������� ��������
 * @author zgh
 *
 */
public class ConsumerRunnable implements Runnable {
	
	private LinkedTransferQueue<TempObject> linkedQueue;


	public ConsumerRunnable(LinkedTransferQueue<TempObject> linkedQueue) {
		super();
		this.linkedQueue = linkedQueue;
	}




	@Override
	public void run() {
		
		Thread currentThread = Thread.currentThread();
		
		while(!currentThread.isInterrupted()) {
			
			try {
				//�ȴ���ֱ����LinkedTransferQueue�����еõ�һ��Ԫ��
				TempObject t = 	this.linkedQueue.take();
				System.out.println("�̣߳�" + currentThread.getId() + "��ȡ��targetObject.index = " + t.getIndex());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	
	public static void main(String[] args) {
		
		LinkedTransferQueue<TempObject> linkedQueue = new LinkedTransferQueue<TempObject>();
		// ����һ���������߳�
		Thread producerThread = new Thread(new ProducerRunnable(linkedQueue));
		// �����������������߳�
		Thread consumerRunnable1 = new Thread(new ConsumerRunnable(linkedQueue));
		Thread consumerRunnable2 = new Thread(new ConsumerRunnable(linkedQueue));

		// ��ʼ����
		producerThread.start();
		consumerRunnable1.start();
		consumerRunnable2.start();

		// ����ֻ��Ϊ��main���˳���û���κ���ʾ����
		Thread currentThread = Thread.currentThread();
		synchronized (currentThread) {
		    try {
				currentThread.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		
	}

}
