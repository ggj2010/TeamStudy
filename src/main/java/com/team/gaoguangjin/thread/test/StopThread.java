package com.team.gaoguangjin.thread.test;

import java.util.concurrent.TimeUnit;

/**
 * happens-before关系只保证可见性，不保证其它，比如原子性 和有序性
 * @author:gaoguangjin
 * @date 2016/12/20 15:30
 */
public class StopThread {
    private static  boolean stopRequested;
//    private static volatile boolean stopRequested;

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (!stopRequested) {
                    i++;
                    //如果加了这一句，这个关键字会自动刷新副本
//                    System.out.println(i);
                }
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
