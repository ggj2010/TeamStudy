package com.team.gaoguangjin.jdbc.transaction.cglib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

import com.team.util.DateUtil;

/**
 * @ClassName:TransactionDao.java
 * @Description: 被代理类，代理事物
 * @author gaoguangjin
 * @Date 2015-3-5 下午2:40:43
 */
@Slf4j
public class TransactionDao implements TransactionInterface {
	Connection connection;
	
	public void insert() throws SQLException {
		PreparedStatement ps = connection
				.prepareStatement("insert into gao.tctransaction values(gao.sq_transaction.nextVal,?,?,to_date(?,'yyyy-MM-dd HH24:MI:SS'))");
		ps.setString(1, "姓名");
		ps.setString(2, "内容");
		ps.setString(3, DateUtil.getTime());
		ps.execute();
	}
	
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ZSORA10G", "gao", "admin");
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
		}
		return connection;
	}
	
	/**
	 * @Description: 统计有多少条数据，验证是否插入成功
	 * @see：这段关闭数据库连接的代码是标准的写法，不要忘记了
	 * @return:void
	 * @throws SQLException
	 */
	private void countMethod() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("select count(1) from gao.tctransaction");
			rs = ps.executeQuery();
			while (rs.next()) {
				log.info("目前表gao.tctransaction里面共有数据【" + rs.getInt(1) + "】条");
			}
		} catch (Exception e) {
			log.error("合计gao.tctransaction里面共有数据失败！" + e.getMessage());
		}
		finally {
			try {
				if (ps != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				log.error("关闭数据库ps,rs失败" + e.getLocalizedMessage());
			}
		}
		
	}
	
	public void insertErrow() throws SQLException {
		PreparedStatement ps = connection
				.prepareStatement("insert into gao.tctransaction values(gao.sq_transaction.nextVal,?,?,to_date(?,'yyyy-MM-dd HH24:MI:SS'))");
		ps.setString(1, "姓名");
		ps.setString(2, "内容");
		ps.setString(3, DateUtil.getTime());
		ps.setString(4, DateUtil.getTime());
		ps.execute();
	}
}
