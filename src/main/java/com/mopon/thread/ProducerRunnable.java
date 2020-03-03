package com.mopon.thread;

import java.util.concurrent.LinkedTransferQueue;

/**
 * 模拟生产者 与消费者
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
			
			//向队列中插入一个新的元素，然后生产者线程等待，直到有一个消费者将
			//这个元素从队列中取走
			try {
				this.linkedQueue.transfer(new TempObject(index));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
