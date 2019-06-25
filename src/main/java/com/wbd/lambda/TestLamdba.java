package com.wbd.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestLamdba {

	public static void main(String[] args) {
		
		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				System.out.println("hello world");
//				
//			}
//		}).start();
		
		
		//new Thread(()->System.out.println("java 8 lamdba ")).start();

		
		List<String>  t = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		
		
		//t.forEach(n->System.out.println(n));
		
		//
		IntBinaryOperator i = (int x,int y) ->{
			return x+y+2;
		};
		
		IntBinaryOperator i2 = (int x,int y) ->x+y+2;
		
		//System.out.println(i.add(2, 3));
		
		//System.out.println(i2.add(5, 3));
		
		//�������ж��д��룬������ʡ��{}��return�ؼ���
		
		Comparator<Integer> com = (x,y)->{
			
			System.out.println(" ����ʽ�ӿ�");
			
			return Integer.compare(x, y);
		};
		
		//System.out.println(com.compare(5, 5));
		
		//������ֻ��һ�д��룬����ʡ��{}��return�ؼ���
		Comparator<Integer> com1 = (x,y)->Integer.compare(x, y);
		//System.out.println(com.compare(5, 6));
		
		
		//����
		
		List<String> l = Arrays.asList(new String[] {"c","a","f","e"});
		
		Collections.sort(l, new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
				
				return o1.compareTo(o2);
			}
			
		});
		
		//l.forEach(x->System.out.println(x));
		
		//lambdaд��
		
		Collections.sort(l, (x,y)->x.compareTo(y));
		
		//l.forEach(x->System.out.println(x));
		
		List<String> l2 = l.stream().map(name->{
			 System.out.println(new TestLamdba().getClass().getName());
			return name.toUpperCase();
			}).collect(Collectors.toList());
		//l2.forEach(x->System.out.println(x));
		l2.forEach(System.out::print);
	
	
	}
	
	

}
