package com.mopon.thread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * AtomicLong ԭ�ӹ������ʹ��
 * 
 * @author zgh
 *
 */
public class Indicator {

	private static final Indicator INSTANCE = new Indicator();

	// ��������

	private final AtomicLong requestCount = new AtomicLong(0);

	// ��¼����ɹ���
	private final AtomicLong successCount = new AtomicLong(0);

	// ��¼����ʧ����
	private final AtomicLong failureCount = new AtomicLong(0);

	private Indicator() {
	}

	public static Indicator getInstance() {
		return INSTANCE;
	}

	/*** set ********/

	public void newRequestReceived() {
		// ʹ������������1�������������
		requestCount.incrementAndGet();
	}

	public void newRequestProcessed() {
		successCount.incrementAndGet();
	}

	public void requestProcessedFailured() {
		failureCount.incrementAndGet();
	}

	/*** get ********/

	public long getRequestCount() {
		return requestCount.get();
	}

	public long getSuccessCount() {

		return successCount.get();
	}

	public long getFailureCount() {

		return failureCount.get();
	}

}
