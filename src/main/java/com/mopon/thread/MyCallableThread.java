package com.mopon.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Entity���class����Ϊ�˼�¼�߳�ִ�еķ��ؽ�����������ж����һ��Class��ʵ���ϣ�����MyCallableThread��˵��ֻҪ�̳���Entity���������࣬���ǿ�����Ϊ���ķ���ֵ�ġ�

ĿǰCallable������߳�����ֻ�ܷ����̳߳��У����̳߳��е��������ִ�С�û��������Runnable�ӿ�������new Thread(new MyDefindRunnable())����start()���߳����з�ʽ��

���������ʹ���̳߳ع��������ִ�У��ֲ���ֱ�ӽ�Callable�ӿڵ��������Thread����ô��ֻ�ܽ���һ�������ࣺFutureTask��ʹ�÷�ʽ���£�
FutureTask<Entity> futureTask = new FutureTask<Entity>(callableThread);
new Thread(futureTask).start();
Future����������ǰ�����̵߳�ִ��״̬��������ʹ��isDone��isCancelled�ȷ���������ȡ��ǰ�����̵߳�ִ��״̬��
Future�ӿ��е�get�����������ǵ�ǰ�߳̽�������״̬��ֱ��Ŀ���߳�ִ����ϣ����ҵõ�Ŀ���̵߳ķ��ؽ��

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
