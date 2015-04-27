package com.team.gaoguangjin.工具.lombok;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.val;

/**
 * @ClassName:ValTest.java
 * @Description: val关键字写出这样的代码来，看起来就是duck type啊
 * @author gaoguangjin
 * @Date 2015-4-22 上午11:35:07
 */
public class ValTest {
	public static void main(String[] args) {
		normal();
		useAnnation();
	}
	
	private static void useAnnation() {
		val list = new ArrayList<String>();
		list.add("abc");
		System.out.println(list.get(0));
		
		val map = new HashMap<Integer, String>();
		map.put(0, "zero");
		map.put(5, "five");
		for (val entry : map.entrySet()) {
			System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
		}
		
	}
	
	private static void normal() {
		// TODO Auto-generated method stub
		
	}
}
