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
