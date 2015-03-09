package com.team.gaoguangjin.jdbc.database.procdeure.normal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.driver.OracleTypes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.team.gaoguangjin.jdbc.database.procdeure.normal.bean.Procedure;
import com.team.gaoguangjin.jdbc.database.procdeure.normal.resultset.QueryMoreProcedure;
import com.team.util.DAORowMapper;

/**
 * @ClassName:ProcedureTest.java
 * @Description: 测试数据库存储过程、方法
 * @see:procedure和function的区别，一个不带返回值，一个带返回值。返回值是指 var m=function() 一个可以返回m 一个不可以
 * @author gaoguangjin
 * @Date 2015-3-6 上午11:47:10
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:jdbcconfig/springjdbc/springjdbc.xml" })
public class ProcedureTest {
	// 注解jdbc和普通的datasource
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	DriverManagerDataSource dataSource;
	
	@Test
	public void test() {
		// // 清空测试表
		// clearTable();
		//
		// // 插入两条数据
		// insert();
		
		// 查询返回一条数据
		// queryOne();
		
		// 查询返回多条数据
		// queryMore();
		
		// 利用rowmapper查询
		// selectByrowMapper();
		
		// 利用继承StoredProcedure返回的结果集
		StoredProcedure();
	}
	
	/**
	 * @Description: 清空表
	 */
	private void clearTable() {
		try {
			dataSource.getConnection().prepareStatement("delete from gao.tcprocedure").execute();
		} catch (Exception e) {
			log.error("清空表失败" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * @Description: jdbc和springjdbc插入
	 * 
	 */
	private void insert() {
		springInsert();
		jdbcInsert();
	}
	
	private void queryMore() {
		jdbcQueryMore();
		springQueryMore();
	}
	
	/**
	 * @Description: 查询多个带返回结果集的函数
	 * @see：方法带返回参数 {? =call gao.pcquerymore(?,?)}
	 * @return:void
	 */
	private void jdbcQueryMore() {
		try {
			CallableStatement ps = dataSource.getConnection().prepareCall(" {? =call gao.pcquerymore(?,?)}");
			ps.registerOutParameter(1, Types.INTEGER);
			ps.setInt(2, 1);
			ps.registerOutParameter(3, OracleTypes.CURSOR);
			ps.execute();
			log.info(ps.getString(1) + "====");
			ResultSet rs = (ResultSet) ps.getObject(3);
			while (rs.next()) {
				log.info(rs.getObject(1) + "====" + rs.getObject(2) + "====" + rs.getObject(3) + "====" + rs.getObject(4));
			}
		} catch (Exception e) {
			log.error("" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * @Description:springjdbc 查询返回结果集
	 */
	private void springQueryMore() {
		try {
			// 匿名内部类写法
			CallableStatementCallback cb = new CallableStatementCallback() {
				public Object doInCallableStatement(CallableStatement cs) throws SQLException {
					cs.registerOutParameter(1, Types.INTEGER);
					cs.setInt(2, 1);
					cs.registerOutParameter(3, OracleTypes.CURSOR);
					cs.execute();
					log.info("springQueryMore 调用存储过程匿名类：需要在doInCallableStatement输出因为返回的是结果集");
					ResultSet rs = (ResultSet) cs.getObject(3);
					while (rs.next()) {
						log.info(rs.getObject(1) + "====" + rs.getObject(2) + "====" + rs.getObject(3) + "====" + rs.getObject(4));
					}
					return null;
				}
			};
			jdbcTemplate.execute("{? =call gao.pcquerymore(?,?)}", cb);
		} catch (Exception e) {
			log.error("" + e.getLocalizedMessage());
		}
	}
	
	private void springInsert() {
		try {
			jdbcTemplate.execute("call gao.pcinsert(" + 2 + ")");
		} catch (Exception e) {
			log.error("数据库插入失败" + e.getLocalizedMessage());
		}
	}
	
	private void jdbcInsert() {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			CallableStatement ps = connection.prepareCall("call gao.pcinsert(?)");
			ps.setInt(1, 1);
			// 提交
			ps.execute();
		} catch (Exception e) {
			log.error("数据库插入失败" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * @Description: 查询返回单个数据
	 * 
	 * @return:void
	 */
	private void jdbcQueryOne() {
		try {
			CallableStatement ps = dataSource.getConnection().prepareCall(" call gao.pcqueryone(?,?,?,?)");
			ps.setInt(1, 1);
			ps.registerOutParameter(2, Types.VARCHAR);
			ps.registerOutParameter(3, Types.VARCHAR);
			ps.registerOutParameter(4, Types.VARCHAR);
			ps.execute();
			// 获取的是输出值
			log.info(ps.getString(2) + "====" + ps.getString(3) + "====" + ps.getString(4));
		} catch (Exception e) {
			log.error("" + e.getLocalizedMessage());
		}
	}
	
	private void springQueryOne() {
		try {
			// 匿名内部类写法
			CallableStatementCallback cb = new CallableStatementCallback() {
				public Object doInCallableStatement(CallableStatement cs) throws SQLException {
					cs.setInt(1, 1);
					cs.registerOutParameter(2, Types.VARCHAR);
					cs.registerOutParameter(3, Types.VARCHAR);
					cs.registerOutParameter(4, Types.VARCHAR);
					cs.execute();
					return cs.getString(2) + "====" + cs.getString(3) + "====" + cs.getString(4);
				}
			};
			String psString = (String) jdbcTemplate.execute(" call gao.pcqueryone(?,?,?,?)", cb);
			log.info("spring 调用存储过程匿名类：" + psString);
		} catch (Exception e) {
			log.error("" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * @Description: springjdbc利用DAORowMapper返回结果集
	 */
	private void selectByrowMapper() {
		try {
			String sql = "select * from gao.tcprocedure";
			List<Procedure> procedureList = jdbcTemplate.query(sql, new DAORowMapper<Procedure>(Procedure.class));
			for (Procedure procedure : procedureList) {
				log.info("===" + procedure.getName() + "时间" + procedure.getCreatedate());
			}
		} catch (Exception e) {
			log.error("" + e.getLocalizedMessage());
		}
	}
	
	private void StoredProcedure() {
		try {
			QueryMoreProcedure qm = new QueryMoreProcedure(jdbcTemplate);
			List<Map<String, Object>> procedureList = qm.execute(1);
			for (Map<String, Object> map : procedureList) {
				// mapget()的值都要大写字段！！！！，日期设置别名 CREATEDATE
				log.info("===" + map.get("NAME") + "时间" + map.get("CREATEDATE") + ":" + map.get("CONTENT"));
			}
		} catch (Exception e) {
			log.error("" + e.getLocalizedMessage());
		}
	}
}
