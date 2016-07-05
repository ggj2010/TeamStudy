package com.team.gaoguangjin.javabase.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.blogjava.net/1186858036/archive/2012/04/18/375163.html
 * @author:gaoguangjin
 * @date 2016/5/13 10:27
 */
public class HashTest {


    public static void main(String[] args) {
        String a="Aa";
        String b="BB";
        //java String类型重写了native的hashCode方法，只要长度一致 hashCode都是一样的
        System.out.println("Aa=BB "+(a.hashCode()==b.hashCode()));

// Hash Collision DoS
        hashDOS();
        String c="0 1";
        String[] ids=c.split(" ");
        System.out.println(ids[0]);
    }

    private static void hashDOS() {
        Map<String,String> map =new HashMap<String,String>();
    }


    public int hashCodes(int hash,char[] value) {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;
            for (int i = 0; i < value.length; i++) {
                //s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }

}
