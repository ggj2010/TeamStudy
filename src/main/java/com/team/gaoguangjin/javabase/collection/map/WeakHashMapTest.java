package com.team.gaoguangjin.javabase.collection.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import lombok.extern.slf4j.Slf4j;

/**
 * -Xmx5M -XX:+PrintGCDetails
 * @author:gaoguangjin
 * @date 2017/6/20 11:55
 */
@Slf4j
public class WeakHashMapTest {
    public static void main(String[] args) {
        try {
//            test1();
            test2();
        } catch (Exception e) {
            log.error("exception", e);
        }
    }

    private static void test1() {
        Map<Integer, Object> map = new WeakHashMap<Integer, Object>();
        for (int i = 0; i < 10000; i++) {
            map.put(i, new byte[i]);
        }
    }

    /**
     * 如果存放在WeakHashMap中的key都存在强引用，那么WeakHashMap就会退化成HashMap。如果在系统中希望通过WeakHashMap自动清楚数据，
     * 请尽量不要在系统的其他地方强引用WeakHashMap的key，否则，这些key就不会被回收，WeakHashMap也就无法正常释放它们所占用的表项。
     *
     * 要想WeakHashMap能够释放掉key被GC的value的对象，尽可能的多调用下put/size/get等操作，因为这些方法会调用expungeStaleEntries方法，expungeStaleEntries方法是关键，而如果不操作WeakHashMap，以企图WeakHashMap“自动”释放内存是不可取的，
     * 这里的“自动”是指譬如map.put(obj,new byte[10M])；之后obj=null了，之后再也没掉用过map的任何方法，那么new出来的10M空间是不会释放的。
     */
    private static void test2() {
        Map<Integer, Object> map = new WeakHashMap<Integer, Object>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Integer ii = new Integer(i);
            list.add(ii);
            map.put(ii, new byte[i]);
        }
    }
}
