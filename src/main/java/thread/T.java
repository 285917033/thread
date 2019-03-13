package thread;

public class T {

	public static void main(String[] args) {
		
		//获取cpu可用个数
		System.out.println(Runtime.getRuntime().availableProcessors());
		
		System.out.println(Runtime.getRuntime().totalMemory());
		
		System.out.println(Thread.currentThread().getName());
		
		System.out.println(Thread.currentThread().getState());
		
		System.out.println(Thread.currentThread().getId());

	}

}
