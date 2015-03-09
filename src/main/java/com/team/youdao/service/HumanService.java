package com.team.youdao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.youdao.bean.Human;
import com.team.youdao.dao.HumanDao;

/**
 * @ClassName:HumanService.java
 * @Description:人员业务层
 * @author gaoguangjin
 * @Date 2015-3-9 上午10:24:39
 */
@Service
/* 此处要增加事务的注解，以便spring在执行到这里是，将session查询注入事务的管理。 */
@Transactional
public class HumanService {
	
	@Autowired
	HumanDao humanDao;
	
	/**
	 * @Description: 保存人员
	 * @param human
	 * @return:void
	 */
	public void saveRegisterHuman(Human human) throws Exception {
		humanDao.saveHuman(human);
	}
	
}
