package com.team.gaoguangjin.设计模式.工厂模式.简单工厂模式;

/**
 * 简单工厂  作为一个最基本和最简单的设计模式，简单工厂模式却有很非常广泛的应用，我们这里以Java中的JDBC操作数据库为例来说明
 * @author:gaoguangjin
 * @date 2016/5/6 9:46
 */
public class JDBCFactory {

    public static  JDBC getJDBC(String type){
        if(type.equalsIgnoreCase("mysql"))return new MysqJDBC();
        if(type.equalsIgnoreCase("oracl"))return new OraclJDBC();
        return null;
    }

}
