package com.team.gaoguangjin.深入java虚拟机.垃圾回收;

import lombok.extern.slf4j.Slf4j;

/**
 *  * 运行时环境：-XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=2
 * jstat -gc pid 3000
 * @author:gaoguangjin
 * @date:2018/5/11
 */
public class TestJstatMinorGCTwo {

    public static void main(String[] args) {
        byte[] a;
        int b=2;

    }
}
