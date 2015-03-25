package com.team.gaoguangjin.javabase.codestyle.接口;

/**
 * @ClassName:StyleInterfaceTest.java
 * @Description: 接口实现方式
 * @author gaoguangjin
 * @Date 2015-3-18 上午11:05:54
 */
public class StyleInterfaceTest {
	public static void main(String[] args) {
		Style normal = new StyleImp();
		Style special = new Style() {
			public void test() {
				System.out.println("这是匿名类的形式");
			}
		};
		
		normal.test();
		special.test();
		
	}
}
