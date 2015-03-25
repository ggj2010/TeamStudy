package com.team.gaoguangjin.thread.base;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:LockTest.java
 * @Description: lock测试
 * @author gaoguangjin
 * @Date 2015-3-25 下午3:55:17
 */
@Slf4j
public class LockTest {
	Lock lock = new ReentrantLock();// 可重入锁
	
	ReadWriteLock lock2 = new ReentrantReadWriteLock();
	ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();// 读写锁
	
	final AtomicInteger num = new AtomicInteger();
	Condition condition1 = lock.newCondition();// 条件
	Condition condition2 = lock.newCondition();// 条件
	
	public static void main(String[] args) {
		// demo1();//lock
		// demo2();// tryLock()
		// demo3();// lockInterruptibly()
		// demo4();// ReentrantReadWriteLock 读写锁
		demo5();
		
	}
	
	/**
	 * @Description: 主线程打印1-5 子线程打印 5-10 循环一直
	 * @return:void
	 */
	private static void demo5() {
		final LockTest lt = new LockTest();
		// 注意这里不要用Integer, Integer 是不可变对象
		
		// 初始化A线程
		new Thread("主线程") {
			public void run() {
				lt.test5();
			}
		}.start();
		new Thread("子线程") {
			public void run() {
				lt.test5();
			}
		}.start();
		
	}
	
	protected void test5() {
		lock.lock();
		try {
			while (num.get() < 100) {
				for (int i = 0; i < 5; i++) {
					log.info(Thread.currentThread().getName() + ":" + num.get());
					num.incrementAndGet();
					if (i == 4) {
						System.out.println("----------------------分隔符---------------------");
						condition1.signal();
						condition1.await();
					}
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
		}
		finally {
			lock.unlock();
		}
	}
	
	private static void demo4() {
		final LockTest lt = new LockTest();
		new Thread("线程1") {
			public void run() {
				try {
					lt.test4();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		new Thread("线程2") {
			public void run() {
				try {
					lt.test4();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
	}
	
	private static void demo3() {
		final LockTest lt = new LockTest();
		Thread t1 = new Thread("线程1") {
			public void run() {
				try {
					lt.test3();
				} catch (InterruptedException e) {
					log.info(Thread.currentThread().getName() + "被中断");
				}
			}
		};
		Thread t2 = new Thread("线程2") {
			public void run() {
				try {
					lt.test3();
				} catch (InterruptedException e) {
					log.info(Thread.currentThread().getName() + "被中断");
				}
			}
		};
		t1.start();
		t2.start();
		
		try {
			Thread.sleep(1000);// 让线程堵塞
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// t2.interrupt();
	}
	
	protected void test3() throws InterruptedException {
		lock.lockInterruptibly(); // 注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
		try {
			log.info(Thread.currentThread().getName() + "获得锁锁了");
			long startTime = System.currentTimeMillis();
			for (;;) {
				if (System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
					break;
			}
		} catch (Exception e) {
		}
		finally {
			log.info("释放锁了");
			lock.unlock();
		}
	}
	
	private static void demo2() {
		final LockTest lt = new LockTest();
		new Thread("线程1") {
			public void run() {
				try {
					lt.test2();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		new Thread("线程2") {
			public void run() {
				try {
					lt.test2();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
	}
	
	protected void test2() throws InterruptedException {
		// tryLock()///拿不到锁就直接退出线程，不会继续往下执行
		if (lock.tryLock()) {
			// if (lock.tryLock(1000, TimeUnit.DAYS)) {//线程拿不到锁会等待的，然后继续执行里面的内容
			try {
				log.info(Thread.currentThread().getName() + "得到了锁");
				for (int i = 0; i < 10; i++)
					log.info("当前线程是：" + Thread.currentThread().getName() + ":" + i);
			} catch (Exception e) {
				log.error("lock异常" + e.getLocalizedMessage());
			}
			finally {
				lock.unlock();// 关闭锁
			}
		} else {
			log.info("获取不到锁");
		}
	}
	
	private static void demo1() {
		final LockTest lt = new LockTest();
		new Thread("线程1") {
			public void run() {
				lt.test1();
			}
		}.start();
		
		new Thread("线程2") {
			public void run() {
				lt.test1();
			}
		}.start();
		
	}
	
	protected void test1() {
		// Lock lock = new ReentrantLock();// 不能放在这里，不然是没有效果的，因为是局部变量获取的锁
		try {
			lock.lock();
			for (int i = 0; i < 10; i++)
				log.info("当前线程是：" + Thread.currentThread().getName() + ":" + i);
		} catch (Exception e) {
			log.error("lock异常" + e.getLocalizedMessage());
		}
		finally {
			lock.unlock();// 关闭锁
		}
	}
	
	protected void test4() throws InterruptedException {
		rwl.readLock().lock();// 将读锁锁住
		try {
			log.info(Thread.currentThread().getName() + "得到了锁");
			for (int i = 0; i < 10; i++)
				log.info("当前线程是：" + Thread.currentThread().getName() + ":" + i);
		} catch (Exception e) {
			log.error("lock异常" + e.getLocalizedMessage());
		}
		finally {
			rwl.readLock().unlock();// 关闭锁
		}
	}
}
