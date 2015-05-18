package com.team.gaoguangjin.缓存.redis.pachong;

import java.sql.Clob;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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
public class StoreToDataBase {
	ConcurrentHashMap<String, String> currentHashMap;
	Session session;
	// 暂时没用到
	private NoteBookGroup noteBookGroup = new NoteBookGroup();
	private NoteBook nb = new NoteBook();
	// 多线程httpclient解决超时连接
	RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000).setConnectionRequestTimeout(15000)
			.setStaleConnectionCheckEnabled(true).build();
	
	public StoreToDataBase(ConcurrentHashMap<String, String> currentHashMap, Session session) {
		this.currentHashMap = currentHashMap;
		this.session = session;
	}
	
	/**
	 * @Description:
	 * @see:单线程插入==》map大小：1127 插入数据库耗时：135340ms
	 * @return:void
	 */
	public void insertToDatabase() {
		nb.setNoteBookId(1);
		noteBookGroup.setNoteBookGroupId(1);
		Iterator<String> iter = currentHashMap.keySet().iterator();
		try {
			while (iter.hasNext()) {
				String title = iter.next();
				String path = currentHashMap.get(title);
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
				session.save(note);
			}
			
		} catch (Exception e) {
			log.error("爬虫抓取网页内容写入到数据库失败！" + e.getLocalizedMessage());
		}
	}
	
	private String getHtmlByPath(String url) {
		try {
			CloseableHttpClient httpClients = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);// get
			// 解决超时,单线程的去掉，不然太慢了
			RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).build();
			httpGet.setConfig(requestConfig);
			CloseableHttpResponse response = httpClients.execute(httpGet);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity);
		} catch (Exception e) {
			log.error("httpClients读取网页内容失败！" + e.getLocalizedMessage() + ":" + url);
			return "error:httpClients读取网页内容失败：失败地址" + url + "===错误信息" + e.getLocalizedMessage();
		}
	}
}
