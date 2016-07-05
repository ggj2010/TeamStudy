package com.team.gaoguangjin.设计模式.工厂模式.工厂方法模式;


/**
 * 工厂方法模式是简单工厂模式的进一步抽象化和推广，工厂方法模式里不再只由一个工厂类决定那一个产品类应当被实例化,这个决定被交给抽象工厂的子类去做。
 * 抽象工厂
 * @author:gaoguangjin
 * @date 2016/5/6 9:28
 */
public abstract class AbstractRegistryFactory {
    public abstract Registry create();
}
