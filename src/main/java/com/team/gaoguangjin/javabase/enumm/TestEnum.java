package com.team.gaoguangjin.javabase.enumm;

/**
 * @ClassName:TestEnum.java
 * @Description: 测试enum
 * @author gaoguangjin
 * @Date 2015-3-24 下午9:52:01
 */
public class TestEnum {
	public static void main(String[] args) {
		// 1、常量
		System.out.println(Color.blue);
		
		// 2、switch
		switchTest();
		
		// 3.向枚举中添加新方法
		System.out.println(Apple.yellow.getName());
		
		// 4、枚举类型定义常量方法
		System.out.println(Light.yellow);
	}
	
	private static void switchTest() {
		// switch只支持 int,char,enum
		switch (Color.blue) {
			case blue:
				System.out.println("这是蓝色");
				break;
			default:
				System.out.println("默认");
		}
	}
	
	public enum Color {
		blue, yellow, white
	}
	
	public enum Apple {
		yellow("香蕉", "黄色"), green("苹果", "绿色");
		private String name;
		private String color;
		
		// 构造方法 必须要有的
		private Apple(String name, String color) {
			this.name = name;// 这个和构造方法相对应 yellow("香蕉", "黄色")
			this.color = color;
		}
		
		private String getName() {
			return name;
		}
	}
	
	public enum Light {
		// /* 红灯 */等同于这样的效果
		// public final static int RED = 1;
		green(1), yellow(2);
		private int code;
		
		private Light(int code) {
			this.code = code;
		}
		
		public String toString() {
			return String.valueOf(this.code);
		}
	}
}
