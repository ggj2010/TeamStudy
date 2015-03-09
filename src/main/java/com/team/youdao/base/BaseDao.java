package com.team.youdao.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName:BaseDao.java
 * @Description: 获取sessionFactory
 * @author gaoguangjin
 * @param <T>
 * @Date 2015-3-9 上午11:46:16
 */
public class BaseDao<T> {
	/**
	 * Spring4+Hibernate4中不再支持HibernaterTemplate，只能使用原生的session接口来操作
	 */
	@Autowired
	protected SessionFactory sessionFactory;
	
	/**
	 * 泛型dao要用到的class
	 */
	protected Class<T> clazz;
	
	/**
	 * 使用getCurrentSession 1、将创建的session绑定到当前线程中 2、session在事务commit或rollback的时候会自动关闭
	 * @author caibosi
	 * @created 2014-02-10
	 * @return
	 */
	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 构造器 自动获取class
	 */
	public BaseDao() {
		Class clazz = getClass();
		while (clazz != Object.class) {
			Type t = clazz.getGenericSuperclass();
			if (t instanceof ParameterizedType) {
				Type[] args = ((ParameterizedType) t).getActualTypeArguments();
				if (args[0] instanceof Class) {
					this.clazz = (Class<T>) args[0];
					break;
				}
			}
			clazz = clazz.getSuperclass();
		}
	}
	
	/******************************************
	 * 查询、统计方法 /
	 ******************************************/
	
	/**
	 * 查询一个
	 * @param entityId
	 * @return
	 * @throws Exception
	 */
	public T findOne(String entityId) throws Exception {
		return (T) getCurrentSession().get(clazz, entityId);
	}
	
	/**
	 * 通过条件查询 criteria的list方法会填充一二级缓存 大批量查询时要注意
	 * @param dc
	 * @return
	 * @throws Exception
	 */
	public List<T> findByCriteria(DetachedCriteria dc) throws Exception {
		Criteria executableCriteria = dc.getExecutableCriteria(getCurrentSession());
		return executableCriteria.list();
	}
	
	/**
	 * 创建实体
	 * @param entity
	 */
	public void save(final T entity) {
		getCurrentSession().save(entity);
	}
	
	/**
	 * 更新实体
	 * @param entity
	 */
	public void update(T entity) {
		getCurrentSession().update(entity);
	}
	
}
