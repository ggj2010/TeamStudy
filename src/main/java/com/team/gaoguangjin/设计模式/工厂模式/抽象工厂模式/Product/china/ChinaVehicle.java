package com.team.gaoguangjin.设计模式.工厂模式.抽象工厂模式.Product.china;

import com.team.gaoguangjin.设计模式.工厂模式.抽象工厂模式.Product.product.Vehicle;

/**
 * @author:gaoguangjin
 * @date 2016/5/6 11:52
 */
public class ChinaVehicle implements Vehicle {
    @Override
    public void buy() {
        System.out.println("ChinaVehicle");
    }
}
