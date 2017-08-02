package com.team.gaoguangjin.javabase.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * list 按照1.5倍扩容
 * 没有扩容因子
 * @author:gaoguangjin
 * @date 2017/6/19 10:58
 */
public class Capacity {
    public static void main(String[] args) {
        List<String> listToArray = new ArrayList<String>(2);
        listToArray.add("guan");
        listToArray.add("bao");

        String[] str = new String[] { "a", "b" };
        //返回的是个ArrayList 匿名类
        List list = Arrays.asList(str);

    }
}
