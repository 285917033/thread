package com.wbd.lambda;

import java.util.stream.IntStream;

public class TestIntStream {

	public static void main(String[] args) {
		
		IntStream.of(new int[] {123}).forEach(System.out::println);
        IntStream.range(1, 4).forEach(x->System.out.print(x));
	}

}
