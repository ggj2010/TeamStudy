package com.team.gaoguangjin.算法.排序;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;

/**
 * 二分查找法
 * 二分查找又称折半查找，优点是比较次数少，查找速度快，平均性能好；其缺点是要求待查表为有序表，且插入删除困难。
 * 因此，折半查找方法适用于不经常变动而查找频繁的有序列表。首先，假设表中元素是按升序排列，将表中间位置记录的关键字与查找关键字比较，
 * 如果两者相等，则查找成功；否则利用中间位置记录将表分成前、后两个子表，如果中间位置记录的关键字大于查找关键字，
 * 则进一步查找前一子表，否则进一步查找后一子表。重复以上过程，直到找到满足条件的记录，使查找成功，或直到子表不存在为止，此时查找不成功。
 * @author:gaoguangjin
 * @date 2017/8/10 9:38
 */
@Slf4j
public class BinarySearch {
    public static void main(String[] args) {
        int[] arrays=new int[11];
        Random random=new Random();
        int data=123;
        for (int i=0;i<10;i++) {
            arrays[i]=(random.nextInt(200));
        }
        arrays[10]=data;
        Arrays.sort(arrays);
        for (int array : arrays) {
            log.info(array+"");
        }
        int seq=search(arrays,data,0,arrays.length-1);
        System.out.println("数组位置："+seq);
    }

    private static int search(int[] arrays,int data,int left,int right) {
        if(data < arrays[left] || data > arrays[right] || left > right){
            return -1;
        }
        int middleSeq=(left+right)/2;
        int middleData=arrays[middleSeq];
        if(data>middleData){
            return search(arrays,data,middleSeq+1,right);
        }else  if(data<middleData){
            return search(arrays,data,left,middleSeq-1);
        }else  if(data==middleData){
            return middleSeq;
        }
        return 0;
    }
}
