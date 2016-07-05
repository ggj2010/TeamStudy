package com.team.gaoguangjin.设计模式.工厂模式.抽象工厂模式.Product.china;


import com.team.gaoguangjin.设计模式.工厂模式.抽象工厂模式.Product.product.Food;

/**
 * @author:gaoguangjin
 * @date 2016/5/6 11:23
 */
public class ChinaFood implements Food {
    @Override
    public void eat() {
        System.out.println("ChinaFood");
    }
}
