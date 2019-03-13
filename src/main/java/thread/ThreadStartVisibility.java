package thread;

/**
 * 多线程产生的问题：1.原子性，2.可见性,3,有序性
 * 1.原子性 表示一个线程更新共享变量要么成功，要么失败，没有第三种情况
 * 2.可见性 表示一个线程对共享变量更新之后，其他线程访问该变量可能无法立刻读取到这个更新
 * 的结果，甚至永远也无法读取到这个更新的结果，这是线程安全问题的另外一个表现形式，可见性
 * 
 * 父线程在启动子线程之前对共享变量的更新对应子线程来说是可见的。
 * 父线程在启动子线程之后对共享变量的更新对应子线程来的可见性是没有保证的。
 * @author jwh
 *
 */
public class ThreadStartVisibility {
	//线程间的共享变量
	static int data=0;
	
	//main 主线程
	public static void main(String[] args) {
		//创建子线程
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					//当前子线程休眠50毫秒
					Thread.sleep(50);
					System.out.println("子线程的data="+data);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		
		//在子线程启动前更新data
		data=100;
		
		//启动子线程
		thread.start();

		//当前主线程休眠50毫秒
		try {
			Thread.sleep(10);
			data=200;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
