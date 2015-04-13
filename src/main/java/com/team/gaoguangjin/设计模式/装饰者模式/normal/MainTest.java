package com.team.gaoguangjin.设计模式.装饰者模式.normal;

/**
 * @ClassName:MainTest.java
 * @Description: 测试装饰着模式。公共的抽象类，然后在构造方法里面放着抽象类的对象，公共的方法里面调用别的方法
 * @author gaoguangjin
 * @Date 2015-4-10 上午10:24:29
 */
public class MainTest {
	public static void main(String[] args) {
		ImgScanerReader isr = new ImgScanerReader(new ImgeDarwReader(new ImageReaderHelper()));
		
		isr.render();
	}
}
