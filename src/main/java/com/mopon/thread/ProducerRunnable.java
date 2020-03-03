package com.mopon.thread;

import java.util.concurrent.LinkedTransferQueue;

/**
 * ģ�������� ��������
 * @author zgh
 *
 */
public class ProducerRunnable implements Runnable {

	private LinkedTransferQueue<TempObject> linkedQueue;
	
	
	public ProducerRunnable(LinkedTransferQueue<TempObject> linkedQueue) {
		this.linkedQueue = linkedQueue;
	}



	@Override
	public void run() {
		
		for(int index = 1 ; ; index++) {
			
			//������в���һ���µ�Ԫ�أ�Ȼ���������̵߳ȴ���ֱ����һ�������߽�
			//���Ԫ�شӶ�����ȡ��
			try {
				this.linkedQueue.transfer(new TempObject(index));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
