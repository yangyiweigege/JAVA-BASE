package com.imooco.concurrency;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.Test;

public class AutomicExample {
	
	public volatile int count = 100;

	
	
	@Test
	public void testAutomicReference() {
		AtomicReference<Integer> count = new AtomicReference<Integer>(0);
		count.compareAndSet(0, 2);
		count.compareAndSet(0, 1);
		count.compareAndSet(1, 3);
		count.compareAndSet(2, 4);
		count.compareAndSet(3, 5);
		System.out.println("count:" + count.get());
	}
	
	@Test
	public  void testAtomicIntegerFieldUpdate() {
		AutomicExample automicExample = new AutomicExample();
		AtomicIntegerFieldUpdater<AutomicExample> updater= AtomicIntegerFieldUpdater.newUpdater(AutomicExample.class, "count");
		if(updater.compareAndSet(automicExample, 100, 120)) {
			System.out.println("update succc:" + automicExample.getCount());
		}
		if(updater.compareAndSet(automicExample, 100, 120)) {
			System.out.println("update succc:" + automicExample.getCount());
		} else {
			System.out.println("update failed:" + automicExample.getCount());
		}
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
