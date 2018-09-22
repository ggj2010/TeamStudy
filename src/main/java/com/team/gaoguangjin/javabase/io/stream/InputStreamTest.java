package com.team.gaoguangjin.javabase.io.stream;


import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author:gaoguangjin
 * @date:2017/11/22
 */
@Slf4j
public class InputStreamTest {
    public static void main(String[] args) throws IOException {
        byte a = 1;
        byte b = 2;
        byte c = (byte) (a + b);

        File file = new File("/Users/gaoguangjin/Desktop/a.txt");
        FileInputStream fis = new FileInputStream(file);
        long length = file.length();
        //availabe 是针对输入流，在阻塞的情况下，还可以读取多少个字节
        int length2 = fis.available();
        log.info(length + "");

        // readOneByte(fis, file);
        readOneBytes(fis, file);
    }

    private static void readOneBytes(FileInputStream fis, File file) throws IOException {
        //每次读取1m
        byte[] bytes = new byte[7];
        StringBuffer sb = new StringBuffer();
        int length=0;
        while ((length=fis.read(bytes)) > -1) {
            sb.append(new String(bytes,0,length,"utf-8"));
        }
        log.info(sb.toString());
    }

    /**
     * 一次只能读取一个字节 比较坑
     *
     * @param fis
     * @param file
     * @throws IOException
     */
    private static void readOneByte(FileInputStream fis, File file) throws IOException {
        int a;
        List<Integer> list = new ArrayList<>();
        while ((a = fis.read()) > 0) {
            list.add(a);
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i).byteValue();
        }
        log.info(new String(bytes));
    }
}
