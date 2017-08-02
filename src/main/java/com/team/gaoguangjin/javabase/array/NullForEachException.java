package com.team.gaoguangjin.javabase.array;

/**
 * 凡是对集合list，set，map，数组等进行循环一定要进行判断是否为空，增强代码的健壮
 *
 * @author:gaoguangjin
 * @date 2017/2/7 9:36
 */
public class NullForEachException {
    public static void main(String[] args) {
        String[] array = null;
        //其实这样理解就可以了，所有的循环都要先判断其length  如果对象为空，获取length肯定报空指针
        for(int i=0;i<=array.length;i++){

        }

       /* for (String s : array) {

        }*/
    }
}
