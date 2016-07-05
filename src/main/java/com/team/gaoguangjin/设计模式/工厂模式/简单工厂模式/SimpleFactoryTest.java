package com.team.gaoguangjin.设计模式.工厂模式.简单工厂模式;

/**
 * @author:gaoguangjin
 * @date 2016/5/6 11:18
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        JDBCFactory.getJDBC("mysql").connection();
    }
}
