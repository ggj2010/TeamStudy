package com.team.gaoguangjin.test.string;

public class StringReplaceTest {
	public static void main(String[] args) {
		String s = "\\";
		s.replaceAll("(\\\\)", "$1$1");
		System.out.println(s);
	}
}
