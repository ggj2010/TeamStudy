package com.team.gaoguangjin.javabase.collection;


/**
 * java虚拟机编译的时候会对for each进行转换
 * 对collection是用nested iteratoration来实现的，对数组是用下标遍历来实现
 * @author:gaoguangjin
 * @date 2017/7/3 9:42
 */
public class Foreach {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        String[] arrays = {"a", "b"};
        for (String array : arrays) {
            System.out.println(array);
        }
        //转换成以下类型
        for (int i = 0; i < arrays.length; i++) {
            System.out.println(arrays[i]);
        }
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        for (Integer i : list) {
//            System.out.println("" + i);
//        }
    }
}
