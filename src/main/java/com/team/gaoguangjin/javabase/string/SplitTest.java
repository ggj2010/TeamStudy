package com.team.gaoguangjin.javabase.string;

import lombok.extern.slf4j.Slf4j;

/**
 * @author:gaoguangjin
 * @date 2017/1/22 9:54
 */
@Slf4j
public class SplitTest {

    public static void main(String[] args) {
        //"|" 对正则表达式来说是特殊字符 所以必须要进行转移
        String str = "gao|gguang|jin";
        String[] array = str.split("|");
        for (String s : array) {
            log.info("没有转译分割，{}", s);
        }
        //其中indexof的参数是字条串，而split的参数是正则表达式，在正则表达式中\\表示一个反斜线，所以split需要4个反斜线来表示\\。
        // 在正则表达式中的“\\”表示和后面紧跟着的那个字符构成一个转义字符（姑且先这样命名），代表着特殊的意义；所以如果你要在正则表达式中表示一个反斜杠\，应当写成“\\\\”
        // "\\" 对java来说就是一个反斜杠\
        String[] array2 = str.split("\\|");
        for (String s : array2) {
            log.info("\\|转译分割，{}", s);
        }

    }
}
