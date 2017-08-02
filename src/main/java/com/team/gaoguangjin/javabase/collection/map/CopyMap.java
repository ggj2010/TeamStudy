package com.team.gaoguangjin.javabase.collection.map;

import java.util.HashMap;
import java.util.Map;

import com.team.gaoguangjin.javabase.servlet.ioc.enty.Student;

import lombok.extern.slf4j.Slf4j;

/**
 * map的拷贝
 * map.clone()
 * 如果map的key 是引用类型，若不对clone()方法进行改写，则调用此方法得到的对象即为浅拷贝
 *
 * @author:gaoguangjin
 * @date 2017/3/14 13:43
 */
@Slf4j
public class CopyMap {


    public static void main(String[] args) {
        copy();
    }

    private static void copy() {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "a");
//        Map<String, String> map2 = (Map<String, String>) map.clone();
        Map<String, String> map2 = new HashMap<>();
        map2.putAll(map);
        map2.put("1","bb");
        map2.put("2","cc");

        log.info(map2.toString());
        log.info("map深拷贝基本类型属性和String类型属性 map1 {}  map2 {}", map.get("1"), map2.get("1"));
        log.info("map深拷贝基本类型属性和String类型属性 map1 size={}  map2 size={}", map.size(),map2.size());

        /*-------------------浅拷贝key的对象值--------------*/
     /*   HashMap<String, Student> studentMap = new HashMap<>();
        Student st = new Student();
        st.setStudentName("gao");
        studentMap.put("1", st);

        HashMap<String, Student> cloneMap = (HashMap<String, Student>) studentMap.clone();
        //对st的修改也会印象到cloneMap 因为他们都浅拷贝 都是引用同一个对象
        st.setStudentName("gao-update");

        log.info("map潜拷贝引用属性 map1 size:{}  map2 size:{}", studentMap.get("1").getStudentName(), cloneMap.get("1").getStudentName());
*/
        /*  -------------------深拷贝key的对象值--------------*/

  /*      HashMap<String, StudentClone> studentMap2 = new HashMap<>();
        StudentClone st2 = new StudentClone();
        st2.setStudentName("gao");
        studentMap2.put("1", st2);


        HashMap<String, StudentClone> cloneMap2 = (HashMap<String, StudentClone>) studentMap2.clone();

        //对st的修改也会印象到cloneMap 因为他们都浅拷贝 都是引用同一个对象
        st2.setStudentName("gao-update");
        st2.setStudentName("gao-update234");

        cloneMap2.get(1).setStudentName("dddddd");
        log.info("map深拷贝引用属性 map1 size:{}  map2 size:{}", studentMap2.get("1").getStudentName(), cloneMap2.get("1").getStudentName());*/

    }


    public static class StudentClone implements Cloneable {
        private String studentName;

        //重写Cloneable接口的clone()方法
        public Object clone() {
            StudentClone student = null;
            try {
                student = (StudentClone) super.clone();//将一个实例克隆，并抛出异常
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return student;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }
    }
}
