package com.team.gaoguangjin.javabase.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static oracle.net.aso.C00.s;

/**
 * 正则表达式
 * @author:gaoguangjin
 * @date 2016/12/22 15:46
 */
public class Regex {
    public static void main(String[] args) {
        demo1();
    }

    private static void demo1() {
        //要求正则表达式把所有http开头 .jpg结尾的图片都改成https
        String str="dasfasdf http://aaaa.jpg 1111 http://bbbb.jpg 2222 http://ccc.jpg cadsfsda http://dsafsdfccc.jpg";
        String[] dd = str.split("http:([\\s\\S]?)jpg");
        System.out.println(str.replaceAll("http:([\\S]*?)jpg","gao"));

    }
}
