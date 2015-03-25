package com.team.gaoguangjin.javabase.statics;

/**
 * @ClassName:StaticTest.java
 * @Description: 对于静态变量在内存中只有一个拷贝（节省内存），JVM只为静态分配一次内存，在加载类的过程中完成静态变量的内存分配
 * @author gaoguangjin
 * @Date 2015-3-23 下午1:19:50
 */
public class StaticTest {
	public Student student = new Student();
	public static StaticStudent staticStudent = new StaticStudent();// 所有对象都共享
	
	public static void main(String[] args) {
		StaticTest st1 = new StaticTest();
		StaticTest st2 = new StaticTest();
		StaticTest st3 = new StaticTest();
		
		st1.student.name = "高广金";
		st2.staticStudent.name = "高广金";
		
		st2.normal(st3);
		st1.staticTest(st3);
		
	}
	
	/**
	 * @Description: 获取的是没加static对象
	 * @param st3
	 * @return:void
	 */
	private static void normal(StaticTest st3) {
		System.out.println(st3.student);
	}
	
	/**
	 * @Description: 获取的是加static的对象
	 * @param st3
	 * @return:void
	 */
	private void staticTest(StaticTest st3) {
		System.out.println(st3.staticStudent);
	}
	
}
