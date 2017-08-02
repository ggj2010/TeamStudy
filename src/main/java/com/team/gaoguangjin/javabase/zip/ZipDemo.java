package com.team.gaoguangjin.javabase.zip;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import lombok.extern.slf4j.Slf4j;



/**
 * @author:gaoguangjin
 * @date 2016/10/27 14:15
 */
@Slf4j
public class ZipDemo {
    public static void main(String[] args) {
        zip();
//        copy();
    }

    private static void copy() {
        FileInputStream fi= null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
             fi=new FileInputStream(new File("c:a.txt"));
             bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File("c:b.txt")));
            byte[] by= new byte[1024];
            int len;
            while ((len = fi.read(by)) != -1) {
                bufferedOutputStream.write(by, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedOutputStream.close();
                fi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void zip() {
        BufferedOutputStream bufferedOutputStream=null;
        try {
            log.info(new File("c:test.rar").exists()+"");
            ZipFile zipFile = new ZipFile(new File("c:test.zip"),Charset.forName("utf-8"));
            Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zipFile.entries();
            while (entries.hasMoreElements()){
                ZipEntry zipEntry = entries.nextElement();
                InputStream files = zipFile.getInputStream(zipEntry);
                 bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(new File("c:")+zipEntry.getName()));
                byte[] by= new byte[1024];
                int len;
                while ((len = files.read(by)) != -1) {
                    bufferedOutputStream.write(by, 0, len);
                }
                bufferedOutputStream.flush();
            }
        } catch (IOException e) {
            log.error("解压异常：{}" + e.getLocalizedMessage());
        }finally {
            try {
                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
