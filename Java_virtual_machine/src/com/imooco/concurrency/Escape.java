package com.imooco.concurrency;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Escape {
	
	private int thisCanBeEscape = 0;
	
	public Escape() {
		 new InnerClass();
	}
	
	private class InnerClass {
		public InnerClass() {
			System.out.println(Escape.this.thisCanBeEscape);
		}
	}
	
	
	public static void main(String[] args) {
		AtomicInteger integer = new AtomicInteger(0);
		System.out.println(integer.incrementAndGet());
		
	}
	

}
