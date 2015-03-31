package com.team.gaoguangjin.thread.同步;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName:ThreadSafeParam.java
 * @Description: 线程安全的一些变量
 * @author gaoguangjin
 * @Date 2015-3-25 下午2:28:22
 */
public class ThreadSafeParam {
	public static void main(String[] args) {
		// ..........线程安全的变量
		AtomicInteger ai = new AtomicInteger();
		AtomicBoolean ab = new AtomicBoolean();
		AtomicLong al = new AtomicLong();
		
		int i = ai.getAndIncrement();// 线程安全的变量
		int a = 1;
		a++;// 多线程环境下不安全
		System.out.println("多线程环境下不安全" + a);
		System.out.println("线程安全的变量" + ai.getAndIncrement());
		System.out.println(ai.getAndIncrement());
		
	}
}
