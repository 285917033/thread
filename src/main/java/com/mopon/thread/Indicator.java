package com.mopon.thread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * AtomicLong 原子工具类的使用
 * 
 * @author zgh
 *
 */
public class Indicator {

	private static final Indicator INSTANCE = new Indicator();

	// 请求总数

	private final AtomicLong requestCount = new AtomicLong(0);

	// 记录处理成功数
	private final AtomicLong successCount = new AtomicLong(0);

	// 记录处理失败数
	private final AtomicLong failureCount = new AtomicLong(0);

	private Indicator() {
	}

	public static Indicator getInstance() {
		return INSTANCE;
	}

	/*** set ********/

	public void newRequestReceived() {
		// 使总请求数增加1，所以无需加锁
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
