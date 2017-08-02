package com.team.gaoguangjin.设计模式.观察者.review;

import java.io.IOException;

/**
 * @author:gaoguangjin
 * @date 2016/7/5 17:27
 */
public class Test {
    public static void main(String[] args) throws IOException {
        Subjet subjet=new Subjet();
        Observer boy=new BoyObserver("ggj");
        Observer girl=new GirlObserver("zjy");
        Observer girlTwo=new GirlObserver("zhh");
        subjet.addListener(boy);
        subjet.addListener(girl);
        //添加观察者

        subjet.update();
        //
        System.in.read();
        subjet.addListener(girlTwo);
        subjet.update();
    }
}
