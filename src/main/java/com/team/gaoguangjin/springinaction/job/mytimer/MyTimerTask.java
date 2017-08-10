package com.team.gaoguangjin.springinaction.job.mytimer;

/**
 * @author:gaoguangjin
 * @date 2017/8/9 13:58
 */
public abstract class MyTimerTask implements Runnable {
    //保证只执行一次
    final Object lock = new Object();
    public abstract void run();
}
