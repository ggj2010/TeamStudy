package com.team.gaoguangjin.设计模式.观察者.标准;

/**
 * @ClassName:MainWheatherTest.java
 * @Description: 天气的变化是被观察者，面板是观察者。布板不同，因为不同的布板有不同的展示内容
 * @author gaoguangjin
 * @Date 2015-4-10 上午9:38:24
 */
public class MainWheatherTest {
	public static void main(String[] args) {
		WeatherDat weather = new WeatherDat();
		CurrentConditionDisplay ccd = new CurrentConditionDisplay(weather);
		
		StaticDisplay sd = new StaticDisplay(weather);
		
		// 当被观察者发生变化时候
		weather.setMeasurements(1, 2, 3);
		
		 ccd.update(12, 12, 100);
	}
}
