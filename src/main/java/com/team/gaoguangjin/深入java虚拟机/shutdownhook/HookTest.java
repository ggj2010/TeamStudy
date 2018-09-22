package com.team.gaoguangjin.深入java虚拟机.shutdownhook;

import lombok.extern.slf4j.Slf4j;

/**
 * jvm强制退出的时候 等待ShutdownHook程序执行完全
 * 关闭钩子本质上是一个线程（也称为Hook线程），对于一个JVM中注册的多个关闭钩子它们将会并发执行，
 * 所以JVM并不保证它们的执行顺序；由于是并发执行的，那么很可能因为代码不当导致出现竞态条件或死锁等问题，
 * 为了避免该问题，强烈建议在一个钩子中执行一系列操作
 *
 * @author:gaoguangjin
 * @date:2018/5/9
 */
@Slf4j
public class HookTest {
    public static void main(String[] args) {
        //不要添加多个hook 防止程序退出的时候并发执行
        Runtime.getRuntime().addShutdownHook(new CloseIoThread());
        doWork();
    }

    private static void doWork() {
        log.info("do work ");
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class CloseIoThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                log.info("hoock close");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
