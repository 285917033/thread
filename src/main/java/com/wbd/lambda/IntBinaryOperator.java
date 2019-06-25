package com.wbd.lambda;
/**
 * 函数接口，该接口中只能定义一个抽象方法，有且只能有一个未实现的方法。
 * @author zgh
 *
 */
@FunctionalInterface
public interface IntBinaryOperator {
	
	int add(int a,int y);
}
