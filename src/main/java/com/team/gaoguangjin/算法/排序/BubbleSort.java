package com.team.gaoguangjin.算法.排序;

/**
 * @ClassName:BubbleSort.java
 * @Description: 冒泡排序
 * @author gaoguangjin
 * @Date 2015-3-20 上午8:51:36
 */
public class BubbleSort {
	public static void main(String[] args) {
		arraySort(SortUtil.getRandomArray(10), 10);
	}
	
	private static void arraySort(int[] randomArray, int length) {
		int temp = 0;
		for (int i = 0; i < length - 1; i++) {// 最多做n-1趟排序
			// 因为排序设计到j+1 所以不能用j<= length - 1 - i
			for (int j = 0; j < length - 1 - i; j++) { // 对当前无序区间[0......length-i-1]进行排序(j的范围很关键，这个范围是在逐步缩小的)
				if (randomArray[j] > randomArray[j + 1]) {
					temp = randomArray[j];
					randomArray[j] = randomArray[j + 1];
					randomArray[j + 1] = temp;
				}
			}
		}
		
		SortUtil.display("之后", randomArray);
	}
}
