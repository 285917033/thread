package com.mopon.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * �����߳�һ�������з�ʽ(�̵߳Ĵ�����ʽ���ų��̳߳صķ�ʽ�⣩�� 1���̳�Thread�࣬
 * 2.ʵ��Runnable�ӿڣ�3.�����첽����ķ�ʽFutureTask,���Է����߳�ִ�еĽ����
 * FutureTask������ʽ��1.new Thread(futureTask).start(); 2.�� futureTaskʵ�������̳߳�)
 * 
 * @author zgh
 *
 */
public class CreateThreadByFutureTask implements Callable<String> {
	
	

	@Override
	public String call() throws Exception {
		System.out.println("abc");
		for(int i=0;i<100;i++) {
			System.out.println(i);
		}
		this.p("tes");
		return "hello FutureTask implements Thread";
	}

	public String p(String a) {
		
		a+="t";
		System.out.println(a);
		this.p2("abcd");
		return a;
	}
	
	public String p2(String a) {
		
		a+="efg";
		System.out.println(a);
		return a;
	}
	
	public static void main(String[] args) {

		FutureTask<String> futureTask = new FutureTask<String>(new CreateThreadByFutureTask());

		new Thread(futureTask).start();

		// ��ȡ�߳�ִ�е�״̬����
		Long a = 123L;
      
		System.out.println("---"+(a==123));
		
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
