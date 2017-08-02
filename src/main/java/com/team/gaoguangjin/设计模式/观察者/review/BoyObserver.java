package com.team.gaoguangjin.设计模式.观察者.review;

/**
 * @author:gaoguangjin
 * @date 2016/7/5 17:14
 */
public class BoyObserver implements  Observer {
    private String name;
    public BoyObserver(String name){
        this.name=name;
    }
    @Override
    public void display() {
        System.out.println(name+"boy被通知了---");
    }
}
