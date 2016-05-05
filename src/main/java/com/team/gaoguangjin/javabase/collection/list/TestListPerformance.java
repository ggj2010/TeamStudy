package com.team.gaoguangjin.javabase.collection.list;

import org.mortbay.log.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author:gaoguangjin
 * @Description:
 * @Email:335424093@qq.com
 * @Date 2016/4/7 10:40
 */
public class TestListPerformance {
    static int size=200000;


    /**
     * 简单来说,LinkedList更适用于:
     没有大规模的随机读取
     大量的增加/删除操作
     * 一般大家都知道ArrayList和LinkedList的大致区别：
     1.ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。
     2.对于随机访问get和set，ArrayList觉得优于LinkedList，因为LinkedList要移动指针。
     3.对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。
     这一点要看实际情况的。若只对单条数据插入或删除，ArrayList的速度反而优于LinkedList。但若是批量随机的插入删除数据，LinkedList的速度大大优于ArrayList. 因为ArrayList每插入一条数据，要移动插入点及之后的所有数据。
     这一点我做了实验。在分别有200000条“记录”的ArrayList和LinkedList的首位插入20000条数据，LinkedList耗时约是ArrayList的20分之1。
     * @param args
     */
    public static void main(String[] args) {
        long begintime = System.currentTimeMillis();
        List<String> arrayList=new ArrayList<String>();
        List<String> linkList=new LinkedList<String>();
          linkList(linkList);
        long endtime = System.currentTimeMillis();

        System.out.println("linkList时间：" + (endtime - begintime));

        arrayList(arrayList);
        long endtime2 = System.currentTimeMillis();

        System.out.println("arrayList时间：" + (endtime2 - endtime));
    }

    private static void arrayList(List<String> arrayList) {
        for (int i = 0; i <size ; i++) {
            arrayList.add(i, i + "");
        }
    }

    private static void linkList(List<String> linkList) {
        for (int i = 0; i <size ; i++) {
            linkList.add(i, i + "");
        }
    }

    public static void insertList(LinkedList linklist, ArrayList arraylist) {
        long time1 =  System.currentTimeMillis();
        System.out.println(time1);
        for (int i = 0; i < 2000000; i++) {
            linklist.add(i, "linklist" + i);
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        for (int j = 0; j < 2000000; j++) {
            arraylist.add(j, "arraylist" + j);
        }
        long time3 =  System.currentTimeMillis();
        System.out.println(time3 - time2);
    }



}
