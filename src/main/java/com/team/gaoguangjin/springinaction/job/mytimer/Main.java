package com.team.gaoguangjin.springinaction.job.mytimer;

/**
 * @author:gaoguangjin
 * @date 2017/8/9 13:56
 */
public class Main {
    public static void main(String[] args) {
        MyJob myJob=new MyJob();
        MyTimer timer=new MyTimer();
        timer.schedul(myJob,1000l,1000l);
    }
}
