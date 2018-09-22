package com.team.gaoguangjin.算法.排序;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 直接排序
 * 比较 N*(N+1)/2次
 *
 * @author:gaoguangjin
 * @date:2018/4/19
 */
@Slf4j
public class NormalSort {
    public static void main(String[] args) {
        int size = 5;
        int[] array1={14 ,7, 6, 5, 13};
        int[] array2={14 ,7, 6, 5, 13};
        SortUtil.display("之前", array1);
        normalSort(array1,size);
        SelectSort.sortNormal(array2,size);
    }

    /**
     * 双重for循环,每次循环得到最小的一个数
     * @param randomArray
     * @param length
     */
    private static void normalSort(int[] randomArray, int length) {
        int m=0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (randomArray[i] > randomArray[j]) {
                    int temp = randomArray[i];
                    randomArray[i] = randomArray[j];
                    randomArray[j] = temp;
                    m++;
                }
            }
            SortUtil.display("之间", randomArray);
        }
        SortUtil.display("之后", randomArray);
        log.info("normalSort 交换次数：{}",m);
    }
}
