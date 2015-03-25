package com.team.gaoguangjin.深入java虚拟机.内存泄露;

import java.util.List;

/**
 * @ClassName:MemoryLeakge.java
 * @Description:内存泄露
 * @author gaoguangjin
 * @Date 2015-3-20 上午10:33:46
 */
public class MemoryLeakge {
	public static void main(String[] args) {
		
		// Vector v = new Vector(10);
		// for (int i = 1; i < 100000; i++) {
		// Object o = new Object();
		// v.add(o);
		// o = null;
		// }
		// v = null;
		// System.gc();
		
		// Map<Integer, Object> v = new HashMap<Integer, Object>();
		// for (int i = 1; i < 1000; i++) {
		// Object o = new Object();
		// v.put(i, o);
		// }
		// System.gc();
		// v.clear();
		
		// test();
		
		test2();
	}
	
	/**
	 * @Description:
	 * @see [GC [DefNew: 2588K->320K(3072K), 0.0017452 secs] 2588K->1036K(9920K), 0.0017724 secs] [Times: user=0.00
	 * sys=0.00, real=0.00 secs]
	 * @see[GC [DefNew: 2456K->0K(3072K), 0.0011392 secs] 3172K->2316K(9920K), 0.0011655 secs] [Times: user=0.00
	 * sys=0.00, real=0.00 secs]
	 * @see[GC [DefNew: 2070K->0K(3072K), 0.0013083 secs] 4386K->4364K(9920K), 0.0013431 secs] [Times: user=0.00
	 * sys=0.00, real=0.00 secs]
	 * @see[GC [DefNew: 2048K->0K(3072K), 0.0011856 secs] 6412K->6412K(9920K), 0.0012106 secs] [Times: user=0.00
	 * sys=0.02, real=0.00 secs]
	 * @see[GC [DefNew: 2048K->2048K(3072K), 0.0000241 secs][Tenured: 6412K->2316K(6848K), 0.0067945 secs]
	 * 8460K->2316K(9920K), [Perm : 384K->384K(12288K)], 0.0068691 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
	 * @see[GC [DefNew: 2048K->2048K(3072K), 0.0000147 secs][Tenured: 6412K->6412K(6848K), 0.0054148 secs]
	 * 8460K->6412K(9920K), [Perm : 384K->384K(12288K)], 0.0054862 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
	 * @see[Full GC [Tenured: 6412K->6398K(6848K), 0.0064809 secs] 6412K->6398K(9920K), [Perm : 384K->379K(12288K)],
	 * 0.0065179 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
	 * @return:void
	 */
	private static void test2() {
		String a = new String("lol ");
		for (int i = 0; i < 100000; ++i) {
			a += a;
		}
		
	}
	
	private static void test() {
		List list = new MyList();
		MyObject m = new MyObject(101);
		for (int i = 0; i < 10000; i++) {
			list.add(new MyObject(i));
			
		}
		list.add(m);
		// m = null; // 这一行注释后与不注释两种情况运行 你都可以试一下就知道
		list = null;
		System.gc();
		
	}
}
