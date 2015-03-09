package com.team.gaoguangjin.jdbc.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.Test;

import com.team.util.DateUtil;

/**
 * @ClassName:WhatIsTransaction.java
 * @Description: 什么是事物，事物是干嘛的，怎么用事物
 * @author gaoguangjin
 * @Date 2015-3-5 上午9:57:59
 */
@Slf4j
public class WhatIsTransaction {
	
	public Connection connection = null;
	
	@Before
	public void before() {
		// 最原始的jdbc事物
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ZSORA10G", "gao", "admin");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() {
		// 什么是事物
		whatIsTransation();
	}
	
	/**
	 * @Description:清空表
	 * @return:void
	 */
	private void deleteAll() {
		try {
			PreparedStatement ps = connection.prepareStatement("delete gao.tctransaction");
			int m = ps.executeUpdate();
			log.info("删除【" + m + "】条数据");
		} catch (Exception e) {
			log.error("删除所有数据失败：" + e.getLocalizedMessage());
		}
		
	}
	
	/**
	 * @Description: 事物是指作为单个逻辑工作单元执行的一系列操作
	 * @see:简单理解：某个insert()方法，对数据库有一系列操作，比如有 A,B,C三个表，如果往A表插入数据的同事，也会向B表，C表插入数据。
	 * @see:如果A,B,C表一直都插入成功，那就没有必要引入事物了，如果在A,C表插入成功，B表插入失败。那么A,C表插入的记录是不是都要删掉。
	 * @see:在insert()方法里面 我们在方法头一行开启事物，如果执行过程出现错误，这一行方法的操作数据库内容都回滚。这样就保证了操作的一致性。
	 * 
	 * @return:void
	 */
	private void whatIsTransation() {
		deleteAll();// 清空表里面测试数据
		log.info("【普通的插入和更新,更新（失败）】，因为没有用到事物，所以就算更新失败，插入和更新的内容都会有");
		withoutTransation();
		log.info("===========================================无敌分割线===================================");
		deleteAll();// 清空表里面测试数据
		log.info("【用到事物的插入和更新,更新（失败）】，因为用到事物，所以最后一次更新失败，插入和更新的内容都会没有的有");
		useTransation();
	}
	
	/**
	 * @Description: 使用事物的操作
	 * @return:void
	 */
	private void useTransation() {
		try {
			connection.setAutoCommit(false);
			deleteAll();// 清空表里面测试数据
			insertMethod();// 插入
			updateMethod();// 更新
			updateMethodError();// 错误更新
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			log.error("useTransation()!" + e.getLocalizedMessage());
			countMethod();
		}
	}
	
	/**
	 * @Description:没用事物的操作
	 * @return:void
	 * @throws SQLException
	 */
	private void withoutTransation() {
		try {
			// 设置事务的提交方式为非自动提交,默认的就是自动提交
			connection.setAutoCommit(true);
			insertMethod();// 插入
			updateMethod();// 更新
			updateMethodError();// 错误更新
		} catch (Exception e) {
			log.error("没用事物，无法回滚!" + e.getMessage());
			countMethod();// 调用统计
		}
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
	
	/**
	 * @Description: 这是一个错误的更新
	 * @return:void
	 */
	private void updateMethodError() throws SQLException {
		PreparedStatement ps = connection.prepareStatement("update gao.tctransaction set ts_content=? and dd");
		ps.setString(1, "更新之后的内容");
		if (ps.executeUpdate() > 0)
			log.info("更新成功！！");
		ps.close();
	}
	
	/**
	 * @Description: 更新
	 * @return:void
	 * @throws SQLException
	 */
	private void updateMethod() throws SQLException {
		PreparedStatement ps = connection.prepareStatement("update gao.tctransaction set ts_content=?");
		ps.setString(1, "更新之后的内容");
		if (ps.executeUpdate() > 0)
			log.info("更新成功！！");
		ps.close();
	}
	
	/**
	 * @Description: 插入数据，oracle创建主键方式有很多种
	 * @see: 1、创建序列
	 * @see: 2、触发器
	 * @see: 3、代码里面获取 max（id）
	 * @throws Exception
	 * @return:void
	 */
	private void insertMethod() throws Exception {
		PreparedStatement ps = connection
				.prepareStatement("insert into gao.tctransaction values(gao.sq_transaction.nextVal,?,?,to_date(?,'yyyy-MM-dd HH24:MI:SS'))");
		ps.setString(1, "姓名");
		ps.setString(2, "内容");
		ps.setString(3, DateUtil.getTime());
		if (!ps.execute())
			log.info("插入数据成功！");
		ps.close();
	}
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		String str = sdf.format(calendar.getTime());
		System.out.println(str);
	}
}
