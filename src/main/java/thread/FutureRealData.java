package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * callable接口中的call方法会构造我们需要的真实数据并返回
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
		
		//构造FutureTask 相当于发送请求,表示这个任务的返回值类型为String,内容为FutureRealData的call方法
		FutureTask<String> futureTask  = new FutureTask<String>(new FutureRealData("real"));
		
		ExecutorService es = Executors.newFixedThreadPool(1);
		
		//开启线程进行 FutureRealData 的call调用
		es.submit(futureTask);
		
		System.out.println("请求完毕");
		
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
