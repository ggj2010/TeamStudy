package com.team.gaoguangjin.缓存.redis.pachong;

import java.sql.Clob;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.hibernate.Session;

import com.team.youdao.bean.Note;
import com.team.youdao.bean.NoteBook;
import com.team.youdao.bean.NoteBookGroup;

/**
 * @ClassName:StoreToDataBase.java
 * @Description: 根据url 读取内容写入到表里面
 * @author gaoguangjin
 * @Date 2015-5-15 下午6:08:19
 */
@Slf4j
public class StoreToDataBaseByThread {
	ConcurrentHashMap<String, String> currentHashMap;
	Session session;
	CountDownLatch latch;
	// 暂时没用到
	private NoteBookGroup noteBookGroup = new NoteBookGroup();
	private NoteBook nb = new NoteBook();
	
	// 多线程httpclient解决超时连接
	private static RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(500000).setConnectTimeout(500000).build();
	// http请求连接池
	private PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
	// 一个服务器只有一个客户端
	private CloseableHttpClient httpClients = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(defaultRequestConfig).build();
	
	public StoreToDataBaseByThread(ConcurrentHashMap<String, String> currentHashMap, Session session, CountDownLatch latch) {
		this.currentHashMap = currentHashMap;
		this.session = session;
		this.latch = latch;
	}
	
	/**
	 * @Description:
	 * @see:多单线程插入==》map大小： 插入数据库耗时：50103ms
	 * @return:void
	 */
	public List<Note> insertToDatabase() {
		nb.setNoteBookId(1);
		noteBookGroup.setNoteBookGroupId(1);
		final List<Note> noteList = new CopyOnWriteArrayList<Note>();
		Iterator<String> iter = currentHashMap.keySet().iterator();
		try {
			while (iter.hasNext()) {
				final String title = iter.next();
				final String path = currentHashMap.get(title);
				new Thread() {
					public void run() {
						Note note = new Note();
						String content = getHtmlByPath(path);
						Clob clobContent = session.getLobHelper().createClob(content);
						note.setFromUrl(path);
						note.setContent(clobContent);
						note.setNoteName(title);
						note.setAuthorName("高广金");
						note.setCreatedate(new Date());
						if (content.contains("error:httpClients读取网页内容失败")) {
							note.setFlag(1);
						} else {
							note.setFlag(0);
						}
						
						note.setNoteBookGroup(noteBookGroup);
						note.setNoteBook(nb);
						noteList.add(note);
						latch.countDown();
					}
				}.start();
			}
			
		} catch (Exception e) {
			log.error("爬虫抓取网页内容写入到数据库失败！" + e.getLocalizedMessage());
		}
		return noteList;
	}
	
	private String getHtmlByPath(String url) {
		
		HttpGet httpGet = null;
		try {
			
			httpGet = new HttpGet(url);// get
			// 解决超时
			RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).build();
			httpGet.setConfig(requestConfig);
			CloseableHttpResponse response = httpClients.execute(httpGet);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity);
			
		} catch (Exception e) {
			log.error("httpClients读取网页内容失败！" + e.getLocalizedMessage() + ":" + url);
			return "error:httpClients读取网页内容失败：失败地址" + url + "===错误信息" + e.getLocalizedMessage();
		}
		finally {
			httpGet.releaseConnection();
		}
	}
}
