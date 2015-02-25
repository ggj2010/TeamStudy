package com.team.gaoguangjin.深入java虚拟机.垃圾回收;

/**
 * @ClassName:ReferenceCountGC.java
 * @Description: -XX:+PrintGCDetails//收集器日志参数，告诉虚拟机发生垃圾收集行为时候打印的内存回收日志
 * @author gaoguangjin
 * @Date 2015-2-13 下午1:44:06
 */
public class ReferenceCountGC {
	public Object instance = null;
	private static final int _1MB = 1024 * 1024;
	// 这个成员属性的唯一意思就是站点内存，以便在GC日志中看清楚是否被回收过
	private byte[] bigSize = new byte[2 * _1MB];
	
	public static void main(String[] args) {
		ReferenceCountGC object1 = new ReferenceCountGC();
		ReferenceCountGC object2 = new ReferenceCountGC();
		
		object1.instance = object2;
		object2.instance = object1;
		object1 = null;
		object1 = null;
		
		// System.gc();
		Runtime.getRuntime().gc();
	}
	
}
