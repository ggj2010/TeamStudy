package com.team.gaoguangjin.jdbc.mybatis.normal;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.team.gaoguangjin.jdbc.mybatis.bean.ClassRoom;
import com.team.gaoguangjin.jdbc.mybatis.bean.Student;
import com.team.gaoguangjin.jdbc.mybatis.bean.Subject;
import com.team.gaoguangjin.jdbc.mybatis.mapper.StudentMapper;

/**
 * 学习mybatis相关知识
 * @author gaoguangjin
 * date 2015-02-05
 */
@Slf4j
public class TestMybatisMain {
	
	public static SqlSessionFactory sessionFactory = null;
	
	@Before
	public void init() {
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("jdbcconfig/mybatisconfig/normal/configuration.xml"));
		} catch (IOException e) {
			log.error("mybatis初始化失败：" + e.getLocalizedMessage());
		}
		
	}
	
	@Test
	public void test() {
		try {
			SqlSession session = sessionFactory.openSession();
			
			testSelectStudentById(session);// 级联查询,多对一
			testSelectClassRoomById(session);// 单个查询
			testSelectOneToMany(session);// 一对多，一个选修课程，有多个学生上。
		} catch (Exception e) {
			log.error("执行sql失败：" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * 一个课程对应多个选修学生
	 * @param session
	 */
	private void testSelectOneToMany(SqlSession session) {
		// 获取mybatis的接口类
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		Subject subject = studentMapper.selectSubjectById("1");
		System.out.println(subject.toString());
	}
	
	/**
	 * 
	 * @param session
	 */
	private void testSelectStudentById(SqlSession session) {
		// 获取mybatis的接口类
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		Student student = studentMapper.selectStudentById("1");
		System.out.println(student.toString());
	}
	
	private void testSelectClassRoomById(SqlSession session) {
		// 获取mybatis的接口类
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		ClassRoom classRoom = studentMapper.selectClassRoomById("1");
		log.info(classRoom.toString());
	}
}
