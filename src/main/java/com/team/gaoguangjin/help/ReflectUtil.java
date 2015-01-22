package com.team.gaoguangjin.help;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class ReflectUtil {
	
	/**
	 * 通过反射获取值,获取request和获取jdbc通用
	 * @param request
	 * @return
	 */
	public static LuHuiEnty getParams(HttpServletRequest request, ResultSet rs) {
		LuHuiEnty lh = null;
		try {
			Class<?> reFlectClass = Class.forName("com.team.gaoguangjin.help.LuHuiEnty");
			// 普通实例化
			lh = (LuHuiEnty) reFlectClass.newInstance();
			if (null != request) {
				Enumeration<String> enumeration = request.getParameterNames();
				// 获取所有参数名称
				while (enumeration.hasMoreElements()) {
					String name = (String) enumeration.nextElement();
					// 获取传来参数的值
					String paramsValue = request.getParameter(name);
					Field fieldName = reFlectClass.getDeclaredField(name);
					// 反射赋值
					fieldName.set(lh, paramsValue);
				}
			} else {
				// 获取实体类所有的bean
				Field[] field = reFlectClass.getDeclaredFields();
				
				for (Field fi : field) {
					// 没有判断类型
					if (fi.getType() == String.class) {
						fi.set(lh, rs.getString(fi.getName()));
					} else if (fi.getType() == Integer.class) {
						
						// id特殊处理
						if (fi.getName().equals("id")) {
							fi.set(lh, rs.getInt("ISOCODE_MAINTAIN_ID"));
						} else {
							fi.set(lh, rs.getInt(fi.getName()));
						}
						
					}
				}
			}
			
		}
		catch (Exception e) {
		}
		
		return lh;
	}
}
