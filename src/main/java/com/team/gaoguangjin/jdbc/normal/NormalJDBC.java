package com.team.gaoguangjin.jdbc.normal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NormalJDBC {
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String strUrl = "jdbc:oracle:thin:@localhost:1521:ZSORA10G";
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(strUrl, "gao", "admin");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * sql预处理比较性能
	 * @param conn 2015-01-14 19:06:14,741 INFO [main] (DataBaseUtil.java:52) - 立即查询 Statement耗费时间：2033 2015-01-14
	 * 19:06:14,857 INFO [main] (DataBaseUtil.java:71) - 立即查询 prepareStatement耗费时间：114
	 */
	private static void doTestSelect(Connection conn) {
		try {
			long begintime = System.currentTimeMillis();
			for (int i = 0; i < 200; i++) {
				// 立即查询
				Statement statement = conn.createStatement();
				ResultSet rsStatement = statement.executeQuery("select id from gao.tccity where 5=" + i);
				rsStatement.close();
			}
			long endTime = System.currentTimeMillis() - begintime;
			log.info("立即查询 Statement耗费时间：" + endTime);
			
			/** 缓存查询 **/
			long begintime2 = System.currentTimeMillis();
			for (int i = 0; i < 200; i++) {
				
				// 缓存查询，绑定变量 对sql进行预处理编译
				PreparedStatement ps = conn.prepareStatement("select id from gao.tccity where 5=?");
				ps.setInt(1, i);
				ResultSet rs = ps.executeQuery();
				// while (rs.next()) {
				// System.out.println(rs.getString("sysdate"));
				// }
				
				rs.close();
				ps.close();
			}
			
			long endTime2 = System.currentTimeMillis() - begintime2;
			log.info("立即查询 prepareStatement耗费时间：" + endTime2);
		}
		catch (Exception e) {
			
		}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			}
			catch (Exception e) {
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		doTestSelect(NormalJDBC.getConnection());
	}
}
