package com.mopon.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 利用线程池创建线程
 * 
 * ThreadPoolExecutor 线程池对象，通过下面的构造器， 可以构造一个线程池对象， 
 * 1.corePoolSize 核心线程池大小
 * 2.maximumPoolSize 最大线程池大小
 * 3.keepAliveTime，unit 联合起来使用， 表示线程池中空闲(Idle)线程的最大存活时间
 * 4.workQueue 工作队列的阻塞队列
 * 5.threadFactory，用于指定创建工作者线程的线程工厂，
 * 6.handler，当线程池饱和，即工作队列满且当前线程池大小达到最大线程池大小的情况，
 *    客户端试图提交的任务会被拒绝(reject),为了提高线程池的可靠性，引入了RejectedExecutionHandler
 *    用于封装拒绝任务的处理策略，默认策略为ThreadPoolExecutor.AbortPolicy 直接抛异常，还有其他的策略
 *    DiscardPolicy 丢弃， 不抛异常， 等等...
 * 
 * 
 *                           (int corePoolSize,
                              int ,maximumPoolSize
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler)
 * @author zgh
 *
 *
 *在使用ThreadPoolExecutor线程池的时候，需要指定一个实现了BlockingQueue接口的任务等待队列。
 *在ThreadPoolExecutor线程池的API文档中，
 *一共推荐了三种等待队列，它们是：SynchronousQueue、LinkedBlockingQueue和ArrayBlockingQueue；
 *
 *1.同步队列，也是有界队列 SynchronousQueue 是这样 一种阻塞队列，其中每个 put 必须等待一个 take，反之亦然。同步队列没有任何内部容量，甚至连一个队列的容量都没有
 *SynchronousQueue<Object> queue = new SynchronousQueue<Object>();
// 不要使用add，因为这个队列内部没有任何容量，所以会抛出异常“IllegalStateException”
// queue.add(new Object());
// 操作线程会在这里被阻塞，直到有其他操作线程取走这个对象
queue.put(new Object());
 *
 *
 *2.有界队列ArrayBlockingQueue
 *一旦创建了这样的缓存区，就不能再增加其容量。试图向已满队列中放入元素会导致操作受阻塞；试图从空队列中提取元素将导致类似阻塞。
// 我们创建了一个ArrayBlockingQueue，并且设置队列空间为2
ArrayBlockingQueue<Object> arrayQueue = new ArrayBlockingQueue<Object>(2);
// 插入第一个对象
arrayQueue.put(new Object());
// 插入第二个对象
arrayQueue.put(new Object());
// 插入第三个对象时，这个操作线程就会被阻塞。
arrayQueue.put(new Object());
// 请不要使用add操作，和SynchronousQueue的add操作一样，它们都使用了AbstractQueue中的add实现

 *
 *
  3.无界队列 LinkedBlockingQueue
  
  LinkedBlockingQueue是我们在ThreadPoolExecutor线程池中常应用的等待队列。它可以指定容量也可以不指定容量。由于它具有“无限容量”的特性，所以我还是将它归入了无限队列的范畴
  LinkedBlockingQueue的实现是基于链表结构，而不是类似ArrayBlockingQueue那样的数组。但实际使用过程中，您不需要关心它的内部实现，如果您指定了LinkedBlockingQueue的容量大小，那么它反映出来的使用特性就和ArrayBlockingQueue类似了。
LinkedBlockingQueue<Object> linkedQueue = new LinkedBlockingQueue<Object>(2);
linkedQueue.put(new Object());
// 插入第二个对象
linkedQueue.put(new Object());
// 插入第三个对象时，这个操作线程就会被阻塞。
linkedQueue.put(new Object());

=======================================

// 或者如下使用：
LinkedBlockingQueue<Object> linkedQueue = new LinkedBlockingQueue<Object>();
linkedQueue.put(new Object());
// 插入第二个对象
linkedQueue.put(new Object());
// 插入第N个对象时，都不会阻塞
linkedQueue.put(new Object());


LinkedTransferQueue也是一个无限队列，它除了具有一般队列的操作特性外（先进先出），还具有一个阻塞特性：LinkedTransferQueue可以由一对生产者/消费者线程进行操作，当消费者将一个新的元素插入队列后，消费者线程将会一直等待，直到某一个消费者线程将这个元素取走，反之亦然。

  
 */
public class TestThreadPoolExecutor {

	
	public static void main(String[] args) {
		
		//当前服务器cpu数量
		final int N_CPU = Runtime.getRuntime().availableProcessors();
		
		final ThreadPoolExecutor  executor = new ThreadPoolExecutor(2, N_CPU*2, 4, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(N_CPU*8), new ThreadPoolExecutor.CallerRunsPolicy());
		
		Future<?> future = executor.submit(new Runnable() {
			
			@Override
			public void run() {
				
			}
		});
		executor.shutdown();
		
	}
}
