package com.team.gaoguangjin.深入java虚拟机.垃圾回收;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;


/**
 * java 弱引用
 * -Xmx10M -Xms10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -Xmn5M
 * <p>
 * 强引用：垃圾回收期不会主动去回收，哪怕Java虚拟机报OutOfMemoryError
 * 软引用：当Java堆内存不够的时候，才会进行回收
 * 弱引用：比软引用更容易被回收，当垃圾回收期线程执行的时候，不管当前堆内存是否够，都会回收
 * 不过，由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。
 *
 * @author:gaoguangjin
 * @date 2017/7/4 10:50
 */
@Slf4j
public class WeakReferenceTest {

    public static void main(String[] args) throws InterruptedException {
        //软引用
        //testSoftReference();
        // 弱引用
        testWeakReference();
    }

    /**
     * 循环第6 次,当Java gc线程执行到时候不管当前堆内存是否够用都会直接回收弱引用
     *
     * @throws InterruptedException
     */
    private static void testWeakReference() throws InterruptedException {
        WeakReference<TestObject> weakReference = new WeakReference<TestObject>(new TestObject());
        List<byte[]> list = new ArrayList<>();
        int i = 0;
        while (true) {
            byte[] byteArray = new byte[1024 * 1024 * 1];
            list.add(byteArray);
            if (weakReference.get() == null) {
                log.info("当内存不够了，弱引用被回收了，循环第{} 次", "" + i);
                break;
            } else {
                Thread.sleep(1000);
            }
            i++;
        }
    }

    /**
     * 当内存不够了，弱引用被回收了，循环第2 次
     * @throws InterruptedException
     */
    private static void testSoftReference() throws InterruptedException {
        SoftReference<TestObject> softReference = new SoftReference<TestObject>(new TestObject());
        List<byte[]> list = new ArrayList<>();
        int i = 0;
        while (true) {
            byte[] byteArray = new byte[1024 * 1024 * 1];
            list.add(byteArray);
            if (softReference.get() == null) {
                log.info("循环第{} 次,当Java gc线程执行到时候不管当前堆内存是否够用都会直接回收弱引用", "" + i);
                break;
            } else {
                Thread.sleep(1000);
            }
            i++;
        }
    }

    static class TestObject {
        byte[] byteArray = new byte[1024 * 1024 * 3];
    }
}
