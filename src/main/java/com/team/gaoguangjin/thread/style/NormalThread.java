package com.team.gaoguangjin.thread.style;

/**
 * @ClassName:NormalThread.java
 * @Description: 为什么使用线程，如下例子
 * @see1．耗时的操作使用线程，提高应用程序响应
 * @see2．并行操作时使用线程，如C/S架构的服务器端并发线程响应用户的请求。
 * @see3．多CPU系统中，使用线程提高CPU利用率
 * @see4．改善程序结构。一个既长又复杂的进程可以考虑分为多个线程，成为几个独立或半独
 * @author gaoguangjin
 * @Date 2015-3-19 下午1:46:35
 */
public class NormalThread extends Thread {
	public static void main(String[] args) {
		Thread th = new Thread();
		
	}
	
	/**
	 * 为什么要使用多线程
	 */
	public void run() {
		
	}
	
	/**
	 * 适用范围:那种需要长时间CPU运算的场合，例如耗时较长的图形处理和算法执行。但是往往由于使用线程编程的简单和符合习惯，所以很多朋友往往会使用线程来执行耗时较长的I/O操作。这样在只有少数几个并发操作的时候还无伤大雅，
	 * 如果需要处理大量的并发操作时就不合适了。
	 */
	
}
