package com.mopon.thread;

/**
 * 双重检查锁定的正确单例模式
 * 
 * @author zgh
 *
 */
public class DCLSingleton {

	/**
	 * volatile的作用
	 * 
	 * 1.保障可见性，一个线程通过执行修改变量值，其他线程可以读取到相应的值
	 * 2.保障有序性，由于volatile能够禁止volatile变量写操作与该操作之前的任何读，写操作进行重排序。
	 */
	private static volatile DCLSingleton instance;

	private DCLSingleton() {
	}

	public static DCLSingleton getInstance() {

		if (null == instance) {  //第一次检查
			synchronized (DCLSingleton.class) {

				if (null == instance) { // 第二次检查

					instance = new DCLSingleton(); // 操作
				}
			}
		}

		return instance;

	}

	public static void main(String[] args) {

	}

}
