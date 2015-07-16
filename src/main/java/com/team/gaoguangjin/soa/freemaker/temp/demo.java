package com.team.gaoguangjin.soa.freemaker.temp;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.team.gaoguangjin.soa.freemaker.test.Column;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class demo {
	public static void main(String[] args) throws Exception {
		InputStream inputStream = demo.class.getClassLoader().getResourceAsStream("com/team/gaoguangjin/soa/freemaker/temp/${className}.txt");
		
		demo1(inputStream);
		
	}
	
	private static void demo1(InputStream inputStream) throws Exception {
		StringWriter result = new StringWriter();
		// fk的配置
		// Configuration cfg = new Configuration();
		Template t = new Template("name", new InputStreamReader(inputStream), new Configuration());
		
		// 步骤三：创建 数据模型
		Map model = createDataModel();
		t.process(model, result);
		//
		System.out.println(result);
		
	}
	
	private static Map createDataModel() {
		Map map = new HashMap();
		Column cl1 = new Column();
		Column cl2 = new Column();
		List listColumn = new ArrayList();
		
		cl1.setName("name");
		cl1.setType("String");
		
		cl2.setName("age");
		cl2.setType("int");
		
		listColumn.add(cl1);
		listColumn.add(cl2);
		
		map.put("package", "com.team.gaoguangjin.soa.freemaker.test");
		map.put("className", "TempClass");
		map.put("listColumn", listColumn);
		return map;
	}
}
