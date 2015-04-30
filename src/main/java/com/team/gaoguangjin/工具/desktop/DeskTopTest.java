package com.team.gaoguangjin.工具.desktop;

import java.awt.Desktop;
import java.net.URL;

/**
 * @ClassName:DeskTopTest.java
 * @Description: deskTopTest
 * @author gaoguangjin
 * @Date 2015-4-27 上午10:27:31
 */
public class DeskTopTest {
	public static void main(String[] args) {
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop desk = Desktop.getDesktop();
				// 打开文件
				// desk.open(new File("resource/jietu/tiantian.jpg"));
				// 编辑文件
				// desk.edit(new File("resource/jietu/desktop.txt"));
				// 打印文件
				// desk.print(new File(""));
				// 打开浏览器网页
				desk.browse(new URL("http://www.baidu.com").toURI());
				// 发邮件
				desk.mail(new URL("335424093@qq.com").toURI());
			}
			
		} catch (Exception e) {
			
		}
	}
}
