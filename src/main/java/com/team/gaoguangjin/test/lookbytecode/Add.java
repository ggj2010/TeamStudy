package com.team.gaoguangjin.test.lookbytecode;

public class Add {
	public static void main(String[] args) {
		// test1();
		test2();
		
	}
	
	private static void test2() {
		int afterAdd = 0;
		afterAdd = afterAdd++;
		
		System.out.println("afterAdd:" + afterAdd);
		
	}
	
	private static void test1() {
		int afterAdd = 0;
		afterAdd++;
		
		int m = afterAdd;
		System.out.println("====正常的=====");
		System.out.println("afterAdd:" + afterAdd);
		System.out.println("m:" + m);
		System.out.println();
		
		int afterAdd2 = 0;
		afterAdd2 = afterAdd2++;
		int m2 = afterAdd2;
		System.out.println("=====不正常的=====");
		System.out.println("afterAdd2:" + afterAdd2);
		System.out.println("m2:" + m2);
		System.out.println();
		
		int afterAdd3 = 0;
		int m3 = afterAdd3++;
		System.out.println("=====m3不正常的");
		System.out.println("afterAdd3:" + afterAdd3);
		System.out.println("m3:" + m3);
		
		int m4 = 0;
		int afterAdd4 = 0;
		
		m4 = afterAdd4++;
		System.out.println("=====m3不正常的");
		System.out.println("afterAdd4:" + afterAdd4);
		System.out.println("m4:" + m4);
	}
}
