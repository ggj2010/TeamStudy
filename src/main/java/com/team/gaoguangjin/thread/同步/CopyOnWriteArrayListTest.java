package com.team.gaoguangjin.thread.同步;

import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.mina.util.CopyOnWriteMap;

/**
 * @ClassName:CopyOnWriteArrayListTest.java
 * @Description: copyOnWriteArrayList
 * @author gaoguangjin
 * @Date 2015-4-7 上午11:55:52
 */
public class CopyOnWriteArrayListTest {
	public static void main(String[] args) {
		method1();
	}
	
	private static void method1() {
		
		CopyOnWriteArrayList coal = new CopyOnWriteArrayList();
		
		CopyOnWriteMap<String, String> cowm = new CopyOnWriteMap<String, String>();
		
	}
}
