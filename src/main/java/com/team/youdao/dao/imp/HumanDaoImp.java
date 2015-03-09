package com.team.youdao.dao.imp;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.team.youdao.base.BaseDao;
import com.team.youdao.bean.Human;
import com.team.youdao.dao.HumanDao;

@Repository
public class HumanDaoImp extends BaseDao<Human> implements HumanDao {
	
	public void saveHuman(Human human) throws Exception {
		human.setCreatedate(new Date());
		human.setFlag(1);
		human.setHumanPassword("123");// 默认123
		human.setUpdatedate(new Date());
		getCurrentSession().save(human);
	}
}
