package com.wbd.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {

	public static void main(String[] args) {
      
		Stream<Integer> i  = Stream.of(1,2,3,4,5);
		
		long c = i.filter(n->{ 
			System.out.println("abc");
			return n>3;
			
		}).count();
		
		//System.out.println(c);
		
		//Stream.generate(() -> Math.random()*10).forEach(System.out::println);
		
		//Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);
		
		List<String> ls = Arrays.asList(new String[] {"a","b","c","a"});
		
		//ls.stream().distinct().forEach(System.out::println);
		
		ls.stream().filter(x->!x.equals("a")).collect(Collectors.toList()).forEach(System.out::println);
		//System.out.println(ls.stream().filter(x->x.equals("a")).count());
		
		Stream<List<Integer>> sl = Stream.of(Arrays.asList(1),Arrays.asList(4,5),Arrays.asList(7,8));
	   
		Stream<Integer> si = sl.flatMap(cl->cl.stream());
		
		//si.forEach(n->System.out.println(n));
		
		List<Integer> m = Arrays.asList(new Integer[] {1,2,3,4});
		
		m.stream().filter(x-> x>2).collect(Collectors.toList()).forEach(System.out::println);
		List<Integer> m2 =m.stream().map(x->x*2).collect(Collectors.toList());
		
		int count = m.stream().map(x->x*2).reduce((sum,y)->sum+y).get();
		
//		System.out.println(count);
//		
//		System.out.println(m.stream().allMatch(x-> x >2));
//		
//		System.out.println(m.stream().anyMatch(x-> x<3));
//		
//		System.out.println(m.stream().findFirst().get());
//		
//		System.out.println(m.stream().noneMatch(x->x>5));
//		
//		System.out.println(m.stream().max((x,y)->x.compareTo(y)).get());
//		
//		System.out.println(m.stream().min((x,y)->x.compareTo(y)).get());
	}
}
