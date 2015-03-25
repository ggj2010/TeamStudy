package com.team.gaoguangjin.javabase.collection.list;

import java.util.ArrayList;
import java.util.List;

public class ListSwitch {
	List list0 = new ArrayList();
	List list1 = new ArrayList();
	List list2 = new ArrayList();
	List list3 = new ArrayList();
	List list4 = new ArrayList();
	List list5 = new ArrayList();
	List list6 = new ArrayList();
	List list7 = new ArrayList();
	List list8 = new ArrayList();
	List list9 = new ArrayList();
	
	public void listCut(List sourceList) {
		for (int i = 0; i < sourceList.size(); i++) {
			int y = i % 10;
			switch (y) {
				case 0:
					list0.add(sourceList.get(i));
					break;
				case 1:
					list1.add(sourceList.get(i));
					break;
				case 2:
					list2.add(sourceList.get(i));
					break;
				case 3:
					list3.add(sourceList.get(i));
					break;
				case 4:
					list4.add(sourceList.get(i));
					break;
				case 5:
					list5.add(sourceList.get(i));
					break;
				case 6:
					list6.add(sourceList.get(i));
					break;
				case 7:
					list7.add(sourceList.get(i));
					break;
				case 8:
					list8.add(sourceList.get(i));
					break;
				case 9:
					list9.add(sourceList.get(i));
					break;
			}
		}
	}
}
