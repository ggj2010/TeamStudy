package com.team.gaoguangjin.jdbc.database.procdeure.normal.resultset;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.driver.OracleTypes;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.team.util.MapRowMapper;

/**
 * @ClassName:QueryMoreProcedure.java
 * @Description: 存储过程返回结果集测试
 * @author gaoguangjin
 * @Date 2015-3-7 下午11:10:26
 */
public class QueryMoreProcedure extends StoredProcedure {
	private List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
	
	public QueryMoreProcedure(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "gao.pcquerymore");
		setFunction(true);
		declareParameter(new SqlOutParameter("result", OracleTypes.INTEGER));
		declareParameter(new SqlParameter("id", Types.INTEGER));
		declareParameter(new SqlOutParameter("resultCur", OracleTypes.CURSOR, new MapRowMapper()));
		compile();
	}
	
	public List<Map<String, Object>> execute(int id) {
		HashMap<String, Object> inParams = new HashMap<String, Object>();
		inParams.put("id", 0);
		Map<String, Object> result = execute(inParams);
		List<Map<String, Object>> list = (List<Map<String, Object>>) result.get("resultCur");
		return list;
		
	}
}
