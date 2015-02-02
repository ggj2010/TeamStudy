package com.team.gaoguangjin.jdbc.normal;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class JunitTestStandardJDBC {
	
	@Test
	public void test() {
		
		StandardJDBC sd = new StandardJDBC();
		try {
			sd.open("localhost:1521:ZSORA10G", "gao", "admin");
			// 封装了原始的jdbc
			sd.createStatement("select * from gao.tccity");
			ResultSet rs = sd.executeSQL();
			
			// 反射赋值
			Class<?> reflectClass = Class.forName("com.team.gaoguangjin.jdbc.normal.CityBean");
			
			List<CityBean> listCityBean = new ArrayList<CityBean>();
			while (rs.next()) {
				listCityBean.add(outPutMessage(rs, reflectClass));
			}
			
			sd.commit();
			// 打印
			display(listCityBean);
		}
		catch (Exception e) {
		}
		
	}
	
	private void display(List<CityBean> listCityBean) {
		for (CityBean cityBean : listCityBean) {
			System.out.println(cityBean);
		}
		
	}
	
	public CityBean outPutMessage(ResultSet rs, Class<?> reflectClass) {
		CityBean cityBean = null;
		try {
			cityBean = (CityBean) reflectClass.newInstance();
			Field[] filedList = reflectClass.getDeclaredFields();
			for (Field field : filedList) {
				System.out.println(field.getName());
				field.set(cityBean, rs.getString(field.getName()));
			}
		}
		catch (Exception e) {
			
		}
		
		return cityBean;
		
	}
	
}
