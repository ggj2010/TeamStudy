package com.team.gaoguangjin.javabase.callback;

public class HibernateTemplate {
	public void execute(CallBack action) {
		getConnection();
		action.doCRUD();
		releaseConnection();
	}
	
	public void add() {
		execute(new CallBack() {
			public void doCRUD() {
				System.out.println("执行add操作...");
			}
		});
	}
	
	public void delete() {
		execute(new CallBack() {
			public void doCRUD() {
				System.out.println("执行delete操作...");
			}
		});
	}
	
	public void getConnection() {
		System.out.println("获得连接...");
	}
	
	public void releaseConnection() {
		System.out.println("释放连接...");
	}
}
