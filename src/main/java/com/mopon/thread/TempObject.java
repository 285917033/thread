package com.mopon.thread;

public class TempObject implements Comparable<TempObject> {

	private int index;
	
	
	public TempObject(int index) {
		this.index = index;
	}
  
   
	public int getIndex() {
		return index;
	}


	@Override
	public int compareTo(TempObject o) {
		System.out.println("abc");
		return o.getIndex() - this.index;
	}
	
	public static void main(String[] args) {
		
		System.out.println(new TempObject(2).compareTo(new TempObject(3)));
		
	}

}
