package com.team.gaoguangjin.深入java虚拟机.垃圾回收;

/**
 * @ClassName:TestMinorGC.java
 * @Description: 新生代垃圾回收规则
 * @see：运行时环境：-XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 * @see:显示java堆的大小为20M 不可以扩展 其中 10M分给新生代 剩下的10M分给老年代。
 * @see(-Xmn10M)为年轻代最大的；-XX:SurviorRatio=8 决定新生代的 Eden与survior区的比例为8:1
 * @author gaoguangjin
 * @Date 2015-3-9 上午9:42:26
 */
public class TestMinorGC {
	public static final int _1MB = 1024 * 1024;
	
	public static void main(String[] args) {
		byte[] a, b, c, d;
		a = new byte[2 * _1MB];
		b = new byte[2 * _1MB];
		c = new byte[2 * _1MB];// 新生代为6m
		d = new byte[4 * _1MB];// 一次垃圾回收 minorGC
	}
	/**
	 * 日志：
	 * 
	 * [GC [DefNew: 6635K->245K(9216K), 0.0046942 secs] 6635K->6389K(19456K), 0.0047232 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 def new generation   total 9216K, used 4669K [0x327b0000, 0x331b0000, 0x331b0000)
  eden space 8192K,  54% used [0x327b0000, 0x32c01fa8, 0x32fb0000)
  from space 1024K,  24% used [0x330b0000, 0x330ed798, 0x331b0000)
  to   space 1024K,   0% used [0x32fb0000, 0x32fb0000, 0x330b0000)
 tenured generation   total 10240K, used 6144K [0x331b0000, 0x33bb0000, 0x33bb0000)
   the space 10240K,  60% used [0x331b0000, 0x337b0030, 0x337b0200, 0x33bb0000)
 compacting perm gen  total 12288K, used 383K [0x33bb0000, 0x347b0000, 0x37bb0000)
   the space 12288K,   3% used [0x33bb0000, 0x33c0ff08, 0x33c10000, 0x347b0000)
    ro space 10240K,  55% used [0x37bb0000, 0x38133320, 0x38133400, 0x385b0000)
    rw space 12288K,  55% used [0x385b0000, 0x38c56128, 0x38c56200, 0x391b0000)

	 */
	
	/**
	 * eden space 8192K from space 1024K to space 1024K 新生代=一个eden+两个survivor区 新生代可用总大小为 eden+1个survivor=9216 当执行到d的时候
	 * 新生代发生了垃圾回收 6635K->245K(9216K) 将6m直接回收掉成245。【 GC 前该区域已使用容量 -> GC 后该区域已使用容量 (该区域内存总容量) 】”
	 * 因为新生代的6M  无法放到 survivor（1m）里面,所以放到老年代里面去
	 * 6635K->6389K(19456K)  【GC 前Java堆已使用容量 -> GC后Java堆已使用容量 （Java堆总容量）】
	 */
	
}
