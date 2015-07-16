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
import com.team.gaoguangjin.jdbc.mybatis.mapper.ClassRoomMapper;
import com.team.gaoguangjin.jdbc.mybatis.mapper.StudentMapper;

/**
 * @ClassName:MyBatisDML.java
 * @Description: mybatis的字符串差改和多表功能  
 * @author gaoguangjin
 * @Date 2015-7-15 下午4:30:40
 */
@Slf4j
public class MyBatisDML {
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
	public void select() {
		SqlSession session = sessionFactory.openSession();
		ClassRoomMapper mapper = session.getMapper(ClassRoomMapper.class);
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		
		/*****查询*****/
		findSingle(mapper);
		// hibernate一般用的就是多对一。学生对应多个老师
		findOneTomanly(studentMapper);
		findManlyToOne(studentMapper);
		session.close();
	}
	
	/**
	 * @Description:  
	 * @return:void
	 */
	@Test
	public void insert() {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			ClassRoomMapper mapper = session.getMapper(ClassRoomMapper.class);
			
			ClassRoom cr = new ClassRoom();
			cr.setRoomName("新增");
			cr.setRoomSize("100");
			mapper.insert1(cr);
		} catch (Exception e) {
			log.error("插入失败！" + e.getLocalizedMessage());
		}
		finally {
			// 需要提交事务
			session.commit();
			session.close();
		}
	}
	
	/**
	 * @Description:  
	 * @param studentMapper     
	 * @return:void
	 * 
	 */
	private void findManlyToOne(StudentMapper studentMapper) {
		Subject subject = studentMapper.selectSubjectById("1");
		System.out.println("subject 一对多" + subject.toString());
	}
	
	private void findOneTomanly(StudentMapper mapper) {
		Student student = mapper.selectStudentById("1");
		log.info("student 多对一" + student.toString());
	}
	
	private void findSingle(ClassRoomMapper mapper) {
		// 第一种方式 带include 、parameterType 和resultType resulttype对应的是config里面配置的typeAlias
		ClassRoom object1 = mapper.get1("1");
		
		// 没有配置column到bean的映射关系，所以room_name和 room_size的值为空
		ClassRoom object2 = mapper.get2("1");
		
		// 第三种方式，resultMap 没有带参数 parameterType
		ClassRoom object3 = mapper.get3("1");
		
		log.info("object1" + object1.toString());
		log.info("object2:" + object2.toString());
		log.info("object3:" + object3.toString());
		
	}
	
}
