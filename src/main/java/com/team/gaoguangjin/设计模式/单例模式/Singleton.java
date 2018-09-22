package com.team.gaoguangjin.设计模式.单例模式;


/**
 * @author:gaoguangjin
 * @date 2016/9/20 11:06
 */
public class Singleton {
    /**
     * 私有化构造器
     */
    private Singleton(){};


    /**
     * 饿汉模式
     */
    private static Singleton singleton=new Singleton();
    public static synchronized Singleton getSingleton(){
        return  singleton;
    }

    /**
     * 懒汉模式
     */
    private static Singleton singleton2;
    public static synchronized  Singleton getSingleton2(){
        if(singleton2==null){
            singleton2=new Singleton();
        }
        return  singleton2;
    }

    /**
     * douleCheck
     */

    private static volatile Singleton singleton3;
    public static Singleton getSingleton3(){
        if(singleton3==null){
           synchronized (Singleton.class){
               if(singleton3==null){
                   //非原子操作,可能导致指令重新排序
                   singleton3=new Singleton();
               }
           }
        }
        return  singleton3;
    }

    /**
     * 匿名类
     */
    private static class SingletonHolder{
       private static final   Singleton singleton=new Singleton();
    }

    public static Singleton getSingleton4(){
        return  SingletonHolder.singleton;
    }

}
