package com.test;

import java.util.regex.Pattern;

public class TestisNumber {
	public static void main(String[] args) {
		System.out.println(isNumeric("12d3"));
		
	}
	
	/**
	 * 判断是否数字
	 * @param number
	 * @return
	 */
	public static boolean isNumeric(String number) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(number).matches();
	}
}
