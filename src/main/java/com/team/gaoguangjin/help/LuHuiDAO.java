package com.team.gaoguangjin.help;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.util.StringUtils;

@Slf4j
public class LuHuiDAO {
	public List<LuHuiEnty> selectByParams(LuHuiEnty lh) {
		List<LuHuiEnty> list = new ArrayList<LuHuiEnty>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DBConfig.getConnection();
			String sql = getSelectSql(lh);
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			// 反射赋值
			HttpServletRequest request = null;
			while (rs.next()) {
				list.add(ReflectUtil.getParams(request, rs));
			}
		}
		catch (Exception e) {
			log.error("查询失败" + e.getLocalizedMessage());
		}
		finally {
			try {
				if (null != connection) {
					connection.close();
					ps.close();
					rs.close();
				}
			}
			catch (Exception e) {
				
			}
		}
		
		return list;
		
	}
	
	private String getSelectSql(LuHuiEnty lh) {
		StringBuffer sb = new StringBuffer();
		
		sb.append(" select * from gao.cux_ISO9000code_maintain where 1=1 ");
		if (lh.getBillName() != null && StringUtils.hasLength(lh.getBillName())) {
			sb.append(" and billname like '%" + lh.getBillName() + "%' ");
		}
		if (lh.getTableName() != null && StringUtils.hasLength(lh.getTableName())) {
			sb.append(" and tablename like '%" + lh.getTableName() + "%' ");
		}
		if (lh.getIsoFlag() != null && StringUtils.hasLength(lh.getIsoFlag())) {
			sb.append(" and isoflag like '%" + lh.getIsoFlag() + "%' ");
		}
		// if () {
		// sb.append("  ");
		// }
		// ....类似这种
		
		System.out.println(sb);
		return sb.toString();
	}
}
