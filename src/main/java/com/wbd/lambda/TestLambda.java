package com.wbd.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntConsumer;

/**
 * ����ʽ��̵ĺ����� lambda���ʽ lambda���ʽ����������������һ��û�к������ĺ����壬 ������Ϊ����ֱ�� ���ݸ���صĵ����ߡ�
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
