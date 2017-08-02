package com.team.gaoguangjin.深入java虚拟机.类解析;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型
 * @author:gaoguangjin
 * @date 2017/7/6 13:48
 */
public class Generics {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        List<String> list=new ArrayList<>();
        list.add("1");
        String a=list.get(1);
    }
}
