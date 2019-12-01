package com.mopon.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * �����߳�һ�������з�ʽ(�̵߳Ĵ�����ʽ���ų��̳߳صķ�ʽ�⣩�� 1���̳�Thread�࣬
 * 2.ʵ��Runnable�ӿڣ�3.�����첽����ķ�ʽFutureTask,���Է����߳�ִ�еĽ��)
 * 
 * @author zgh
 *
 */
public class CreateThreadByFutureTask implements Callable<String> {
 
	
	@Override
	public String call() throws Exception {
		return "hello FutureTask implements Thread";
	}

	public static void main(String[] args) {

		FutureTask futureTask = new FutureTask(new CreateThreadByFutureTask());

		new Thread(futureTask).start();

		// ��ȡ�߳�ִ�е�״̬����

		System.out.println(futureTask.isDone());

		System.out.println(futureTask.isCancelled());
		try {
			System.out.println(futureTask.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
