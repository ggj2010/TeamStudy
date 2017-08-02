package com.team.gaoguangjin.设计模式.观察者.review;

/**
 * @author:gaoguangjin
 * @date 2016/7/5 17:14
 */
public class GirlObserver implements  Observer {

    private String name;
    public GirlObserver(String name){
        this.name=name;
    }
    @Override
        public void display() {
            System.out.println(name+"girl被通知了---");
        }
}