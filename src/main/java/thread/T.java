package thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class T {

	public static void main(String[] args) {
		
		//��ȡcpu���ø���
//		System.out.println(Runtime.getRuntime().availableProcessors());
//		
//		System.out.println(Runtime.getRuntime().totalMemory());
//		
//		System.out.println(Thread.currentThread().getName());
//		
//		System.out.println(Thread.currentThread().getState());
//		
//		System.out.println(Thread.currentThread().getId());
		CompletableFuture cf = CompletableFuture.supplyAsync(()->{
           
            System.out.println("�߳�:"+Thread.currentThread().getName()+"������supplyAsync");
            return "zhw";
        }).thenApplyAsync(x->{
            System.out.println("�߳�:"+Thread.currentThread().getName()+"������thenApply");
            return x+" hellow word";
        });


     try {
    	 Future<String> f = cf.whenComplete((v, e) -> {
             System.out.println(v);
             System.out.println(e);
         });
    	 
		System.out.println(cf.get());
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
