package com.team.gaoguangjin.javabase.basetype;

/**
 * @author:gaoguangjin
 * @date:2017/11/22
 */
public class BaseType {
    public static void main(String[] args) {

        bytetype();
        inttype();
        chartype();
        floattype();
        doubletype();
    }

    /**
     * double数据类型是双精度、64位
     */
    private static void doubletype() {
        double a=123.3333333;
    }

    /**
     * float数据类型是单精度、32位
     */
    private static void floattype() {
        float a=123.129999999f;
    }

    /**
     * char类型是一个单一的16位Unicode字符；用 ‘’表示一个字符
     */
    private static void chartype() {
        char a='d';
    }

    /**
     * 最小值是-2,147,483,648（-2^31）；
     * 最大值是2,147,485,647（2^31 - 1）；
     */
    private static void inttype() {

    }

    /**
     * byte数据类型是8位、有符号的，以二进制补码表示的整数；（256个数字），占1字节
     * 最小值是-128（-2^7）；
     * 最大值是127（2^7-1）；
     */
    private static void bytetype() {
        byte a = -128;
    }
}
