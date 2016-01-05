package com.team.gaoguangjin.javabase.callback.normal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class B {
	
	public static void main(String[] args) {
		B b = new B();
		ACallBack a = new A();
		// b中调用A
		b.execute(a);
	}
	
	private void execute(ACallBack a) {
		getConnection();
		a.doCRUD();
		releaseConnection();
	}
	
	public void getConnection() {
		log.info("获得连接...");
	}
	
	public void releaseConnection() {
		log.info("释放连接...");
	}
	
}
