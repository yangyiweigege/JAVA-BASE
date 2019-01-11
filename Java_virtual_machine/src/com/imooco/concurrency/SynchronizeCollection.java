package com.imooco.concurrency;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public class SynchronizeCollection {
	
	
	
	
	public static void main(String[] args) {
	
	}
	
	private static void test3(List<Integer> list) {
		for (int i = 0; i <list.size(); i++) {
			
		}
	}
	
	private static void test2(List<Integer> list) {
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			Integer integer = iterator.next();
			if (integer.equals(3)) {
				list.remove(integer);
			}
		}
	}
	
	
	private static void test1(List<Integer> list) {
		list.forEach( x -> {
			if (x == 3) {
				list.remove(x);
			}
		});
	}

}
