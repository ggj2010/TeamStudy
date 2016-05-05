package com.team.gaoguangjin.javabase.insanceof;

/**
 * author:gaoguangjin
 * Description:
 * Email:335424093@qq.com
 * Date 2016/2/19 11:02
 */
public class InstanceOfTest {
    public static void main(String[] args) {
        A a=new A();
        B b=new B();
        System.out.println(a instanceof PInterface);
        //a 是不是实现PInterface
        System.out.println("a 是不是实现PInterface："+PInterface.class.isInstance(a));
        //b 是不是继承a 第一个为flalse  第二个为对的
        System.out.println("b 是不是继承a:"+a.getClass().isInstance(B.class));
        System.out.println("b 是不是继承a:"+a.getClass().isInstance(b));
        System.out.println("b 是不是继承a:"+A.class.isInstance(b));


       // System.out.println("B 是不是继承C:"+C.class.isInstance(b));
        System.out.println("B 是不是和A有派生关系:"+A.class.isAssignableFrom(B.class));

    }
}
