package com.team.gaoguangjin.算法.排序;

import lombok.extern.slf4j.Slf4j;

/**
 * 选择排序（Selection sort）是一种简单直观的排序算法。它的工作原理是每一次从待排序的数据元素中选出最小（或最大）的一个元素，存放在序列的起始位置，直到全部待排序的数据元素排完。
 * 选择排序是不稳定的排序方法（比如序列[5， 5， 3]第一次就将第一个[5]与[3]交换，导致第一个5挪动到第二个5后面）。
 *
 * @author gaoguangjin
 * @ClassName:SelectSort.java
 * @Description: 选择排序
 * @Date 2015-3-20 上午9:25:46
 */
@Slf4j
public class SelectSort {
    public static void main(String[] args) {
        int size = 5;
        sortNormal(SortUtil.getRandomArray(size), size);
    }

    /**
     * @param randomArray
     * @param length
     * @Description: 用一个参数下标控制,找到最小的下标数
     * @return:void
     */
    public static void sortNormal(int[] randomArray, int length) {
        int m = 0;
        for (int i = 0; i < length - 1; i++) {
            // 最小值
            int min = i;
            for (int j = i; j < length; j++) {
                if (randomArray[min] > randomArray[j]) {
                    // 最小值的位置变化
                    min = j;
                }
            }
            if (i != min) {
                int temp = randomArray[i];
                randomArray[i] = randomArray[min];
                randomArray[min] = temp;
                m++;
            }
            SortUtil.display("之间", randomArray);
        }
        SortUtil.display("选择之后", randomArray);
        log.info("select Sort 交换次数：{}", m);
    }
}
