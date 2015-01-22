package com.team.gaoguangjin.help;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String strUrl = "jdbc:oracle:thin:@z:1521:ZSORA10G";
	
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
	
}
