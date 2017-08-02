package com.team.gaoguangjin.设计模式.观察者.review;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoguangjin
 * @date 2016/7/5 17:13
 */
public class Subjet {
   private List<Observer> observerList=new ArrayList<>();


    public void addListener(Observer observer){
        observerList.add(observer);
    }

    public void notifyAlls(){
        for (Observer observer : observerList) {
            observer.display();
        }
    }

    public void  update(){
        notifyAlls();
    }
}
