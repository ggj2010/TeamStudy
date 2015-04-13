package com.team.gaoguangjin.test;

import com.alibaba.fastjson.JSONObject;
import com.team.gaoguangjin.jdbc.hibernate.bean.Student;

public class JsonTest {
	// public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray
	// public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject
	// public static final T parseObject(String text, Class clazz); // 把JSON文本parse为JavaBean
	// public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
	// public static final List parseArray(String text, Class clazz); //把JSON文本parse成JavaBean集合
	// public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
	// public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
	// public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。
	
	/**
	 * @Description:
	 * @param args
	 * @return:void
	 */
	public static void main(String[] args) {
		
		Student st = new Student();
		st.setId("id1");
		st.setStudentName("学生1");
		
		String string = JSONObject.toJSONString(st);
		
		System.out.println(string);
		
	}
}
