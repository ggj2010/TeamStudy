package com.team.util;

import java.text.NumberFormat;

public class NumberUtil {
	public static String getPercent(long x, long total) {
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(0);
		String result = numberFormat.format((float) x / (float) total * 100) + "%";
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(getPercent(20, 30));
	}
}
