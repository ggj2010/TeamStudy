package com.team.gaoguangjin.设计模式.工厂模式.抽象工厂模式;

import com.team.gaoguangjin.设计模式.工厂模式.抽象工厂模式.Product.product.Food;
import com.team.gaoguangjin.设计模式.工厂模式.抽象工厂模式.Product.product.Vehicle;

/**
 * 在抽象工厂模式中，抽象产品 (AbstractProduct) 可能是一个或多个，从而构成一个或多个产品族(Product Family)。 在只有一个产品族的情况下，抽象工厂模式实际上退化到工厂方法模式
 * @author:gaoguangjin
 * @date 2016/5/6 11:20
 */
public abstract class AbstractFactory {
    public abstract Vehicle createVehicle();
    public abstract Food createFood();
}
