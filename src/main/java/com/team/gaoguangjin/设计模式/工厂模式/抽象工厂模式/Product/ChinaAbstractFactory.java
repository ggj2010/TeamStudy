package com.team.gaoguangjin.设计模式.工厂模式.抽象工厂模式.Product;

import com.team.gaoguangjin.设计模式.工厂模式.抽象工厂模式.AbstractFactory;
import com.team.gaoguangjin.设计模式.工厂模式.抽象工厂模式.Product.china.ChinaFood;
import com.team.gaoguangjin.设计模式.工厂模式.抽象工厂模式.Product.china.ChinaVehicle;
import com.team.gaoguangjin.设计模式.工厂模式.抽象工厂模式.Product.product.Food;
import com.team.gaoguangjin.设计模式.工厂模式.抽象工厂模式.Product.product.Vehicle;

/**
 * @author:gaoguangjin
 * @date 2016/5/6 11:43
 */
public class ChinaAbstractFactory extends AbstractFactory{

    @Override
    public Vehicle createVehicle() {
        return new ChinaVehicle();
    }

    @Override
    public Food createFood() {
        return new ChinaFood();
    }
}
