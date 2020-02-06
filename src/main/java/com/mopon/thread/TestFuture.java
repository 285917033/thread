package com.mopon.thread;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 从指定的车牌照片中识别出对应的车牌号， 这个识别的过程可能比较耗时，因此
 * 我们将这个识别任务封装为一个callable实例，提交给专门的线程池执行， 并在需要该
 * 任务的处理结果数据(车牌号)是才调用Futrue.get()方法
 * @author zgh
 *
 */
public class TestFuture {
	
	//cpu数量
	final static int N_CPU = Runtime.getRuntime().availableProcessors();
	final ThreadPoolExecutor exector = new ThreadPoolExecutor(0,N_CPU * 2, 4,TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(N_CPU * 100),new ThreadPoolExecutor.CallerRunsPolicy());
	

	public static void main(String[] args) {

		TestFuture tf = new TestFuture();
		Future<String> future = tf.recognizeImage("c:/a/images/001.png");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println(future.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public Future<String> recognizeImage(final String imageFile){
		return exector.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return doRecognizeImage(new File(imageFile));
			}
			
		});
	}
	
	protected String doRecognizeImage(File imageFile) {
		
		String result = null;
		String[] t = {"鄂A001","鄂A002","鄂A003"};
		result = t[(int) (Math.random() * t.length)];
		
		return result;
	}
}
