package com.team.gaoguangjin.javabase.collection.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import lombok.extern.slf4j.Slf4j;

import org.mortbay.log.Log;

/**
 * @ClassName:SplitListTest.java
 * @Description: 分割list的
 * @author gaoguangjin
 * @Date 2015-3-25 下午2:41:15
 */
@Slf4j
public class SplitListTest {
	
	public static void main(String[] args) {
		// demo();
		capability();// 分割效率
	}
	
	/**
	 * @Description: 分割list
	 * @return:void
	 */
	private static void capability() {
		SplitListTest s1 = new SplitListTest();
		List list = new ArrayList();
		for (int i = 0; i < 10000000; i++)
			list.add(i);
		
		long begintime1 = System.currentTimeMillis();
		
		s1.userSub(list);
		long endtime1 = System.currentTimeMillis();
		
		long begintime2 = System.currentTimeMillis();
		s1.useClass(list);
		long endtime2 = System.currentTimeMillis();
		
		Log.info("userSub花费时间：" + (endtime1 - begintime1));
		Log.info("userSwitch花费时间：" + (endtime2 - begintime2));
	}
	
	private void useClass(List list) {
		ListSwitch lw2 = new ListSwitch();
		int m = list.size() / 10;
		lw2.list0 = list.subList(0, m);
		log.info("useClass:" + lw2.list0.get(9999));
		lw2.list1 = list.subList(m * 1, m * 2);
		log.info("useClass:" + lw2.list1.get(9999));
		lw2.list2 = list.subList(m * 2, m * 3);
		lw2.list3 = list.subList(m * 3, m * 4);
		lw2.list4 = list.subList(m * 4, m * 5);
		lw2.list5 = list.subList(m * 5, m * 6);
		lw2.list6 = list.subList(m * 6, m * 7);
		lw2.list7 = list.subList(m * 7, m * 8);
		lw2.list8 = list.subList(m * 8, m * 9);
		lw2.list9 = list.subList(m * 9, m * 10);
		
	}
	
	private void userSub(List list) {
		ListSwitch ls = new ListSwitch();
		ls.listCut(list);
		log.info("userSub:" + ls.list0.get(9999));
		log.info("userSub:" + ls.list1.get(9999));
		
	}
	
	private static void demo() {
		List list = new Vector();
		for (int i = 0; i < 10; i++)
			list.add(i);
		List list1 = list.subList(0, 5);
		List list2 = list.subList(5, list.size());
		
		System.out.println(list.size());
		System.out.println("list1:" + list1.size());
		System.out.println("list2:" + list2.size());
		
		System.out.println("list1: get(2)==>" + list1.get(2));
		System.out.println("list2: get(2)==>" + list2.get(2));
		
	}
	
}
