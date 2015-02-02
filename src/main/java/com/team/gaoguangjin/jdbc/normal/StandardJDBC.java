package com.team.gaoguangjin.jdbc.normal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StandardJDBC {
	private Connection con; // Database connection object
	
	public PreparedStatement pstmt; // SQL statement object
	
	public ResultSet rs; // SQL query results
	
	private boolean IsQuery; // is a query statement
	
	private String dbHost;
	
	private String userName;
	
	private String password;
	
	private static final String JDBC_DRIVER_NAME = "oracle.jdbc.driver.OracleDriver"; // Weblogic
	
	private static final String JDBC_ORACLE = "jdbc:oracle:thin:@";
	
	public StandardJDBC() {
		rs = null;
		pstmt = null;
		IsQuery = true;
		con = null;
	}
	
	public StandardJDBC(String dbHost, String userName, String password) throws SQLException, ClassNotFoundException {
		this.dbHost = dbHost;
		this.userName = userName;
		this.password = password;
		
		this.open();
	}
	
	public void open(String dbHost, String userName, String password) throws SQLException, ClassNotFoundException {
		this.dbHost = dbHost;
		this.userName = userName;
		this.password = password;
		
		// Load the JDBdriver
		Class.forName(JDBC_DRIVER_NAME);
		
		// Connect to the database
		con = DriverManager.getConnection(JDBC_ORACLE + dbHost, userName, password);
		
		// Set auto-commit to be false
		con.setAutoCommit(false);
		
	}
	
	public void open() throws SQLException, ClassNotFoundException {
		// Load the JDBdriver
		Class.forName(JDBC_DRIVER_NAME);
		
		// Connect to the database
		con = DriverManager.getConnection(JDBC_ORACLE + dbHost, userName, password);
		
		// Set auto-commit to be false
		con.setAutoCommit(false);
	}
	
	private void checkConnection() throws ClassNotFoundException {
		try {
			if ((con == null) || con.isClosed()) {
				this.open();
			}
		}
		catch (SQLException ex) {
			System.out.println("Could not obtain status of the connection: " + ex.getMessage());
		}
		
	}
	
	/** Prepare a SQL statement */
	public void createStatement(String sqlString) throws SQLException, ClassNotFoundException {
		this.checkConnection();
		
		try {
			if (pstmt != null)
				pstmt.close();
			
			sqlString = sqlString.toUpperCase();
			if (sqlString.startsWith("SELECT "))
				IsQuery = true;
			else
				IsQuery = false;
			
			pstmt = con.prepareStatement(sqlString);
		}
		catch (SQLException e) {
			System.out.println("DBConnection timed out, createStatement() reconnected");
			
			this.open();
			this.createStatement(sqlString);
		}
	}
	
	/** Execute a SQL statement */
	public ResultSet executeSQL() throws SQLException, ClassNotFoundException {
		try {
			this.checkConnection();
			
			if (IsQuery)
				rs = pstmt.executeQuery();
			else
				pstmt.executeUpdate();
			return rs;
		}
		catch (SQLException e) {
			int err = e.getErrorCode();
			
			if (err == 0) {
				System.out.println("DBConnection timed out, executeSQL() reconnected");
				
				this.open();
				
			}
			
			throw e;
		}
	}
	
	public boolean hasMoreRow() throws SQLException {
		boolean result = false;
		if (IsQuery) {
			if (rs.next())
				result = true;
			else
				close(); // close the connection when the query is done
		}
		return result;
	}
	
	public void rollback() throws SQLException {
		if (!IsQuery)
			con.rollback();
	}
	
	public void commit() throws SQLException {
		if (!IsQuery) {
			con.commit();
			
			close();
		}
	}
	
	public void close() throws SQLException {
		if (rs != null)
			rs.close();
		
		if (pstmt != null)
			pstmt.close();
		
		if (con != null)
			con.close();
		
		rs = null;
		pstmt = null;
		con = null;
	}
	
}
