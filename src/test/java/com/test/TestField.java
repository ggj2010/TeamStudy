package com.test;

import java.lang.reflect.Field;

public class TestField {
	
	public static void main(String[] args) throws Exception {
		Student st = new Student();
		
		Field fild = st.getClass().getDeclaredField("name");
		
		fild.set(st, "高广金");
		System.out.println(st.getName());
	}
	
	public static class Student {
		String name;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
	}
}
