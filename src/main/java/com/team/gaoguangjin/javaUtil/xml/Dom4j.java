package com.team.gaoguangjin.javaUtil.xml;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4j {
	public static void main(String[] args) {
		String url = "src/main/resources/testxml/dom.xml";
		parseXml(url);
	}
	
	private static void parseXml(String url) {
		try {
			SAXReader sr = new SAXReader();
			Document document = sr.read(new File(url));
			Element element = document.getRootElement();
			// System.out.println(element.getName());
			
			parseXmlByElement(element);
			
		}
		catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void parseXmlByElement(Element element) {
		Iterator iterator = element.elementIterator();
		while (iterator.hasNext()) {
			Element ele = (Element) iterator.next();
			System.out.println(ele.elementText("name"));
			System.out.println(ele.elementText("price"));
			System.out.println(ele.elementText("description"));
			System.out.println(ele.elementText("calories"));
		}
	}
}
