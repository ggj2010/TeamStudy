package com.team.gaoguangjin.设计模式.工厂模式.抽象工厂模式;

import com.team.gaoguangjin.设计模式.工厂模式.抽象工厂模式.Product.ChinaAbstractFactory;

/**
 * （1）简单工厂模式是由一个具体的类去创建其他类的实例，父类是相同的，父类是具体的。
 （2）工厂方法模式是有一个抽象的父类定义公共接口，子类负责生成具体的对象，这样做的目的是将类的实例化操作延迟到子类中完成。
 （3）抽象工厂模式提供一个创建一系列相关或相互依赖对象的接口，而无须指定他们具体的类。它针对的是有多个产品的等级结构。而工厂方法模式针对的是一个产品的等级结构。
 *  在只有一个产品族的情况下，抽象工厂模式实际上退化到工厂方法模式。
 * @author:gaoguangjin
 * @date 2016/5/6 12:16
 */
public class TestAbstractFactory {
    public static void main(String[] args) {
        AbstractFactory factory=new ChinaAbstractFactory();
        factory.createFood().eat();
        factory.createVehicle().buy();
    }
}
