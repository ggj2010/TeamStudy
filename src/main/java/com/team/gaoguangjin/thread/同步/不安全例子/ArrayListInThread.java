package com.team.gaoguangjin.thread.同步.不安全例子;

import java.util.List;
import java.util.Vector;

/**
 * @ClassName:ArrayListInThread.java
 * @Description:
 * @author gaoguangjin
 * @Date 2015-3-25 下午7:06:37
 */
public class ArrayListInThread implements Runnable {
	// List<String> list1 = new ArrayList<String>(1);// not thread safe
	
	// List<String> list1 = Collections.synchronizedList(new ArrayList<String>());// thread safe
	List<String> list1 = new Vector<String>();// tread safe
	
	public void run() {
		try {
			Thread.sleep((int) (Math.random() * 2));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		list1.add(Thread.currentThread().getName());
	}
	
	public static void main(String[] args) throws InterruptedException {
		ThreadGroup group = new ThreadGroup("testgroup");
		ArrayListInThread t = new ArrayListInThread();
		for (int i = 0; i < 10000; i++) {
			Thread th = new Thread(group, t, String.valueOf(i));
			th.start();
		}
		
		while (group.activeCount() > 0) {
			Thread.sleep(10);
		}
		System.out.println();
		System.out.println(t.list1.size()); // it should be 10000 if thread safe collection is used.
	}
	/**
	 * 一个 ArrayList ，在添加一个元素的时候，它可能会有两步来完成： 1. 在 Items[Size] 的位置存放此元素； 2. 增大 Size 的值。 在单线程运行的情况下，如果 Size =
	 * 0，添加一个元素后，此元素在位置 0，而且 Size=1； 而如果是在多线程情况下，比如有两个线程，线程 A 先将元素存放在位置 0。但是此时 CPU 调度线程A暂停，线程 B 得到运行的机会。线程B也向此 ArrayList
	 * 添加元素，因为此时 Size 仍然等于 0 （注意哦，我们假设的是添加一个元素是要两个步骤哦，而线程A仅仅完成了步骤1），所以线程B也将元素存放在位置0。然后线程A和线程B都继续运行，都增加 Size 的值。
	 * 那好，现在我们来看看 ArrayList 的情况，元素实际上只有一个，存放在位置 0，而 Size 却等于 2。这就是“线程不安全”了。
	 */
}
