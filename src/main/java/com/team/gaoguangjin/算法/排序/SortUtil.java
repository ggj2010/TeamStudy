package com.team.gaoguangjin.算法.排序;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName:SortUtil.java
 * @Description:获取随机数组util
 * @author gaoguangjin
 * @Date 2015-3-20 上午8:56:40
 */
@Slf4j
public class SortUtil {
	
	public static int[] getRandomArray(int length) {
		int[] arry = new int[length];
		Random random = new Random();
		for(int i = 0; i < length - 1; i++) {
			arry[i] = random.nextInt(length * 10);
		}
		display("之前", arry);
		return arry;
	}
	
	public static void display(String key, int[] array) {
		String str = "";
		for(int i = 0; i < array.length; i++) {
			str += array[i] + " ";
		}
		log.info("排序" + key + "数组顺序是：" + str);
	}
}
