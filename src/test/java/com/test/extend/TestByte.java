package com.test.extend;

import java.io.UnsupportedEncodingException;

/**
 * @author:gaoguangjin
 * @date:2018/4/11
 */
public class TestByte {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("a".getBytes()[0]);
        System.out.println("a".charAt(0)+0);
        System.out.println("高".getBytes().length);
        System.out.println("高".getBytes("gbk").length);
    }
}
