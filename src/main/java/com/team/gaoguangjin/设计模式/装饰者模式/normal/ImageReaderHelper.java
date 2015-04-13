package com.team.gaoguangjin.设计模式.装饰者模式.normal;

public class ImageReaderHelper extends SimpleImage implements ReadRender {
	
	@Override
	void render() {
		System.out.println("开始处理图片");
	}
	
}
