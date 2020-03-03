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
 *
 *��ʹ��ThreadPoolExecutor�̳߳ص�ʱ����Ҫָ��һ��ʵ����BlockingQueue�ӿڵ�����ȴ����С�
 *��ThreadPoolExecutor�̳߳ص�API�ĵ��У�
 *һ���Ƽ������ֵȴ����У������ǣ�SynchronousQueue��LinkedBlockingQueue��ArrayBlockingQueue��
 *
 *1.ͬ�����У�Ҳ���н���� SynchronousQueue ������ һ���������У�����ÿ�� put ����ȴ�һ�� take����֮��Ȼ��ͬ������û���κ��ڲ�������������һ�����е�������û��
 *SynchronousQueue<Object> queue = new SynchronousQueue<Object>();
// ��Ҫʹ��add����Ϊ��������ڲ�û���κ����������Ի��׳��쳣��IllegalStateException��
// queue.add(new Object());
// �����̻߳������ﱻ������ֱ�������������߳�ȡ���������
queue.put(new Object());
 *
 *
 *2.�н����ArrayBlockingQueue
 *һ�������������Ļ��������Ͳ�������������������ͼ�����������з���Ԫ�ػᵼ�²�������������ͼ�ӿն�������ȡԪ�ؽ���������������
// ���Ǵ�����һ��ArrayBlockingQueue���������ö��пռ�Ϊ2
ArrayBlockingQueue<Object> arrayQueue = new ArrayBlockingQueue<Object>(2);
// �����һ������
arrayQueue.put(new Object());
// ����ڶ�������
arrayQueue.put(new Object());
// �������������ʱ����������߳̾ͻᱻ������
arrayQueue.put(new Object());
// �벻Ҫʹ��add��������SynchronousQueue��add����һ�������Ƕ�ʹ����AbstractQueue�е�addʵ��

 *
 *
  3.�޽���� LinkedBlockingQueue
  
  LinkedBlockingQueue��������ThreadPoolExecutor�̳߳��г�Ӧ�õĵȴ����С�������ָ������Ҳ���Բ�ָ�����������������С����������������ԣ������һ��ǽ������������޶��еķ���
  LinkedBlockingQueue��ʵ���ǻ�������ṹ������������ArrayBlockingQueue���������顣��ʵ��ʹ�ù����У�������Ҫ���������ڲ�ʵ�֣������ָ����LinkedBlockingQueue��������С����ô����ӳ������ʹ�����Ծͺ�ArrayBlockingQueue�����ˡ�
LinkedBlockingQueue<Object> linkedQueue = new LinkedBlockingQueue<Object>(2);
linkedQueue.put(new Object());
// ����ڶ�������
linkedQueue.put(new Object());
// �������������ʱ����������߳̾ͻᱻ������
linkedQueue.put(new Object());

=======================================

// ��������ʹ�ã�
LinkedBlockingQueue<Object> linkedQueue = new LinkedBlockingQueue<Object>();
linkedQueue.put(new Object());
// ����ڶ�������
linkedQueue.put(new Object());
// �����N������ʱ������������
linkedQueue.put(new Object());


LinkedTransferQueueҲ��һ�����޶��У������˾���һ����еĲ��������⣨�Ƚ��ȳ�����������һ���������ԣ�LinkedTransferQueue������һ��������/�������߳̽��в������������߽�һ���µ�Ԫ�ز�����к��������߳̽���һֱ�ȴ���ֱ��ĳһ���������߳̽����Ԫ��ȡ�ߣ���֮��Ȼ��

  
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
