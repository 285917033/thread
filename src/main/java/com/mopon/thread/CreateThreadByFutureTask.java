package com.mopon.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程一般有三中方式(线程的创建方式（排除线程池的方式外）， 1，继承Thread类，
 * 2.实现Runnable接口，3.采用异步任务的方式FutureTask,可以返回线程执行的结果)
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

		// 获取线程执行的状态与结果

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
