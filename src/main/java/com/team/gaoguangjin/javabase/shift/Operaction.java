package com.team.gaoguangjin.javabase.shift;


import lombok.extern.slf4j.Slf4j;


/**
 * 运算复活
 * & 与运算 只有两个位都是1，结果才是1
 * | 或运算 两个位只要有一个为1，那么结果就是1，否则就为0，
 * && ||
 * ~ 非运算 果位为0，结果是1，如果位为1，结果是0，
 * ^ 异或运算 两个操作数的位中，相同则结果为0，不同则结果为1。
 * @author:gaoguangjin
 * @date 2017/6/27 14:41
 */
@Slf4j
public class Operaction {

    public static void main(String[] args) {
//        0010
        int a=2;
//        0100
        int b=4;
        //0010
        //0100

        int c=a&b;
        int d=a|b;
        int e=a^b;
        log.info(c+"");
        log.info(d+"");
        log.info(e+"");
    }
}
