package com.mopon.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程一般有三中方式(线程的创建方式（排除线程池的方式外）， 1，继承Thread类，
 * 2.实现Runnable接口，3.采用异步任务的方式FutureTask,可以返回线程执行的结果，
 * FutureTask启动方式，1.new Thread(futureTask).start(); 2.将 futureTask实例交给线程池)
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

		// 获取线程执行的状态与结果
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
