package com.team.gaoguangjin.springinaction.job.mytimer;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author:gaoguangjin
 * @date 2017/8/9 13:56
 */
public class MyTimer {

	private static AtomicInteger atomicInteger = new AtomicInteger();

	private MyTimerThread timerThrad = new MyTimerThread();

	private MyTimerTask task;

	public MyTimer() {
		this("jobtimer-" + getSerialNum());
	}

	public MyTimer(String name) {
		timerThrad.setName(name);
		timerThrad.start();
	}

	private static Integer getSerialNum() {
		return atomicInteger.getAndIncrement();
	}

    public void schedul(MyJob myJob, long delay, long period) {
        //异步处理，不是一个定时启动一个定时



    }
    class TaskQueue{
//        MyTimerTask[] myTimerTask=new MyTimerTask[];


    }


    class MyTimerThread extends Thread {
		@Override
		public void run() {
            while (true){
            }
		}
	}
}
