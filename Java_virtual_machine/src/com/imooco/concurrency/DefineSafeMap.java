package com.imooco.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 自定义线程安全map
 * @author yangyiwei
 * @date 2018年12月1日
 * @time 下午1:35:22
 */
public class DefineSafeMap<K, V> {
	
	private Map<K, V> map = new HashMap<>();
	
	/**
	 * 读写锁
	 */
	private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	private static Lock readLock = readWriteLock.readLock();

	private static Lock writeLock = readWriteLock.writeLock();
	
	public void put(K key, V value) {
		writeLock.lock();
		try {
			map.put(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writeLock.unlock();
		}
		
	}
	
	public void get(K key) {
		readLock.lock();
		try {
			map.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			readLock.unlock();
		}
	
	}
	
	public static void main(String[] args) {
		
	}

}
