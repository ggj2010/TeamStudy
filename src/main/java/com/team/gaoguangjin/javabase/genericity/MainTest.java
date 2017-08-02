package com.team.gaoguangjin.javabase.genericity;


/**
 * @author:gaoguangjin
 * @date 2017/6/21 13:41
 */
public class MainTest {
    //频繁往外读取内容的，适合用上界Extends。
//    经常往里插入的，适合用下界Super。
    public static void main(String[] args) {
        //这种写法不允许
        //装苹果的盘子 NOT-IS-A 装水果的盘子
        //所以，就算容器里装的东西之间有继承关系，但容器之间是没有继承关系的。所以我们不可以把Plate的引用传递给Plate
      //  Plate<Fruit> fruitPlate=new Plate<Apple>(new Apple());
        //上界通配符 <? extends Fruit>
        Plate<? extends Fruit> fruitPlate=new Plate<Apple>(new Apple());

        //不能存入任何元素
       // fruitPlate.set(new Apple());
        fruitPlate.get();

        Plate<? super Fruit> superFruitPlate=new Plate<Fruit>(new Apple());
        //存入元素正常
        superFruitPlate.set(new Fruit());
        //读取出来的东西只能存放在Object类里。
       // Apple newFruit3=superFruitPlate.get();

    }
}
