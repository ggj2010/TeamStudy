package com.team.gaoguangjin.javabase.collection.list;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @author:gaoguangjin
 * @date 2017/6/16 17:07
 */
@Slf4j
public class SubList {

    //subList 返回的是 ArrayList 的内部类 SubList，并不是 ArrayList ，
    // 而是 ArrayList 的一个视图，对于SubList子列表的所有操作最终会反映到原列表上
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        try {
            ArrayList<Integer> dd = (ArrayList<Integer>) list.subList(0, 1);
        } catch (Exception e) {
            log.error("" + e);
        }

        try {
            // if (modCount != expectedModCount) {
            //throw new ConcurrentModificationException();
            //}

            //集合在循环的时候不能对原来的集合大小进行修改，不然会抛ConcurrentModificationException
            for (Integer integer : list) {
                list.remove(2);
            }
        } catch (Exception e) {
            log.error("ConcurrentModificationException" + e);
        }


        List<String> listToArray = new ArrayList<String>(2);
        listToArray.add("guan");
        listToArray.add("bao");
        String[] array1 = new String[listToArray.size()];
        log.info(array1 + "");

        array1 = listToArray.toArray(array1);
        log.info(array1 + "");
        try {
            //无参数这种toArray 会抛异常ClassCastException
            String[] dd = (String[]) listToArray.toArray();
            log.info(dd + "");
        } catch (Exception e) {
            log.error("ClassCastException" + e);
        }

    }
}
