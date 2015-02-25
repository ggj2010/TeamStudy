package com.team.gaoguangjin.深入java虚拟机.垃圾回收;

/**
 * @ClassName:FinalizeEscapeGC.java
 * @Description: 任何一个对象的finalize()方法都只会被系统自动调用一次， 如果对象面临下一次回收，它的finalize()方法不会再次执行 。
 * @author gaoguangjin
 * @Date 2015-2-14 上午11:44:28
 */
public class FinalizeEscapeGC {
	public static FinalizeEscapeGC SAVE_HOOK = null;
	
	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK = new FinalizeEscapeGC();
		// 对象第一个成功解救自己,虽然调用了gc
		doMethod(1);
		// // 这一段代码和上面的一模一样，但是自我救赎失败
		doMethod(2);
	}
	
	private static void doMethod(int i) throws InterruptedException {
		SAVE_HOOK = null;
		
		System.gc();
		
		// finalizer方法优先级比较低，暂停0.5秒等待他。
		Thread.sleep(500);
		
		if (SAVE_HOOK != null) {
			isAlive(i);
		} else {
			System.out.println("第【" + i + "】次调用：now i am dead");
		}
	}
	
	public static void isAlive(int i) {
		System.out.println("第【" + i + "】次调用：yes, i am still alive:");
	}
	
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalizeMethod is executed");
		SAVE_HOOK = this;
	}
	
}
