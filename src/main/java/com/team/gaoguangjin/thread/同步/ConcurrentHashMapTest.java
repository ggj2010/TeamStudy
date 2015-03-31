package com.team.gaoguangjin.thread.同步;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:ConcurrentHashMapTest.java
 * @Description:学习concurrentHashMap
 * @see CopyOnWriteArrayList：专为多线程并发设计的容器，“写入时复制”策略。
 * @see Collections.synchronizedMap：同步容器，独占策略。
 * @author gaoguangjin
 * @Date 2015-3-25 下午6:20:40
 */
@Slf4j
public class ConcurrentHashMapTest {
	public static void main(String[] args) {
		// hashcodeTest();
		// overWhiteHashCode();
		demo();
		demo2();// Collections.synchronized();
	}
	
	/**
	 * @Description: 重写一个map对象的equal方法时候，要记得同时重写其hashcode方法。
	 */
	private static void overWhiteHashCode() {
		Map<Student, Integer> map1 = new HashMap<Student, Integer>();
		Student student1 = new Student("高广金", 18);
		// System.out.println(student1.hashCode());
		Student student2 = new Student("高广金", 18);
		
		map1.put(student1, 1);
		map1.put(student2, 1);
		System.out.println("map1获取student2的值：" + map1.get(student2));
		
		log.info("map总共大小：" + map1.size());
		// equals和hashcode都需要重写，才能匹配
		
	}
	
	private static void hashcodeTest() {
		
		// 重写equals方法时需要重写hashCode方法，主要是针对Map、Set等集合类型的使用；
		Object object = new ConcurrentHashMapTest();
		int key = object.hashCode();
		log.info("key" + key);
		
	}
	
	private static void demo2() {
		Map map = Collections.synchronizedMap(new HashMap());// 线程安全的
		map.put(1, 2);
	}
	
	private static void demo() {
		// 只对写加锁，用reentrantlock加锁
		ConcurrentHashMap chmt = new ConcurrentHashMap();// Segment的结构，一个Segment其实就是一个类Hash Table的结构，Segment内部维护了一个链表数组，
		
	}
	
	@Getter
	@Setter
	public static class Student {
		private String name;
		private int age;
		
		public Student(String name, int age) {
			this.name = name;
			this.age = age;
		}
		
		public boolean equals(Object Object) {// object类型
			return (((Student) Object).age == this.age) && (((Student) Object).name.equals(this.name));
		}
		
		// 重写hashcode方法 ，如果姓名一样判断
		public int hashCode() {
			return name.hashCode() * 37 + age;
		}
		
	}
	
}
