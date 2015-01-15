package com.team.gaoguangjin.test;

import lombok.extern.slf4j.Slf4j;

import com.alibaba.fastjson.JSONObject;

@Slf4j
public class Test {
	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		
		JSONObject people = new JSONObject();
		
		for (int i = 0; i < 3; i++) {
			JSONObject js = new JSONObject();
			if (i == 0) {
				js.put("age", "24");
				js.put("name", "gaoguangjin");
				js.put("sex", "man");
				people.put("高广金", js);
			} else if (i == 1) {
				js.put("age", "24");
				js.put("name", "jiangtao");
				js.put("sex", "man");
				people.put("蒋涛", js);
			} else if (i == 2) {
				js.put("age", "24");
				js.put("name", "yuyong");
				js.put("sex", "man");
				people.put("余勇", js);
			}
		}
		
		json.put("人员", people);
		json.put("总人数", 3);
		
		System.out.println(json.toJSONString());
	}
}
