package com.mopon.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * �����̳߳ش����߳�
 * 
 * ThreadPoolExecutor �̳߳ض���ͨ������Ĺ������� ���Թ���һ���̳߳ض��� 
 * 1.corePoolSize �����̳߳ش�С
 * 2.maximumPoolSize ����̳߳ش�С
 * 3.keepAliveTime��unit ��������ʹ�ã� ��ʾ�̳߳��п���(Idle)�̵߳������ʱ��
 * 4.workQueue �������е���������
 * 5.threadFactory������ָ�������������̵߳��̹߳�����
 * 6.handler�����̳߳ر��ͣ��������������ҵ�ǰ�̳߳ش�С�ﵽ����̳߳ش�С�������
 *    �ͻ�����ͼ�ύ������ᱻ�ܾ�(reject),Ϊ������̳߳صĿɿ��ԣ�������RejectedExecutionHandler
 *    ���ڷ�װ�ܾ�����Ĵ�����ԣ�Ĭ�ϲ���ΪThreadPoolExecutor.AbortPolicy ֱ�����쳣�����������Ĳ���
 *    DiscardPolicy ������ �����쳣�� �ȵ�...
 * 
 * 
 *                           (int corePoolSize,
                              int ,maximumPoolSize
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler)
 * @author zgh
 *
 */
public class TestThreadPoolExecutor {

	
	public static void main(String[] args) {
		
		//��ǰ������cpu����
		final int N_CPU = Runtime.getRuntime().availableProcessors();
		
		final ThreadPoolExecutor  executor = new ThreadPoolExecutor(2, N_CPU*2, 4, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(N_CPU*8), new ThreadPoolExecutor.CallerRunsPolicy());
		
		Future<?> future = executor.submit(new Runnable() {
			
			@Override
			public void run() {
				
			}
		});
		executor.shutdown();
		
	}
}
