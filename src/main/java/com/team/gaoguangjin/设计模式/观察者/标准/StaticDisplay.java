package com.team.gaoguangjin.设计模式.观察者.标准;

public class StaticDisplay implements Observer, DisplayElement {
	private float temperature;
	private float humidity;
	private float pressure;
	private Subject weatherData;
	
	public StaticDisplay(Subject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}
	
	@Override
	public void update(float temp, float humidity, float pressure) {
		this.temperature = temp;
		this.humidity = humidity;
		this.pressure = pressure;
		display();
	}
	
	@Override
	public void display() {
		System.out.println("static公告板：观察者:" + temperature);
		
	}
	
}
