package com.team.gaoguangjin.jdbc.springJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Slf4j
public class SpringJdbc {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("jdbcconfig/springjdbc/springjdbc.xml");
		testDriverManagerDataSource(ac);
		
		Resource resource = new ClassPathResource("jdbcconfig/springjdbc/springjdbc.xml");
		BeanFactory beanFactory = new XmlBeanFactory(resource);
		
		// testDriverManagerDataSource2(beanFactory);
	}
	
	private static void testDriverManagerDataSource2(BeanFactory beanFactory) {
		DriverManagerDataSource driver = (DriverManagerDataSource) beanFactory.getBean("dataSource");
		try {
			PreparedStatement ps = driver.getConnection().prepareStatement("select sysdate from dual");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("sysdate"));
			}
		}
		catch (SQLException e) {
			log.error("spring testDriverManagerDataSource()方法查询异常！" + e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * <!-- 纯普通的jdbc,用到的是DriverManagerDataSource，没意思啊 -->
	 */
	public static void testDriverManagerDataSource(ApplicationContext ac) {
		DriverManagerDataSource driver = (DriverManagerDataSource) ac.getBean("dataSource");
		try {
			PreparedStatement ps = driver.getConnection().prepareStatement("select sysdate from dual");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("sysdate"));
			}
		}
		catch (SQLException e) {
			log.error("spring testDriverManagerDataSource()方法查询异常！" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * 注意点：XmlBeanFactory 获取数据库连接，如果applicationContext.xml配置的数据库源的信息是写死的，不会出错
	 * 如果是读取jdbc.properties里面的数据，根据<context:property-placeholder location="classpath:jdbc.properties" /> 扫描到jdbc
	 * 然后<property name="driverClassName" value="${jdbc.driverClassName}"/> <property name="url" value="${jdbc.url}"/>
	 * <property name="username" value="${jdbc.username}"/> <property name="password" value="${jdbc.password}"/>
	 * 这样写就会报错，原因是在读取xml的时候，不会独立读取jdbc.properties里面的值
	 * 
	 */
	
}
