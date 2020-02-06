package com.mopon.thread;

/**
 * ˫�ؼ����������ȷ����ģʽ
 * 
 * @author zgh
 *
 */
public class DCLSingleton {

	/**
	 * volatile������
	 * 
	 * 1.���Ͽɼ��ԣ�һ���߳�ͨ��ִ���޸ı���ֵ�������߳̿��Զ�ȡ����Ӧ��ֵ
	 * 2.���������ԣ�����volatile�ܹ���ֹvolatile����д������ò���֮ǰ���κζ���д��������������
	 */
	private static volatile DCLSingleton instance;

	private DCLSingleton() {
	}

	public static DCLSingleton getInstance() {

		if (null == instance) {  //��һ�μ��
			synchronized (DCLSingleton.class) {

				if (null == instance) { // �ڶ��μ��

					instance = new DCLSingleton(); // ����
				}
			}
		}

		return instance;

	}

	public static void main(String[] args) {

	}

}
