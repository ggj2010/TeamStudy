package com.team.gaoguangjin.javabase.classforname;

import static oracle.net.aso.C00.s;
import static org.hsqldb.Library.sString;

/**
 * 携程面试题
 * 输出为null
 *  new Sub();==》父类构造方法，因为子类重写了callName()，所以调用的是子类的
 * @author:gaoguangjin
 * @date 2017/7/27 10:01
 */
public class Base {
    private  String baseName="base";

    public Base() {
        callName();
    }

    public void callName(){
        System.out.println("父类"+baseName);
    }
    static class Sub extends  Base{
        private   String baseName="sub";
//        private  static String baseName="sub";
        public void callName(){
            System.out.println("子类"+baseName);
        }
    }

    public static void main(String[] args) {
        new Sub();
    }
}
