package com.team.gaoguangjin.设计模式.装饰者模式.normal;

public class ImgScanerReader extends SimpleImage {
	
	public ImgScanerReader(ImgeDarwReader ir) {
		simpleImage = ir;
	}
	
	@Override
	void render() {
		simpleImage.render();
		System.out.println("图片扫描处理");
	}
	
}
