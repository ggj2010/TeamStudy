package com.team.youdao.dao.imp;

import java.io.File;
import java.io.FileInputStream;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.team.youdao.base.BaseDao;
import com.team.youdao.bean.Human;
import com.team.youdao.dao.ExceptionDao;

@Repository
@Slf4j
public class ExceptionDaoImp extends BaseDao<Human> implements ExceptionDao {
	
	public void getData() {
		try {
			Human human = new Human();
			human.setHumanName("getData");
			human.setFlag(1);
			human.setHumanPassword("getData");
			getCurrentSession().save(human);
		} catch (Exception e) {
			// 捕获不了这些事物的异常,不会catch到因为事物设置readonly的异常
			log.error("get方法只是readonly 不能修改：" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * 非运行时异常捕获：FileInputStream：ddddddddd (系统找不到指定的文件。) 异常可以捕获，但是事物不会回滚
	 */
	public void saveDataWithcheckedExcepton() {
		try {
			Human human = new Human();
			human.setFlag(1);
			human.setHumanName("saveDataWithcheckedExcepton");
			getCurrentSession().save(human);
			FileInputStream fis = new FileInputStream(new File("ddddddddd"));// 非运行时异常
		} catch (Exception e) {
			log.error("saveDataWithcheckedExcepton  FileInputStream：" + e.getLocalizedMessage());
		}
	}
	
	public void saveDataWihtUnCheckedException() {
		Human human = new Human();
		human.setFlag(1);
		human.setHumanPassword("修改后的密码");// 默认123
		human.setHumanName("saveDataWihtUnCheckedException");
		getCurrentSession().save(human);
		Integer.parseInt("abcd");// 运行时异常
	}
	
	/**
	 * 事物测试回滚：not-null property references a null or transient value
	 */
	public void saveDataWithNull() {
		try {
			Human human = new Human();
			human.setHumanName("saveDataWithNull");
			// human.setFlag(1);//缺少这个
			getCurrentSession().save(human);
		} catch (Exception e) {
			log.error("saveDataWithNull：" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * 正常保存：可以插入
	 */
	public void saveData() {
		try {
			Human human = new Human();
			human.setHumanName("saveData");
			human.setFlag(1);
			human.setHumanPassword("saveData");
			getCurrentSession().save(human);
		} catch (Exception e) {
			log.error("saveData：" + e.getLocalizedMessage());
		}
	}
	
}
