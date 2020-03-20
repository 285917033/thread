package com.mopon.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * �Զ����̳߳أ���Ҫ�̳�ThreadPoolExecutor�࣬������3��û�о���ʵ�ֵķ����� ���ǿ���ʵ�֣�
 * �������������԰����������̳߳ز�ͬ�׶δ�������ʱ��ʵ�ֲ�ͬ��ҵ���߼�
 * 1.beforeExecute,���̳߳���Ҫ��ʼִ��ĳ�������ʱ��(ע�ⲻ���������ȴ����е�ʱ��,�ǽ�Ҫ��ʼ��ʽ���̳߳���ִ�е�ʱ��)
 *     �̳߳ػᴥ����������ĵ���
 * 
 * 2.afterExecute,���̳߳������ĳһ�������ִ�к��̳߳ػᴥ���������
 *   
 * 3.terminated,���̳߳ر���ִֹͣ�е�ʱ�򣬸÷����ͻᱻ���á�
 * @author zgh
 * 
 * 
 * 5-2��execute������submit����������
�����ʾ�������У�����ʹ�õ���execute�������ύ���񣻶������е�ʾ�������У�����ʹ�õ���submit�����ύ����ThreadPoolExecutor�̳߳��У������ַ����ύ�����ǿ��Եģ��������ǵĹ���ԭ���ǲ�һ���ģ�

execute����������ʵ����Runnable�ӿڵ����񶼿���ʹ��execute���������ύ����ʵ����Runnable�ӿڵ����񣬲�û���ṩ�κΡ���׼���ķ�ʽΪ���Ƿ��������ִ�н�����������ǻ�û�н�����֪ʶ�㣩���߳����̳߳������н����ˣ��ͽ����ˡ����ԣ�ʹ��execute�����ύ�����񣬳���Ա������������ִ����ɺ󣬻��һ������׼����ִ�н����

submit������submit�����ύ��������ʵ����Callable�ӿڵ������������ǻ�û�н�����֪ʶ�㣩��Callable�ӿڵ������ǣ�����������ɺ󣬻᷵��һ������׼����ִ�н����

���еĶ��߿��ܻ��ʣ�����˵submit����Ҳ�����ύʵ��Runnable�ӿڵ���������֮ǰҲ����ôʹ�õġ��ǵģ�submit����Ҳ�����ύʵ��Runnable�ӿڵ����񣬵��Ǵ���ʽ��execute�����Ĵ���ʽ��ȫ��ͬ��ʹ��submit�����ύ��ʵ����Runnable�ӿڵ����񣬽��ᱻ��װ�� �̳߳��ڲ�ʹ��Executors������callable����������RunnableAdapter�����С�Դ����Ƭ�����£�


�һ�˵��ֻ��ʹ��extendsPool.execute�ύ���� ����Ҫʹ��extendsPool.submit�ύ������Ϊ���ʹ��extendsPool.submit�ύ������ô��ʹ�õ�hook method��beforeExecute��afterExecute����Ȼ�����õ�һ��Runnable���󣬵������Runnable����ȴ������������Runnable����������һ��FutureTask���������װ��һ��RunnableAdapter������RunnableAdapter�������棬��������Runnable������

Executors��һ�����ڴ��������̳߳����ԵĹ����ࡣͨ������£���ʹ����������ഴ�����̳߳ؾͿ��Ժ���90%���ϵ�ҵ�񳡾��ˡ�

������۲�һ��Executors���Դ���룬���Ϳ��Է���Executors������ʵ���Ͼ��ǰ�����������ض��̳߳صĴ�������
 *
 */
public class ExtendThreadPoolExecutor extends ThreadPoolExecutor {

	
	public ExtendThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		
	}
/**
 * �������Ҫʹ��submit�ύ������ô��ִ�е���TestRunnable testRunnable = (TestRunnable)r; ��ʱ��
 * �ͻ��׳���������ת�����󣨲����������ԭ��󣬵�Ȼ���Ը�ʾ�������ˣ���
 */
	
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		TestRunnable tr = (TestRunnable) r;
		
		System.out.println("before..." + Thread.currentThread().getName() + " == " + tr.getIndex());
	}
	
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		
		TestRunnable tr = (TestRunnable) r;
		System.out.println("after***" + Thread.currentThread().getName() + " == " + tr.getIndex());

	}
	
	
	@Override
		protected void terminated() {
		System.out.println("terminated" );
		}
	
	public static void main(String[] args) {
	
		ExtendThreadPoolExecutor  pool = new ExtendThreadPoolExecutor(5,5,1000,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
		
		//������Executors��
//		Executors.newCachedThreadPool(); ����ͬ�����е��̳߳أ�ע����̳߳غ����߳���Ϊ0������̳߳���Ϊ2^32-1�������߳�ֻ���60�룻�ȴ����в����ܴ洢Runnable���񣬷���ʹ���̳߳ؼ��������߳���ִ������
//	CachedThreadPool�����õĵȴ����е�������SynchronousQueue����ζ�����񽫻������������̳߳�ִ�У������ʱû�п��е��̣߳������������µ��̣߳���
		
//		Executors.newSingleThreadExecutor();
//		��δ�����Executors�������У����ڴ���ֻ��һ���̶��̵߳��̳߳ء���corePoolSize ��maximumPoolSize��ֵ������1������߳�Ҳ���ᱻ���գ�Ȼ��LinkedBlockingQueue����޴�С���Ƶ��Ƚ��ȳ������е�ÿ�����񣬰���˳����д���
//
//		��Ȼ������ļ�ʱ�Բ������������Ե��̳߳ع��ĵ��ص㣻�����ص���Ȼ�Ǳ�֤LinkedBlockingQueue�еĵȴ������ܹ����ռȶ�˳�򱻴���
		
//		
//		/**
//		 * �Ƿ��ڳ����ȴ�ʱ��󣬾����̳߳���С��corePoolSize�ġ������̡߳�����Ҳ���л��ա�
//		 * Ĭ�����Ϊfalse
//		 */
//		private volatile boolean allowCoreThreadTimeOut;
//
//		/**
//		 * �������̡߳��Ĵ�С��С�ڻ��ߵ�������������̣߳����㳬��keepAliveTimeҲ���ᱻ���գ�
//		 * ����allowCoreThreadTimeOut����Ϊtrue
//		 */
//		private volatile int corePoolSize;
		
//		Executors.newFixedThreadPool(5)
//		���ϴ���Ƭ�ξ���Executors�����ഴ���̶���С��ThreadPoolExecutor�̳߳صĹ��̡�ʵ���Ͼ��ǰ�corePoolSize ��maximumPoolSize ��ֵ���ó�һ����Ҳ�����̳߳���ֻ�С������̡߳�������Щ�����˾Ͳ�����յ��̡߳������̳߳صĵȴ���������ΪLinkedBlockingQueue����һ��û���������Ƶ��Ƚ��ȳ����С�
//	
		
		for(int index = 1 ; index < 11 ; index++) {
			
			pool.execute(new TestRunnable(index));
		}
		
		
		// ����ָֹͣ�ע��ָֹͣ�����ȴ���Ҫʹ��awaitTermination���еȴ���
        // ע�⣬�����������Ľ������̳߳صĹ���ԭ���̳߳����յ�shutdown��ָֹ���
        // �Ͳ����ٽ����ύ�����������ˣ����ۡ������̡߳����ȴ����д���ʲô����״̬��

		pool.shutdown(); //����ָֹͣ�ע��ָֹͣ�����ȴ�
		try {
			//  ����������ִ����ɺ���ֹ�̳߳ص�����
			pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
