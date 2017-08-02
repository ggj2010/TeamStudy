package com.team.gaoguangjin.javabase.classforname.parent2;

/**
 * @author:gaoguangjin
 * @date 2017/7/27 9:49
 */
public class Child extends Parent{
    String name="Child";
    public Child() {
        System.out.println("子类：Child()");
    }
    public Child(String name) {
        System.out.println("子类：Child(String name)");
    }

    public void test(){
        System.out.println("test:name="+name);
    }
}
