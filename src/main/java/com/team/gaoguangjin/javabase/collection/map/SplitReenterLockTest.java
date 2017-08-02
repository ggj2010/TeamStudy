package com.team.gaoguangjin.javabase.collection.map;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.RandomStringUtils;

/**
 * @author:gaoguangjin
 * @date 2017/6/20 9:13
 */
public class SplitReenterLockTest {
	
	public static void main(String[] args) {
		method(50, 1000);
	}
	
	public static void method(int lockNum, int testNum) {
		SplitReentrantLock splitLock = new SplitReentrantLock(lockNum);
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		for(int i = 0; i < lockNum; i++) {
			map.put(i, 0);
		}
		for(int i = 0; i < testNum; i++) {
			Integer key = splitLock.index(RandomStringUtils.random(128));
			map.put(key, map.get(key) + 1);
		}
		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
}
