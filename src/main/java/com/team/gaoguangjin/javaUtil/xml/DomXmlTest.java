package com.team.gaoguangjin.javaUtil.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import lombok.extern.slf4j.Slf4j;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * 学习使用dom解析xml
 * @author gaoguangjin
 * 
 */
@Slf4j
public class DomXmlTest {
	public static void main(String[] args) {
		String path = "resources/testxml/dom.xml";
		test1(path);
	}
	
	/**
	 * 得到document对象的输入流，并且解析
	 * @param path
	 */
	private static void test1(String path) {
		try {
			// 1、得到dom解析器工厂的示例
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// 2、dom工厂得到dom解析器
			DocumentBuilder bulider = dbf.newDocumentBuilder();
			// 3、得到document文本对象
			// 3.1直接得到document对象
			// Document document = bulider.newDocument();
			// 3.2根据输入流得到文本对象
			Document document = bulider.parse(new File(path));
			
		}
		catch (ParserConfigurationException e) {
		}
		catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
