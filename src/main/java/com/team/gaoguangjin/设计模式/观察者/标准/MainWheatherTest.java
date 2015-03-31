package com.team.gaoguangjin.设计模式.观察者.标准;

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
