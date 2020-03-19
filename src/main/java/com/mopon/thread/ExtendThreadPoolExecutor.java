package com.mopon.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 自定义线程池，需要继承ThreadPoolExecutor类，该类有3个没有具体实现的方法， 我们可以实现，
 * 这三个方法可以帮助我们再线程池不同阶段处理任务时，实现不同的业务逻辑
 * 1.beforeExecute,当线程池正要开始执行某个任务的时候(注意不是任务进入等待队列的时候,是将要开始正式在线程池中执行的时候)
 *     线程池会触发这个方法的调用
 * 
 * 2.afterExecute,当线程池完成了某一个任务的执行后，线程池会触发这个方法
 *   
 * 3.terminated,当线程池本身停止执行的时候，该方法就会被调用。
 * @author zgh
 * 
 * 
 * 5-2、execute方法和submit方法的区别
上面的示例代码中，我们使用的是execute方法来提交任务；而上文有的示例代码中，我们使用的是submit方法提交任务。ThreadPoolExecutor线程池中，这两种方法提交任务都是可以的，但是他们的工作原理是不一样的：

execute方法：所有实现了Runnable接口的任务都可以使用execute方法进行提交。而实现了Runnable接口的任务，并没有提供任何“标准”的方式为我们返回任务的执行结果（这是我们还没有讲到的知识点）。线程在线程池中运行结束了，就结束了。所以，使用execute方法提交的任务，程序员并不能在任务执行完成后，获得一个“标准”的执行结果。

submit方法：submit方法提交的任务是实现了Callable接口的任务（这是我们还没有讲到的知识点）。Callable接口的特性是，在其运行完成后，会返回一个“标准”的执行结果。

但有的读者可能会问，不是说submit方法也可以提交实现Runnable接口的任务吗？你之前也是这么使用的。是的，submit方法也可以提交实现Runnable接口的任务，但是处理方式和execute方法的处理方式完全不同：使用submit方法提交的实现了Runnable接口的任务，将会被封装到 线程池内部使用Executors工具中callable方法创建的RunnableAdapter对象中。源代码片段如下：


我会说明只能使用extendsPool.execute提交任务， 而不要使用extendsPool.submit提交任务。因为如果使用extendsPool.submit提交任务，那么您使用的hook method：beforeExecute和afterExecute，虽然可以拿到一个Runnable对象，但是这个Runnable对象却不是您创建的Runnable任务本身。而是一个FutureTask对象，里面封装了一个RunnableAdapter对象，在RunnableAdapter对象里面，才是您的Runnable任务本身：

Executors是一个用于创建各种线程池特性的工具类。通常情况下，您使用这个工具类创建的线程池就可以涵盖90%以上的业务场景了。

如果您观察一下Executors类的源代码，您就可以发现Executors工具类实际上就是帮助您完成了特定线程池的创建过程
 *
 */
public class ExtendThreadPoolExecutor extends ThreadPoolExecutor {

	
	public ExtendThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		
	}
/**
 * 如果您非要使用submit提交任务，那么在执行到：TestRunnable testRunnable = (TestRunnable)r; 的时候，
 * 就会抛出对象类型转换错误（不过您理解了原因后，当然可以改示例代码了）。
 */
	
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		TestRunnable tr = (TestRunnable) r;
		
		System.out.println("before..." + Thread.currentThread().getName() + " == " + tr.getIndex());
	}
	
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		
		TestRunnable tr = (TestRunnable) r;
		System.out.println("after***" + Thread.currentThread().getName() + " == " + tr.getIndex());

	}
	
	
	@Override
		protected void terminated() {
		System.out.println("terminated" );
		}
	
	public static void main(String[] args) {
	
		ExtendThreadPoolExecutor  pool = new ExtendThreadPoolExecutor(5,5,1000,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
		
		//工具类Executors，
//		Executors.newCachedThreadPool(); 创建同步队列的线程池，注意该线程池核心线程数为0，最大线程池数为2^32-1，空闲线程只存活60秒；等待队列并不能存储Runnable任务，反而使得线程池继续创建线程来执行任务。
//	CachedThreadPool所设置的等待队列的类型是SynchronousQueue，意味着任务将会立即被送入线程池执行（如果这时没有空闲的线程，则立即创建新的线程）。
		
//		Executors.newSingleThreadExecutor();
//		这段代码在Executors工具类中，用于创建只有一个固定线程的线程池。即corePoolSize 和maximumPoolSize的值都等于1。这根线程也不会被回收，然后将LinkedBlockingQueue这个无大小限制的先进先出队列中的每个任务，按照顺序进行处理。
//
//		显然，处理的即时性并不是这中特性的线程池关心的重点；它的重点显然是保证LinkedBlockingQueue中的等待任务能够按照既定顺序被处理。
		
//		
//		/**
//		 * 是否在超过等待时间后，就连线程池中小于corePoolSize的“核心线程”对象也进行回收。
//		 * 默认情况为false
//		 */
//		private volatile boolean allowCoreThreadTimeOut;
//
//		/**
//		 * “核心线程”的大小。小于或者等于这个数量的线程，即便超过keepAliveTime也不会被回收，
//		 * 除非allowCoreThreadTimeOut设置为true
//		 */
//		private volatile int corePoolSize;
		
//		Executors.newFixedThreadPool(5)
//		以上代码片段就是Executors工具类创建固定大小的ThreadPoolExecutor线程池的过程。实际上就是把corePoolSize 和maximumPoolSize 的值设置成一样，也就是线程池中只有“核心线程”——那些创建了就不会回收的线程。并且线程池的等待队列设置为LinkedBlockingQueue——一种没有数量限制的先进先出队列。
//	
		
		for(int index = 1 ; index < 11 ; index++) {
			
			pool.execute(new TestRunnable(index));
		}
		
		
		// 发出停止指令。注意停止指令本身不会等待，要使用awaitTermination进行等待。
        // 注意，按照我们上文讲过的线程池的工作原理，线程池在收到shutdown终止指令后
        // 就不会再接受提交过来的任务了，无论“核心线程”、等待队列处于什么样的状态！

		pool.shutdown(); //发出停止指令。注意停止指令本身不会等待
		try {
			//  当所有任务执行完成后，终止线程池的运行
			pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
