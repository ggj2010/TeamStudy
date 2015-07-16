package com.team.gaoguangjin.javaUtil.xml;

import java.io.FileNotFoundException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlToBean {
	public static void main(String[] args) throws FileNotFoundException {
		
		XStream xs = new XStream(new DomDriver());
		
		xs.alias("food", Bean.class);
		Bean bean = (Bean) xs.fromXML(XmlToBean.class.getResourceAsStream("bean.xml"));
		System.out.println(bean);
		
	}
}
