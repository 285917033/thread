package com.wbd.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntConsumer;

/**
 * 函数式编程的核心是 lambda表达式 lambda表达式即匿名函数，他是一段没有函数名的函数体， 可以作为参数直接 传递给相关的调用者。
 * 
 * @author jwh
 *
 */
public class TestLambda {

	public static void main(String[] args) {

		List<Integer> list = Arrays.asList(1, 4, 5, 2, 6, 1, 9);

		list.forEach((Integer value) -> System.out.println(value));

	    list.forEach((x)->System.out.println(x));
	    
	    list.forEach(System.out::println);
		
	} 

}
