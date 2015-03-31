package com.team.gaoguangjin.设计模式.观察者.标准;

/**
 * @ClassName:Subject.java
 * @Description: 实现气象站,有多个布告板的观察者
 * @author gaoguangjin
 * @Date 2015-3-27 下午3:24:32
 */
public interface Subject {
	void registerObserver(Observer o);
	
	void removerObserver(Observer o);
	
	void notifyObserver();
}
