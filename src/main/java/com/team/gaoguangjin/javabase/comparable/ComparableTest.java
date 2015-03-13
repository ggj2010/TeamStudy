package com.team.gaoguangjin.javabase.comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName:ComparableTest.java
 * @Description:接口强行对实现它的每个类的对象进行整体排序。此排序被称为该类的自然排序, Collections.sort和和 Arrays.sort 排序
 * @author gaoguangjin
 * @Date 2015-3-12 下午1:49:03
 */
public class ComparableTest {
	public static void main(String[] args) {
		test();
	}
	
	private static void test() {
		cp1 a = new cp1(10);
		cp1 b = new cp1(20);
		cp1 c = new cp1(30);
		List<cp1> list = new ArrayList<cp1>();
		list.add(b);
		list.add(a);
		list.add(c);
		Collections.sort(list);
		// 如果list里面add的 cp1没有实现Comparable，那么利用sort排序输出的就是 一开始放入list的顺序 20,10,30
		
		// 如果cp1实现了 Comparable 并且 value > o.value 返回 1 那么排序是从小打大
		
		// 如果cp1实现了 Comparable 并且 value > o.value 返回 -1 那么排序是从大到小
		
		for (cp1 cp : list)
			System.out.println(cp.value);
		
		// Arrays.sort（数组）;
		
	}
	
	public static class cp1 implements Comparable<cp1> {
		int value;
		
		public cp1(int value) {
			this.value = value;
		}
		
		/**
		 * 修改排序规则 /这里比较的是什么 sort方法实现的就是按照此比较的东西从小到大排列
		 */
		// public int compareTo(cp1 o) {
		// if (value > o.value) // 大于比较的值 返回1 那么排序是从小到大的顺序
		// return 1;
		// else if (value < o.value)
		// return -1;
		// return 0;
		// }
		
		public int compareTo(cp1 o) {
			if (value > o.value) // 大于比较的值 返回-1 那么排序是从大到小的顺序
				return -1;
			else if (value < o.value)
				return 1;
			return 0;
		}
		
	}
}
