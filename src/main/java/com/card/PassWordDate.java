package com.card;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:gaoguangjin
 * @date 2016/12/22 12:48
 */
public class PassWordDate {


    public static void main(String[] args) throws IOException {
        List<String> monthList=new ArrayList<>();
        for (int i = 1; i <=12 ; i++) {
            if(i<10) {
                monthList.add("0"+ i);
            }else {
                monthList.add(i+"");
            }
        }
        List<String> dateList=new ArrayList<>();
        for (int i = 1; i <=31 ; i++) {
            if(i<10) {
                dateList.add("0" + i);
            }else {
                dateList.add(i+"");
            }
        }
        StringBuilder sb=new StringBuilder();
        for (int i = 1970; i <1990 ; i++) {
            for (String m : monthList) {
                for (String d : dateList) {
                    sb.append(i+"-"+m+"-"+d+"\r\n");
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("c://date.txt")));
       bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
