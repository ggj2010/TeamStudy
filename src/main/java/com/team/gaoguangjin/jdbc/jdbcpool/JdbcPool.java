package com.team.gaoguangjin.jdbc.jdbcpool;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * 需要用到 commons-dbcp和commons-pool 架包
 * @author gaoguangjin
 * 
 */
public class JdbcPool {
	
	private static BasicDataSource basicDataSource = null;
	static {
		try {
			Properties properties = new Properties();
			ClassPathResource file = new ClassPathResource("jdbcconfig/jdbcpool/jdbcpool.properties");
			properties.load(new FileInputStream(file.getFile()));
			
			basicDataSource = (BasicDataSource) BasicDataSourceFactory.createDataSource(properties);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = basicDataSource.getConnection();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void main(String[] args) throws SQLException {
		PreparedStatement ps = basicDataSource.getConnection().prepareStatement("select sysdate from dual");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("sysdate"));
		}
		
	}
}
