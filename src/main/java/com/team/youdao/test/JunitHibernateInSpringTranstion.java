package com.team.youdao.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.util.concurrent.CountDownLatch;

import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.team.youdao.bean.Note;
import com.team.youdao.bean.NoteBook;
import com.team.youdao.bean.NoteBookGroup;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:youdao/youdao*.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
public class JunitHibernateInSpringTranstion extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	SessionFactory sessionFactory;
	
	@Test
	@Transactional
	public void test() throws IOException {
		// 事物的控制交给当前线程，如果当前方法执行完毕就自动提交事物关闭session
		final Session session = sessionFactory.getCurrentSession();
		
		// 可能会报错,因为有可能 test() 方法执行结束了，线程还没结束
		// new Thread() {
		// public void run() {
		// System.out.println("=a=");
		// try {
		// Thread.sleep(2000);// 故意制造延迟
		// System.out.println("=b=");
		// save(session);
		// System.out.println("=c=");// 打印不出来，因为单元测试不支持多线程里面sleep
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		//
		// }
		// }.start();
		//
		
		// 正确的
		// for (int i = 0; i < 5; i++) {
		// save(session);
		// System.out.println("==" + session);
		// }
		
		final CountDownLatch latch = new CountDownLatch(2);
		
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					System.out.println("==" + session);
					save(session);
					latch.countDown();
					
				}
			}.start();
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("=end=");
	}
	
	private void save(Session session) {
		Note note = new Note();
		NoteBook nb = new NoteBook();
		note.setFlag(1);
		// 创建clob Hibernate.createBlob hibernate3 用的
		// 需要用到jdbc6 才支持clob这样做
		Clob clobContent = session.getLobHelper().createClob("测试哦哦哦哦哦");
		note.setContent(clobContent);
		NoteBookGroup noteBookGroup = new NoteBookGroup();
		nb.setNoteBookId(1);
		noteBookGroup.setNoteBookGroupId(1);
		note.setNoteBook(nb);
		note.setNoteBookGroup(noteBookGroup);
		session.save(note);
	}
	
	public String ClobToString(Clob clob) throws IOException {
		BufferedReader br = null;
		String reString = "";
		try {
			
			Reader is = clob.getCharacterStream();// 得到流
			br = new BufferedReader(is);
			String s = br.readLine();
			StringBuffer sb = new StringBuffer();
			while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
				sb.append(s);
				s = br.readLine();
			}
			reString = sb.toString();
			return reString;
		} catch (Exception e) {
			br.close();
		}
		return reString;
	}
}
