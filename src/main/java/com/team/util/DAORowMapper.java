package com.team.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSetMetaData;

/**
 * @ClassName:DAORowMapper.java
 * @Description: 将数据库查询返回的对象映射到具体的类
 * @author gaoguangjin
 * @Date 2015-3-7 下午11:15:48
 */
public class DAORowMapper<T> implements RowMapper {
	public DAORowMapper(Class<? extends T> rowObjClass) {
		super();
		this.rowObjClass = rowObjClass;
		this.direct = this.isDirectClass();
	}
	
	public DAORowMapper(Class<? extends T> rowObjClass, boolean direct) {
		super();
		this.rowObjClass = rowObjClass;
		boolean directClass = this.isDirectClass();
		this.direct = directClass ? true : direct;
	}
	
	@SuppressWarnings(value = "unchecked")
	public T mapRow(ResultSet rs, int index) throws SQLException {
		T object = null;
		Method[] methods = null;
		if (!this.direct) {
			// 获取数据保存对象所有的公开方法，包括继承的方法
			methods = this.rowObjClass.getMethods();
		}
		// 获取列数据
		ResultSetWrappingSqlRowSetMetaData wapping = new ResultSetWrappingSqlRowSetMetaData(rs.getMetaData());
		int columnCount = wapping.getColumnCount();
		for (int columnIndex = 0; columnIndex++ != columnCount;) {
			// 列被封装的java类型名称,下面使用它表示可以封装到那些java类型
			String paramClassName = wapping.getColumnClassName(columnIndex);
			int type = wapping.getColumnType(columnIndex);
			Object value = rs.getObject(columnIndex);
			
			// 是否字符串类型
			boolean stringType = (paramClassName != null && paramClassName.equals("java.lang.String") || type == Types.CLOB);
			
			// 将字符串类型的 null 转换为 ""
			if (stringType && value == null) {
				value = "";
			}
			
			// 对于取不到值的字段不进行处理，直接处理下一个字段
			if (value == null) {
				continue;
			}
			
			boolean isNumber = false;
			switch (type) {
				case Types.NUMERIC: // 处理整数和浮点数等数字类型
					isNumber = true;
					if (this.direct) {
						String className = this.rowObjClass.getName();
						DAORowMapper.ClassType classType = DAORowMapper.classNameMap.get(className);
						if (classType == null) {
							classType = DAORowMapper.ClassType.UNDEFINED;
						}
						// 根据所需数据类型对当前值进行变形、返回
						switch (classType) {
							case BOOLEAN:
								value = rs.getBoolean(columnIndex);
								return (T) value;
							case BYTE:
								value = rs.getBytes(columnIndex);
								return (T) value;
							case SHORT:
								value = rs.getShort(columnIndex);
								return (T) value;
							case INT:
								value = rs.getInt(columnIndex);
								return (T) value;
							case LONG:
								value = rs.getLong(columnIndex);
								return (T) value;
							case FLOAT:
								value = rs.getFloat(columnIndex);
								return (T) value;
							case DOUBLE:
								value = rs.getDouble(columnIndex);
								return (T) value;
						}
					} else {
						paramClassName = "java.lang.Integer,int,java.lang.Boolean,boolean,java.lang.Float,float,java.lang.Double,double,java.lang.Long,long";
					}
					break;
				case Types.CLOB:// 处理 CLOB 为字符串
					Clob clob = rs.getClob(columnIndex);
					if (clob != null) {
						StringBuffer content = new StringBuffer();
						BufferedReader br = new BufferedReader(clob.getCharacterStream());
						String line;
						try {
							while ((line = br.readLine()) != null) {
								content.append(line);
							}
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
						value = content.toString();
						paramClassName = "java.lang.String";
					}
					if (this.direct && DAORowMapper.classNameMap.get(this.rowObjClass.getName()) == DAORowMapper.ClassType.STRING) {
						return this.rowObjClass.cast(value);
					}
					break;
				// 处理日期时间类型
				case Types.TIMESTAMP:
				case Types.DATE:
					value = rs.getTimestamp(columnIndex);
					if (this.direct) {
						String className = this.rowObjClass.getName();
						DAORowMapper.ClassType classType = DAORowMapper.classNameMap.get(className);
						if (classType == null) {
							classType = DAORowMapper.ClassType.UNDEFINED;
						}
						// 根据所需数据类型对当前值进行变形、返回
						switch (classType) {
							case UTILDATE:
							case CALENDAR:
							case SQLDATE:
								return (T) this.transDateValue(this.rowObjClass, value);
						}
					} else {
						paramClassName = "java.sql.Date,java.util.Date,java.util.Calendar";
					}
					break;
				case Types.BLOB: // 处理 BLOB
					value = null; // 暂时不对该之处理。目前不支持在数据库中保存文件：如图片等
					break;
				case Types.VARCHAR:
				case Types.CHAR:
					paramClassName = "java.lang.String,java.lang.Boolean,boolean";
					break;
			}
			
			if (this.direct) { // 直接映射情况下，对剩余字段类型，比如 VARCHAR 类型或 CHAR 类型，进行补充处理
				String className = this.rowObjClass.getName();
				DAORowMapper.ClassType classType = DAORowMapper.classNameMap.get(className);
				if (classType == null) {
					classType = DAORowMapper.ClassType.UNDEFINED;
				}
				switch (classType) {
					case STRING:
						return this.rowObjClass.cast(value);
					case BOOLEAN:
						value = rs.getBoolean(columnIndex);
						return (T) value;
					case CHAR:
						String stringValue = rs.getString(columnIndex);
						if (stringValue == null || stringValue.length() == 0) {
							value = (char) 0;
						} else {
							value = stringValue.charAt(0);
						}
						return (T) value;
				}
			} else {
				try {
					if (columnIndex == 1 || object == null)
						object = this.rowObjClass.newInstance();
				} catch (Exception e) {
				}
				// 找到和当前字段名称一致的对象属性设置方法，然后赋值
				String name = wapping.getColumnName(columnIndex);
				for (Method method : methods) {
					// 通过方法名以及参数类型来过滤掉不匹配的方法，过滤之后剩下的就是对应的 setter
					String methodName = method.getName();
					if (methodName == null || !methodName.equalsIgnoreCase("set".concat(name))) {
						continue;
					}
					// 获取参数类型
					Class<?>[] params = method.getParameterTypes();
					if (params.length != 1) {
						continue;
					}
					// 参数不匹配则继续
					if (!paramClassName.contains(params[0].getName()) && !params[0].getName().equals("java.lang.Object")) {
						continue;
					}
					// Integer value = 0.0
					if (String.valueOf(value).equals("0.0") && params[0].getName().equals("java.lang.Integer")) {
						value = 0;
					}
					// 如果是时间类型，根据 setter 的参数类型进行类型转换
					if (type == Types.DATE) {
						value = this.transDateValue(params[0], value);
					}
					// 如果方法参数是逻辑类型，取逻辑值
					if (DAORowMapper.classNameMap.get(params[0].getName()) == DAORowMapper.ClassType.BOOLEAN) {
						value = rs.getBoolean(columnIndex);
					}
					
					if (isNumber) {
						DAORowMapper.ClassType classType = DAORowMapper.classNameMap.get(params[0].getName());
						switch (classType) {
							case BOOLEAN:
								value = rs.getBoolean(columnIndex);
								break;
							case BYTE:
								value = rs.getBytes(columnIndex);
								break;
							case SHORT:
								value = rs.getShort(columnIndex);
								break;
							case INT:
								value = rs.getInt(columnIndex);
								break;
							case LONG:
								value = rs.getLong(columnIndex);
								break;
							case FLOAT:
								value = rs.getFloat(columnIndex);
								break;
							case DOUBLE:
								value = rs.getDouble(columnIndex);
								break;
							case OBJECTT:
								value = rs.getObject(columnIndex);
						}
					}
					// 执行 setter
					try {
						method.invoke(object, value);
					} catch (Exception e) {
						
					}
					break;
				}
			}
		}
		return object;
	}
	
	protected Object transDateValue(Class<?> targetClass, Object value) {
		if (!(value instanceof Timestamp)) {
			return value;
		}
		Timestamp realValue = (Timestamp) value;
		String targetClassName = targetClass.getName();
		DAORowMapper.ClassType classType = DAORowMapper.classNameMap.get(targetClassName);
		if (classType == null) {
			classType = DAORowMapper.ClassType.UNDEFINED;
		}
		long timestamp = realValue.getTime();
		switch (classType) {
			case UTILDATE:
				value = new java.util.Date(timestamp);
				break;
			case CALENDAR:
				Calendar targetValue = Calendar.getInstance();
				targetValue.setTimeInMillis(timestamp);
				value = targetValue;
				break;
			case SQLDATE:
				value = new java.sql.Date(timestamp);
				break;
		}
		return value;
	}
	
	private final boolean isDirectClass() {
		if (this.rowObjClass == null)
			return false;
		String className = this.rowObjClass.getName();
		boolean result = DAORowMapper.classNameMap.get(className) != null;
		return result;
	}
	
	static protected enum ClassType {
		UNDEFINED, STRING, CHAR, INT, BYTE, SHORT, LONG, FLOAT, DOUBLE, BOOLEAN, UTILDATE, CALENDAR, SQLDATE, OBJECTT;
	}
	
	static protected Map<String, DAORowMapper.ClassType> classNameMap = new HashMap<String, DAORowMapper.ClassType>();
	static {
		DAORowMapper.classNameMap.put("java.lang.String", ClassType.STRING);
		DAORowMapper.classNameMap.put("java.lang.Character", ClassType.CHAR);
		DAORowMapper.classNameMap.put("char", ClassType.CHAR);
		DAORowMapper.classNameMap.put("java.lang.Integer", ClassType.INT);
		DAORowMapper.classNameMap.put("int", ClassType.INT);
		DAORowMapper.classNameMap.put("java.lang.Byte", ClassType.BYTE);
		DAORowMapper.classNameMap.put("byte", ClassType.BYTE);
		DAORowMapper.classNameMap.put("java.lang.Short", ClassType.SHORT);
		DAORowMapper.classNameMap.put("short", ClassType.SHORT);
		DAORowMapper.classNameMap.put("java.lang.Long", ClassType.LONG);
		DAORowMapper.classNameMap.put("long", ClassType.LONG);
		DAORowMapper.classNameMap.put("java.lang.Float", ClassType.FLOAT);
		DAORowMapper.classNameMap.put("float", ClassType.FLOAT);
		DAORowMapper.classNameMap.put("java.lang.Double", ClassType.DOUBLE);
		DAORowMapper.classNameMap.put("double", ClassType.DOUBLE);
		DAORowMapper.classNameMap.put("java.lang.Boolean", ClassType.BOOLEAN);
		DAORowMapper.classNameMap.put("boolean", ClassType.BOOLEAN);
		DAORowMapper.classNameMap.put("java.util.Date", ClassType.UTILDATE);
		DAORowMapper.classNameMap.put("java.util.Calendar", ClassType.CALENDAR);
		DAORowMapper.classNameMap.put("java.sql.Date", ClassType.SQLDATE);
		DAORowMapper.classNameMap.put("java.lang.Object", ClassType.OBJECTT);
	}
	
	private Class<? extends T> rowObjClass;
	private boolean direct;
}