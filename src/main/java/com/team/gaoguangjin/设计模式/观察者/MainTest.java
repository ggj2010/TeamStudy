package com.team.gaoguangjin.设计模式.观察者;

public class MainTest {
	public static void main(String[] args) {
		Observer ob = new Observer();
		PeopleInterface peopleInterface = new People1("观察者1");
		PeopleInterface peopleInterface2 = new People2("观察者2");
		ob.add(peopleInterface);
		ob.add(peopleInterface2);
	}
}
