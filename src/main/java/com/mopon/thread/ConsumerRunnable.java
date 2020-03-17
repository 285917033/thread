package com.mopon.thread;

import java.util.concurrent.LinkedTransferQueue;

/**
 *  * 模拟生产者 与消费者
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
				//等待，直到从LinkedTransferQueue队列中得到一个元素
				TempObject t = 	this.linkedQueue.take();
				System.out.println("线程（" + currentThread.getId() + "）取得targetObject.index = " + t.getIndex());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	
	public static void main(String[] args) {
		
		LinkedTransferQueue<TempObject> linkedQueue = new LinkedTransferQueue<TempObject>();
		// 这是一个生产者线程
		Thread producerThread = new Thread(new ProducerRunnable(linkedQueue));
		// 这里有两个消费者线程
		Thread consumerRunnable1 = new Thread(new ConsumerRunnable(linkedQueue));
		Thread consumerRunnable2 = new Thread(new ConsumerRunnable(linkedQueue));

		// 开始运行
		producerThread.start();
		consumerRunnable1.start();
		consumerRunnable2.start();

		// 这里只是为了main不退出，没有任何演示含义
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
