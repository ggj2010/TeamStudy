package com.team.gaoguangjin.javabase.classforname.parent2;

/**
 * @author:gaoguangjin
 * @date 2017/7/27 9:49
 */
public class Parent {
    String name="Parent";
    public Parent() {
        System.out.println("父类：Parent()");
        test();
    }
    public void test(){
        System.out.println("test:name="+name);
    }
    public Parent(String name) {
        System.out.println("父类：Parent(String name)");
    }
}
