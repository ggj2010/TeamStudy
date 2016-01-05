package com.team.gaoguangjin.javabase.callback;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

@Slf4j
public class TestCallBack {
	@Test
	public void test() {
		HibernateTemplate ht = new HibernateTemplate();
		ht.execute(new CallBack() {
			public void doCRUD() {
				log.info("执行自定义的docrud");
			}
		});
	}
}
