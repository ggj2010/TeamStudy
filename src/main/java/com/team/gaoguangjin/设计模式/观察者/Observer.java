package com.team.gaoguangjin.设计模式.观察者;

import java.util.ArrayList;
import java.util.List;

public class Observer {
	List<PeopleInterface> list = new ArrayList<PeopleInterface>();
	
	public void add(PeopleInterface peopleInterface) {
		list.add(peopleInterface);
		notice("添加新的观察者了噢！" + peopleInterface.getName());
	}
	
	public void notice(String message) {
		System.out.println("ddd" + list.size());
		for (PeopleInterface people : list) {
			people.notice(message);
		}
	}
	
	public void remove(PeopleInterface peopleInterface) {
		
	}
	
}
