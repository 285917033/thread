package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * callable�ӿ��е�call�����ṹ��������Ҫ����ʵ���ݲ�����
 * 
 * @author jwh
 *
 */
public class FutureRealData implements Callable<String> {

	private String param;

	public FutureRealData(String param) {
		this.param = param;
	}

	public String call() throws Exception {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			sb.append(param);
		}

		Thread.sleep(1000);
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		//����FutureTask �൱�ڷ�������,��ʾ�������ķ���ֵ����ΪString,����ΪFutureRealData��call����
		FutureTask<String> futureTask  = new FutureTask<String>(new FutureRealData("real"));
		
		ExecutorService es = Executors.newFixedThreadPool(1);
		
		//�����߳̽��� FutureRealData ��call����
		es.submit(futureTask);
		
		System.out.println("�������");
		
		for(int i=0;i<100;i++) {
			System.out.println("i===="+i);
		}
		
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
