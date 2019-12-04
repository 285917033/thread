package com.mopon.thread;

/**
 * wait,object�ķ��������õ�ǰ�߳̽��������� �ȴ����ͷŵ�ǰ�����ȴ�object������notify()����notifyAll() �����Ļ���
 * ��sleep���̵߳ķ����� ��˯��ʱ�����ͷŵ�ǰ����
 * 
 * @author zgh
 *
 */
public class TestWait {

	// ������Դa
	private static volatile Object a = new Object();

	// ������Դb
	private static volatile Object b = new Object();

	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {

				synchronized (a) {

					System.out.println("t1 ��ȡ��a����Դ");

					synchronized (b) {

						System.out.println("t1 ��ȡ��b����Դ");

						// �߳�T1�����������ͷŻ�ȡ��a����
						try {
							a.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}

			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					synchronized (a) {

						System.out.println("t2 get a lock");

						System.out.println("t2 try get b lock");

						synchronized (b) {
							System.out.println("t2  get b lock");
							System.out.println("t2  release a lock");
							a.wait();
						}

					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main over");

	}

}
