package com.team.gaoguangjin.javabase.collection.set;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;


/**
 * @author:gaoguangjin
 * @date 2017/6/15 17:36
 */
@Slf4j
public class SetKey {

    public static void main(String[] args) {
//        Student student1 = new Student("a");
//        Student student2 = new Student("a");
//
//        Set<Student> set=new HashSet<>();
//        set.add(student1);
//        set.add(student2);
//        log.info(set.size()+"");





        // SET 里面的key 判断相同必须要重写equal和hashCode()方法
        StudentOverride student11 = new StudentOverride("a");
        StudentOverride student22 = new StudentOverride("a");
        Set<StudentOverride> sett=new HashSet<>();
        sett.add(student11);
        sett.add(student22);
        log.info(sett.size()+"");
    }

    @Getter
    @Setter
    static class Student {
        String name;

        public Student(String name) {
            this.name = name;
        }
    }
    @Getter
    @Setter
    static class StudentOverride {
        String name;

        public StudentOverride(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StudentOverride that = (StudentOverride) o;
            return name != null ? name.equals(that.name) : that.name == null;

        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }
    }
}
