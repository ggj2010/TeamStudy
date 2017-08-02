package com.team.gaoguangjin.test.string;



/**
 * @author:gaoguangjin
 * @date 2017/6/30 17:26
 */
public class StringTest {
    public static void main(String[] args) {
        String a = "12";
        String b = "1" + "2";// 编译后虚拟机直接优化成 LDC "12"
        String c = "1" + new String("2");// NEW java/lang/StringBuilder append
        System.out.println(a == b);
        System.out.println(a == c);

        String d = "ddd";//会放到常量池
        String f = d.intern();//直接从常量池里面拿数据
        System.out.println(d == f);//true

        String e = new String("ddd");
        String g = e.intern();//直接从常量池里面拿数据

        System.out.println(e == g);//false

    }
}
