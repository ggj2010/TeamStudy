package com.team.gaoguangjin.缓存.redis.util;

import com.team.youdao.bean.Note;

public class ServiceDao {
	
	public void getServiceBean(String param) {
		// select * from table where id=?
		// Bean bean=jdbcDao.get();
		
		// 组合参数
		String key = "Note:" + param;
		
		NoteDao noteDao = new NoteDao();
		Note aa = noteDao.getBean("key");
	}
}
