package com.team.gaoguangjin.缓存.redis.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;

/**
 * @ClassName:RedisDao.java
 * @Description:redisDao公共API
 * @author gaoguangjin
 * @Date 2015-5-17 下午10:24:33
 */
public abstract class RedisDao<T> {
	public final static String SPLIT_MARK = ":";
	public final static String SORT = "sort";
	public final static String INDEX = "index";
	public final static String UPDATE_LOG = "updatelog";
	public static Jedis jedis;
	protected Class<T> classs;
	
	static {
		// 连接redis linux服务器
		jedis = new Jedis("123.56.118.135", 6379);
		// 权限认证
		jedis.auth("gaoguangjin");// 密码最好越长越好，防止暴力破解
	}
	
	/**
	 * 构造器 自动获取class
	 */
	public RedisDao() {
		Class clazz = getClass();
		while (clazz != Object.class) {
			Type t = clazz.getGenericSuperclass();
			if (t instanceof ParameterizedType) {
				Type[] args = ((ParameterizedType) t).getActualTypeArguments();
				if (args[0] instanceof Class) {
					this.classs = (Class<T>) args[0];
					break;
				}
			}
			classs = clazz.getSuperclass();
		}
	}
	
	/**
	 * @Description比较key存储的value 和传入的value是否相等
	 * @param key
	 * @return:Boolean
	 */
	public Boolean existValueByKey(String key, String value) {
		return jedis.get(key) == value ? true : false;
	}
	
	/**
	 * @Description:初始化将表中数据放到redis,存放格式为tableName:id:column.
	 * @param tableName 表映射的类名
	 * @param id 主键ID值
	 * @param column 表映射的列信息
	 * @param value 列对应的值
	 * @return:String
	 */
	public String setTable(String tableName, String id, String column, String value) {
		return jedis.set(tableName + SPLIT_MARK + id + SPLIT_MARK + column, value);
	}
	
	/**
	 * @Description:初始化存放表中所有字段数据，存放格式为tableName:column:columnValue
	 * @param tableName
	 * @param column
	 * @param value
	 * @param id
	 * @return:Long
	 */
	public Long saddColumn(String tableName, String column, String columnValue, String id) {
		return jedis.sadd(tableName + SPLIT_MARK + column + SPLIT_MARK + columnValue, tableName + SPLIT_MARK + id);
	}
	
	/**
	 * @Description:初始化将表中数据放到redis,将bean转换成json格式，存放格式为tableName:id。
	 * @param tableName 表映射的类名
	 * @param id 主键id值
	 * @param json 改id值对应的json字符串
	 * @return:String
	 */
	public String setJSON(String tableName, String id, String json) {
		return jedis.set(tableName + SPLIT_MARK + id, json);
	}
	
	/**
	 * @Description: 根据jsonKey，获取相应的json字符串，转换成实体类List
	 * @param key
	 * @return
	 * @return:List<T>
	 */
	public List<T> getListBean(Set<String> sortKey) {
		List<T> list = new ArrayList<T>();
		for (String key : sortKey) {
			list.add(getBean(key));
		}
		return list;
	}
	
	/**
	 * @Description:根据jsonKey，获取相应的json字符串，转换成实体类
	 * @param key
	 * @return:T dao层泛型的实体类
	 */
	public T getBean(String key) {
		return JSON.parseObject(jedis.get(key), classs);
	}
	
	/**
	 * @Description:对指定值加排序
	 * @param tableName
	 * @param column
	 * @param value
	 * @param id
	 * @return:void
	 */
	public Long zaddSort(String tableName, String column, String value, String id) {
		return jedis.zadd(tableName + SPLIT_MARK + SORT + SPLIT_MARK + column, Double.parseDouble(value), id);
	}
	
	/**
	 * @Description:对表主键加索引
	 * @param tableName
	 * @param column
	 * @param value
	 * @return:Long
	 */
	public Long zaddIndex(String tableName, String column, String value) {
		return jedis.sadd(tableName + SPLIT_MARK + INDEX + SPLIT_MARK + column, value);
	}
	
	/**
	 * @Description: 对redis缓存操作的日志，以对象json格式保存，然后队列方式更新到数据库
	 * @param value
	 * @return:Long
	 */
	public Long log(String className, String value) {
		// set里面放类名+json list里面放类名
		jedis.sadd(className, value);
		return jedis.lpush(UPDATE_LOG, className);
	}
	
	/**
	 * @Description:定时任务自动将更新数据同步到数据库
	 * @return:void
	 * @throws ClassNotFoundException
	 */
	public List<Object> updateToDataBase() throws ClassNotFoundException {
		List<Object> ob = new ArrayList<Object>();
		String className = jedis.lpop(UPDATE_LOG);
		Set<String> keySet = jedis.smembers(className);
		if (keySet.size() > 0) {
			Class<?> object = classs.forName(className);
			for (String json : keySet) {
				Object bean = JSON.parseObject(json, object);
				ob.add(bean);
			}
		}
		return ob;
	}
}
