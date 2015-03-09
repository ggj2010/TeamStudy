package com.team.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * @ClassName:MapRowMapper.java
 * @Description:获取列的值
 * @author gaoguangjin
 * @Date 2015-3-7 下午11:15:30
 */
public class MapRowMapper implements RowMapper<Map<String, Object>> {
	public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
			map.put(rs.getMetaData().getColumnName(i + 1), rs.getObject(1 + i));
		}
		return map;
	}
}
