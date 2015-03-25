package com.team.gaoguangjin.深入java虚拟机.内存泄露;

import java.util.ArrayList;

public class MyList extends ArrayList {
	public void finalize() {
		System.out.println("Release resource ");
	}
}

class MyObject {
	int i = 0;
	
	public MyObject(int i) {
		this.i = i;
	}
	
	public void finalize() {
		System.out.println("MyObject " + this.i + " Release ");
	}
}