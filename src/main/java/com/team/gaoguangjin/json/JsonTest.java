package com.team.gaoguangjin.json;

import java.util.ArrayList;
import java.util.HashMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonTest {
	// public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray
	// public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject
	// public static final T parseObject(String text, Class clazz); // 把JSON文本parse为JavaBean
	// public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
	// public static final List parseArray(String text, Class clazz); //把JSON文本parse成JavaBean集合
	// public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
	// public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
	// public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。
	
	public static void main(String[] args) {
		demo1();
		// createJson();
		// parseJson();
		
		// parseJson2();
	}
	
	private static void parseJson2() {
		// 没有[]括号,带[]的无法解析噢
		// String str1 = "[{\"address\":\"南京市游乐园\",\"city\":\"南京\",\"district\":\"玄武区\",\"province\":\"江苏\"}]";
		String str1 = "{\"address\":\"南京市游乐园\",\"city\":\"南京\",\"district\":\"玄武区\",\"province\":\"江苏\"}";
		JSONObject json = JSONObject.parseObject(str1);
		System.out.println(json.getString("address"));
	}
	
	private static void parseJson() {
		String jsonString = "{\"UserName\":\"ZHULI\",\"age\":\"30\",\"workIn\":\"ALI\",\"Array\":[\"ZHULI\",\"30\",\"ALI\"]}";
		// 将Json字符串转为java对象
		JSONObject obj = JSONObject.parseObject(jsonString);
		// 获取Object中的UserName
		if (obj.containsKey("UserName")) {
			System.out.println("UserName:" + obj.getString("UserName"));
		}
		System.out.println(obj);
		// 获取ArrayObject
		if (obj.containsKey("Array")) {
			JSONArray transitListArray = obj.getJSONArray("Array");
			for (int i = 0; i < transitListArray.size(); i++) {
				System.out.print("Array:" + transitListArray.getString(i) + " ");
			}
		}
	}
	
	private static void createJson() {
		// JsonObject和JsonArray区别就是JsonObject是对象形式，JsonArray是数组形式
		// 创建JsonObject第一种方法
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("UserName", "ZHULI");
		jsonObject.put("age", "30");
		jsonObject.put("workIn", "ALI");
		System.out.println("jsonObject1：" + jsonObject);
		
		// 创建JsonObject第二种方法
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("UserName", "ZHULI");
		hashMap.put("age", "30");
		hashMap.put("workIn", "ALI");
		System.out.println("jsonObject2：" + JSONObject.toJSONString(hashMap));
		
		// 创建一个JsonArray方法1
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(0, "ZHULI");
		jsonArray.add(1, "30");
		jsonArray.add(2, "ALI");
		System.out.println("jsonArray1：" + jsonArray);
		
		// 创建JsonArray方法2
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("ZHULI");
		arrayList.add("30");
		arrayList.add("ALI");
		System.out.println("jsonArray2：" + JSONArray.toJSONString(arrayList));
		
		// 如果JSONArray解析一个HashMap，则会将整个对象的放进一个数组的值中
		System.out.println("jsonArray FROM HASHMAP：" + JSONArray.toJSONString(hashMap));
		
		// 组装一个复杂的JSONArray
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("UserName", "ZHULI");
		jsonObject2.put("age", "30");
		jsonObject2.put("workIn", "ALI");
		jsonObject2.put("Array", arrayList);
		System.out.println("jsonObject2：" + jsonObject2);
		
	}
	
	private static void demo1() {
		String str = "{\"response\":{\"data\":[{\"address\":\"南京市游乐园\",\"province\":\"江苏\",\"district\":\"玄武区\",\"city\":\"南京\"}]},\"status\":\"ok\"}";
		JSONObject object = JSONObject.parseObject(str);
		System.out.println("【1】：" + object);
		
		JSONObject responseObject = (JSONObject) object.get("response");
		System.out.println("【2】：" + responseObject);
		
		String statusObject = object.getString("status");// 如果没有子集 了那就直接是String类型
		System.out.println("【2】：" + statusObject);
		
		// JSONObject dataObject = (JSONObject) responseObject.get("data");// 错误的格式转换
		JSONArray dataObject = (JSONArray) responseObject.get("data");// 因为格式是带"[]"的所以能用JSONObject，得用jsonArray
		System.out.println("【3】：" + dataObject);// 行不通的
		
		JSONObject dataJSonObject = (JSONObject) dataObject.get(0);// 获取数组里面的第一个。
		
		System.out.println("【4】：" + dataJSonObject);
		System.out.println("【5】：" + dataJSonObject.getString("address"));
		
	}
	// {
	// "response": {
	// "data": [
	// {
	// "address": "南京市游乐园",
	// "province": "江苏",
	// "district": "玄武区",
	// "city": "南京"
	// }
	// ]
	// },
	// "status": "ok"
	// }
}
