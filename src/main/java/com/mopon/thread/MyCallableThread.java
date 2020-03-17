package com.mopon.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Entity这个class，是为了记录线程执行的返回结果由我们自行定义的一个Class。实际上，对于MyCallableThread来说，只要继承了Entity的所有子类，都是可以作为它的泛化值的。

目前Callable定义的线程任务，只能放入线程池中，由线程池中的任务进行执行。没有类似于Runnable接口那样，new Thread(new MyDefindRunnable())并且start()的线程运行方式。

如果您不想使用线程池管理任务的执行，又不能直接将Callable接口的任务放入Thread，那么您只能借助一个工具类：FutureTask。使用方式如下：
FutureTask<Entity> futureTask = new FutureTask<Entity>(callableThread);
new Thread(futureTask).start();
Future用于描述当前任务线程的执行状态。您可以使用isDone、isCancelled等方法，来获取当前任务线程的执行状态。
Future接口中的get方法，将会是当前线程进入阻塞状态。直到目标线程执行完毕，并且得到目标线程的返回结果

 * 
 * @author zgh
 *
 * @param <V>
 */
public class MyCallableThread<V extends Entity> implements Callable<V>{

	private V resultEntity;
	
	
	
	public MyCallableThread(V resultEntity) {
		
		this.resultEntity = resultEntity;
	}



	@Override
	public V call() throws Exception {
		synchronized (this) {
			this.wait(5000);
		}
		this.resultEntity.setStatus(1);
		return this.resultEntity;
	}

	public static void main(String[] args) {
		MyCallableThread<Entity> t = new MyCallableThread<Entity>(new Entity());
		
		ExecutorService es = Executors.newFixedThreadPool(1);
		Future<Entity> f = es.submit(t);
		try {
			Entity entity  = f.get();
			System.out.println(entity.getStatus());
			
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			es.shutdown();
			try {
				es.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
