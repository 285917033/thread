package thread;

public class T {

	public static void main(String[] args) {
		
		//��ȡcpu���ø���
		System.out.println(Runtime.getRuntime().availableProcessors());
		
		System.out.println(Runtime.getRuntime().totalMemory());
		
		System.out.println(Thread.currentThread().getName());
		
		System.out.println(Thread.currentThread().getState());
		
		System.out.println(Thread.currentThread().getId());

	}

}
