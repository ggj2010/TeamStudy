package com.team.gaoguangjin.test.random;

import java.util.Vector;

public class TestObject {
	public static void main(String[] args) {
		Vector v = new Vector(10);
		for (int i = 1; i < 100000; i++) {
			Object o = new Object();
			v.add(o);
			o = null;
			System.out.println("11");
		}
		
	}
}
