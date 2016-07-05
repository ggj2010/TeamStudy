package com.team.gaoguangjin.设计模式.工厂模式.抽象工厂模式.Product.usa;


import com.team.gaoguangjin.设计模式.工厂模式.抽象工厂模式.Product.product.Food;

/**
 * @author:gaoguangjin
 * @date 2016/5/6 11:24
 */
public class USAFood implements Food {
    @Override
    public void eat() {
        System.out.println("USAFood");
    }
}