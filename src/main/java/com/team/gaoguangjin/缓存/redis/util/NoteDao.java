package com.team.gaoguangjin.缓存.redis.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.team.youdao.bean.Note;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:youdao/youdao*.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class NoteDao extends RedisDao<Note> {
	@Autowired
	SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		try {
			NoteDao noteDao = new NoteDao();
			// noteDao.select(noteDao);// 查询
			// noteDao.insert();// 插入
			noteDao.update(noteDao);// 更新
			// noteDao.delete( noteDao);
			// 异步更新到数据库
			dataBaseUpdate(noteDao);
		} catch (Exception e) {
			log.error("redis查询错误！" + e.getLocalizedMessage());
		}
	}
	
	private static void dataBaseUpdate(NoteDao noteDao) {
		try {
			List<Object> object = noteDao.updateToDataBase();
			for (Object object2 : object) {
				log.info(object2.toString());
				String className = object2.getClass().getName();
				Field[] fieldList = Thread.currentThread().getContextClassLoader().loadClass(className).getDeclaredFields();
				for (Field field : fieldList) {
					field.setAccessible(true);
					if (field.getName().equals("flag")) {
						if (field.get(object2).toString().equals("1")) {
							// hibernate对数据库进行删除操作
							log.info("数据库删除动作");
						} else {
							// 比如用hibernate sessionFactory.getCurrentSession().save(object2)。就直接新增和更新了
							log.info("数据库更新动作");
						}
						// 更新成功之后删除set k/v里面的值
						// 目前没有写具体判断细节。
						jedis.srem(className, JSON.toJSON(object2).toString());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insert() throws Exception {
		List<Note> noteList = new ArrayList<Note>();
		Note note = new Note();
		note.setNoteId(20151314);
		note.setAuthorName("高广金");
		note.setFromUrl("www.baidu.com");
		noteList.add(note);
		insertToredis(noteList);
		log.info("插入id为 20151314 成功！Note:20151314");
	}
	
	/**
	 * @Description:select * from table where url=? and ...
	 * @param noteDao
	 * @return:void
	 */
	private void select(NoteDao noteDao) {
		String fromUrl = "http://www.tuicool.com/articles/zu6Nrm";
		String authorName1 = "admin";
		String authorName2 = "gao";
		String flag = "0";
		// 查询根据id
		// selectById(noteDao);
		// /* 查询方式一 根据set/get */
		// selectTypeOne(noteDao, fromUrl);
		// /* 查询方式二 * 根据 sadd/smembers */
		// selectTypeTwo(noteDao, fromUrl);
		/* 查询方式三 and */
		selectTypeThree(noteDao, authorName1, fromUrl);
		/* 查询方式四 or */
		selectTypeFour(noteDao, authorName1, authorName2);
		/* 查询方式五 and和or 同时存在 */
		selectTypeFour(noteDao, authorName1, authorName2, flag);
		
	}
	
	/**
	 * @Description: 查询并交集合 select * from tableName where authorName in("gao","admin") and flag=0
	 * @param noteDao
	 * @param authorName1
	 * @param authorName2
	 * @param fromUrl
	 * @return:void
	 */
	private void selectTypeFour(NoteDao noteDao, String authorName1, String authorName2, String flag) {
		// 并交等于。给自己的交在加到一起
		log.info("=================并交================");
		long time = System.currentTimeMillis();
		Set<String> sortKey1 = jedis.sinter("Note:authorName:" + authorName1, "Note:flag:" + flag);
		Set<String> sortKey2 = jedis.sinter("Note:authorName:" + authorName2, "Note:flag:" + flag);
		display(sortKey1, noteDao);
		display(sortKey2, noteDao);
		long endTime = System.currentTimeMillis();
		log.info("sadd/smembers sinter两次查询交集合 方式执行select * from tableName where authorName in(?,?) and flag=0 耗时：" + (endTime - time) + "ms");
	}
	
	/**
	 * @Description:查询并集合 sunion select* from tableName where authorName in("gao","admin")
	 * @param noteDao
	 * @param url
	 * @param authorName1
	 * @param authorName2
	 * @return:void
	 */
	private void selectTypeFour(NoteDao noteDao, String authorName1, String authorName2) {
		log.info("=================================");
		long time = System.currentTimeMillis();
		Set<String> sortKey = jedis.sunion("Note:authorName:" + authorName1, "Note:authorName:" + authorName2);
		display(sortKey, noteDao);
		long endTime = System.currentTimeMillis();
		log.info("sadd/smembers sunion查询并结合 select* from tableName where authorName in(gao,admin) 耗时：" + (endTime - time) + "ms");
	}
	
	/**
	 * @Description: 查询交集合 sinter select* from tableName where authorName=? and url=?
	 * @param noteDao
	 * @param url
	 * @param authorName admin
	 * @param url
	 * @return:void
	 */
	private void selectTypeThree(NoteDao noteDao, String authorName1, String fromUrl) {
		log.info("=================================");
		long time = System.currentTimeMillis();
		Set<String> sortKey = jedis.sinter("Note:authorName:" + authorName1, "Note:fromUrl:" + fromUrl);
		display(sortKey, noteDao);
		long endTime = System.currentTimeMillis();
		log.info("sadd/smembers sinter查询交集合 方式执行 select* from tableName where authorName=? and url=?耗时：" + (endTime - time) + "ms");
	}
	
	private void selectTypeTwo(NoteDao noteDao, String url) {
		long time = System.currentTimeMillis();
		Set<String> sortKey = jedis.smembers("Note:fromUrl:" + url);
		display(sortKey, noteDao);
		long endTime = System.currentTimeMillis();
		log.info("sadd/smembers set方式执行 select * from table where url=?耗时：" + (endTime - time) + "ms");
	}
	
	private static void selectTypeOne(NoteDao noteDao, String url) {
		long time = System.currentTimeMillis();
		Set<String> sort = jedis.keys("Note:*:fromUrl");
		// 符合条件的key值集合
		Set<String> sortKey = new HashSet<String>();
		for (String key : sort) {
			if (url.equals(jedis.get(key))) {
				StringTokenizer st = new StringTokenizer(key, ":");
				st.nextToken();
				sortKey.add("Note:" + st.nextToken());
			}
		}
		display(sortKey, noteDao);
		long endTime = System.currentTimeMillis();
		log.info("set/get方式执行 select * from table where url=?耗时：" + (endTime - time) + "ms");
	}
	
	private static void display(Set<String> sortKey, NoteDao noteDao) {
		// 查询所有key的结果
		List<Note> noteList = noteDao.getListBean(sortKey);
		// 打印检查
		for (Note note2 : noteList) {
			log.info(note2.toString());
		}
	}
	
	private static void selectById(NoteDao noteDao) {
		Note note = noteDao.getBean("Note:29453");
		log.info("获取redis服务器上面的值。select * from table where id=?");
		log.info(note.toString());
	}
	
	private void delete(NoteDao noteDao) {
		
	}
	
	private void update(NoteDao noteDao) {
		Note note = noteDao.getBean("Note:29892");
		// 修改了url
		String newFromUrl = "www.baidu.com";
		String oldFromUrl = note.getFromUrl();
		String id = "29892";
		String jsonString = "";
		note.setFromUrl(newFromUrl);
		
		// 如果删除的话就用到flag标记
		// note.setFlag(1);
		
		// 记录日志
		if (null != note) {
			String className = note.getClass().getName();
			jsonString = JSON.toJSON(note).toString();
			noteDao.log(className, jsonString);
		}
		
		// 更新缓存类型一
		setTable("Note", id, "fromUrl", newFromUrl);
		
		// 更新缓存类型二、先去除原来key里面的，然后再重新创建一个
		jedis.srem("Note:fromUrl:" + oldFromUrl, id);
		saddColumn("Note", "fromUrl", newFromUrl, id);
		// 更新缓存类型三
		noteDao.setJSON("Note", id, jsonString);
		
	}
	
	@Test
	@Transactional
	public void testStore() throws Exception {
		// 事物的控制交给当前线程，如果当前方法执行完毕就自动提交事物关闭session
		final Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Note");
		List<Note> noteList = query.list();
		System.out.println("总大小：" + noteList.size());
		// 截取留50个
		noteList = noteList.subList(0, 50);
		jedis.flushDB();// 清空测试
		long time = System.currentTimeMillis();
		
		insertToredis(noteList);
		long end = System.currentTimeMillis();
		log.info("耗时：" + (end - time));
	}
	
	private void insertToredis(List<Note> noteList) throws Exception {
		Field[] fieldList = Thread.currentThread().getContextClassLoader().loadClass(Note.class.getName()).getDeclaredFields();
		for (Note note : noteList) {
			note.setContents(note.ClobToString(note.getContent()));
			note.setContent(null);
			for (Field field : fieldList) {
				field.setAccessible(true);
				String fieldName = field.getName();
				Object fieldValue = field.get(note);
				if (!"serialVersionUID".equals(fieldName) && !"log".equals(fieldName) && null != fieldValue) {
					// 1、类型一k/v
					setTable("Note", "" + note.getNoteId(), fieldName, fieldValue.toString());
					// 对有注解的进行sadd kv存储
					if (field.isAnnotationPresent(NeedSelect.class)) {
						// 2、类型二 k/v
						saddColumn("Note", fieldName, fieldValue.toString(), note.getNoteId() + "");
					}
				}
			}
			Object object = JSON.toJSON(note);
			// 3、 存放映射bean key-jsonValue
			setJSON("Note", "" + note.getNoteId(), JSON.toJSON(note).toString());
			// 4、id值放到set里面，id加索引
			zaddIndex("Note", "noteId", note.getNoteId() + "");
			
			// 5、按照id大小排序
			zaddSort("Note", "noteId", note.getNoteId() + "", note.getNoteId() + "");
		}
	}
	
}
