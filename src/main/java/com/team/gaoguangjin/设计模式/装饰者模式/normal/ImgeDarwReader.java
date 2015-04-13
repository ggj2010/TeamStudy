package com.team.gaoguangjin.设计模式.装饰者模式.normal;

public class ImgeDarwReader extends SimpleImage {
	
	public ImgeDarwReader(SimpleImage ir) {
		simpleImage = ir;
	}
	
	@Override
	void render() {
		simpleImage.render();
		System.out.println("图片花线处理");
	}
	
}
