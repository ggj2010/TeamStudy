package com.team.gaoguangjin.设计模式.工厂模式.工厂方法模式;

/**
 * @author:gaoguangjin
 * @date 2016/5/6 9:36
 */
public class RedisRegistryFactory extends  AbstractRegistryFactory{
    @Override
    public Registry create() {
        System.out.println("创建 RedisRegistry");
        return new RedisRegistry();
    }
}