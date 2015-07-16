package com.team.gaoguangjin.test.list;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {
	public static void main(String[] args) {
		List<Bean> list = new ArrayList<Bean>();
		Bean b = new Bean();
		b.setName("高");
		
		list.add(b);
		
		Bean bb = list.get(0);
		bb.setName("广");
		
		System.out.println(list.get(0).getName());
		
	}
}
