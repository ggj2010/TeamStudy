package com.team.gaoguangjin.jdbc.druidpool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

/**
 * druid数据库连接池相关知识，properties，map ,传统的赋值，spring
 * @author gaoguangjin date :2015-02-05
 */
@Slf4j
public class DruidPoolTest {
	
	/**
	 * 最传统的赋值数据库连接池
	 * @throws SQLException
	 */
	@Test
	public void testByNormal() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		DruidPooledConnection connection = null;
		try {
			dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:ZSORA10G");
			dataSource.setUsername("gao");
			dataSource.setPassword("admin");
			connection = dataSource.getConnection();
			test(connection, "传统的jdbc,手动set值");
		}
		catch (SQLException e) {
			log.error("DruidDataSource加载失败：" + e.getLocalizedMessage());
			if (connection != null)
				connection.close();
		}
	}
	
	/**
	 * 通过获取properties得到连接,或者是map
	 * @throws SQLException
	 */
	@Test
	public void testByProperitest() throws SQLException {
		DruidDataSource dataSource = null;
		DruidPooledConnection connection = null;
		try {
			Properties properties = new Properties();
			properties.load(new ClassPathResource("/jdbcconfig/druidpool/druidpool.properties").getInputStream());
			dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
			connection = dataSource.getConnection();
			test(connection, "通过properties赋值");
			/* ========================================================= */
			
			// //通过Map作为参数获取连接
			Map<String, String> map = new HashMap<String, String>();
			// 向map中传递参数
			map.put("url", "jdbc:oracle:thin:@localhost:1521:ZSORA10G");// 数据库地址
			map.put("username", "gao");// 用户名
			map.put("password", "admin");// 密码
			map.put("driverClassName", "oracle.jdbc.driver.OracleDriver");// 类型
			test(((DruidDataSource) DruidDataSourceFactory.createDataSource(map)).getConnection(), "通过map去赋值");
			
		}
		catch (Exception e) {
			log.error("DruidDataSource加载失败：" + e.getLocalizedMessage());
			if (connection != null)
				connection.close();
		}
		
	}
	
	/**
	 * 通过springxml进行赋值
	 * @throws SQLException
	 */
	@Test
	public void testBySpringXmlJdbc() throws SQLException {
		DruidDataSource dataSource = null;
		DruidPooledConnection connection = null;
		try {
			ApplicationContext ac = new ClassPathXmlApplicationContext("jdbcconfig/druidpool/springjdbc.xml");
			dataSource = (DruidDataSource) ac.getBean("dataSource");
			connection = dataSource.getConnection();
			test(connection, "通过springXml赋值");
		}
		catch (Exception e) {
			log.error("DruidDataSource加载失败:" + e.getLocalizedMessage());
			if (connection != null)
				connection.close();
		}
		
	}
	
	/**
	 * 测试得到的连接
	 * @param connection
	 * @throws SQLException
	 */
	private void test(DruidPooledConnection connection, String tag) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("select sysdate from dual");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			log.info("输出时间：" + rs.getString("sysdate") + "=====" + tag);
		}
		rs.close();
		ps.close();
		connection.close();
	}
	
	public Connection getConnection() {
		return null;
	}
	
}
