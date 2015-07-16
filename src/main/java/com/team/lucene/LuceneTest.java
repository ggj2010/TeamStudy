package com.team.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * @ClassName:LuceneTest.java
 * @Description:搜索引擎lucene学习
 * @author gaoguangjin
 * @Date 2015-7-13 下午2:54:03
 */
@Slf4j
public class LuceneTest {
	private final static String INDEX_FILE = "d:/ftp/index";
	// 需要写入lucene源文件目录
	private final static String FILE_PATH = "d:/ftp/lucene";
	
	static Directory directory;
	static {
		try {
			File indexFile = new File(INDEX_FILE);
			// 将索引存放在磁盘index_file目录
			directory = FSDirectory.open(indexFile);
		} catch (IOException e) {
		}
	}
	
	public static void main(String[] args) {
		// 删除指定的索引
		String deleteIndexName = "lucene1.txt";
		String updateIndexName = "lucene2.txt";
		// 创建
		createIndex();
		// 普通查询
		search();
		
		// // 删除
		// deleteIndex(deleteIndexName);
		// search();
		// // 更新
		// updateIndex(updateIndexName);
		// search();
	}
	
	/**
	 * @Description:更新索引 相当于先删除原来的，再插入新的document。因为lucene不支持更新单个field
	 * @param updateIndexName
	 * @return:void
	 */
	private static void updateIndex(String updateIndexName) {
		IndexWriter iw = null;
		try {
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_40, analyzer);
			iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
			iw = new IndexWriter(directory, iwc);
			Document document = new Document();
			Field field1 = new StringField("path", "f:a/b/c", Field.Store.YES);
			Field field2 = new StringField("fileName", "更新的fileName", Field.Store.YES);
			Field fied3 = new TextField("contents", "students is a baby", Field.Store.YES);
			document.add(field1);
			document.add(field2);
			document.add(fied3);
			
			// 根据term匹配document，如果term匹配准确性不高，将会删除多个索引
			Term term = new Term("fileName", updateIndexName);
			iw.updateDocument(term, document);
			
			/** 上一步的updte等于注视的 先删除再更新 **/
			// iw.deleteDocuments(term);
			// iw.addDocument(document);
		} catch (Exception e) {
			log.error("删除索引失败！" + e.getLocalizedMessage());
		}
		finally {
			try {
				// 需要提交和关闭
				iw.commit();
				// iw.rollback();
				iw.close();
				log.info("---------------更新索引-------------------");
			} catch (IOException e) {
				log.error("关闭IndexWriter失败！" + e.getLocalizedMessage());
			}
		}
		
	}
	
	/**
	 * @Description: 删除索引
	 * @param deleteIndexName
	 * @return:void
	 */
	private static void deleteIndex(String deleteIndexName) {
		IndexWriter iw = null;
		try {
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_40, analyzer);
			iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
			iw = new IndexWriter(directory, iwc);
			
			// 根据term匹配document，如果term匹配准确性不高，将会删除多个索引
			Term term = new Term("fileName", deleteIndexName);
			iw.deleteDocuments(term);
		} catch (Exception e) {
			log.error("删除索引失败！" + e.getLocalizedMessage());
		}
		finally {
			try {
				// 需要提交和关闭
				iw.commit();
				// iw.rollback();
				iw.close();
				log.info("---------------删除索引-------------------");
			} catch (IOException e) {
				log.error("关闭IndexWriter失败！" + e.getLocalizedMessage());
			}
		}
	}
	
	/**
	 * @Description: 构建索引
	 * @see:Version.LUCENE_40为版本号,比如maven里面引入的是4.0.0版本的core架包
	 * @return:void
	 */
	private static void createIndex() {
		BufferedReader br = null;
		IndexWriter iw = null;
		try {
			// File indexFile = new File(INDEX_FILE);
			// Directory directory = FSDirectory.open(new File(INDEX_FILE));
			// 分词器
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
			// 配置
			IndexWriterConfig indexwc = new IndexWriterConfig(Version.LUCENE_40, analyzer);
			// 创建新的索引文件时候 追加到已有的索引库
			indexwc.setOpenMode(OpenMode.CREATE);
			// 写入索引
			iw = new IndexWriter(directory, indexwc);
			// 将索引写入指定目录
			File[] files = new File(FILE_PATH).listFiles();
			for (File file : files) {
				br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
				// 构建文档，文档可以指一个 HTML 页面，一封电子邮件，或者是一个文本文件。
				Document docuemnt = new Document();
				
				// field对象是用来描述一个文档的某个属性的，比如一封电子邮件的标题和内容可以用两个 Field 对象分别描述
				Field pathField = new StringField("path", file.getPath(), Field.Store.YES);
				// 最后的修改时间，不存放到到index里面
				Field modifiField = new LongField("modifiField", file.lastModified(), Field.Store.NO);
				// 内容不妨到index里面
				// Field contentFied = new TextField("contents", br);
				// 内容存放到index里面
				Field contentFied = new TextField("contents", IOUtils.toString(br), Field.Store.YES);
				// 文件名称
				Field fileNameFied = new StringField("fileName", file.getName(), Field.Store.YES);
				
				// 将field添加到文档里面
				docuemnt.add(pathField);
				docuemnt.add(modifiField);
				docuemnt.add(contentFied);
				docuemnt.add(fileNameFied);
				iw.addDocument(docuemnt);
				log.info("构建" + file.getAbsolutePath() + "文件索引成功！");
			}
		} catch (Exception e) {
			log.error("构建索引失败！" + e.getLocalizedMessage());
		}
		finally {
			try {
				// 一定要关闭写入索引，不然不写入的噢!
				iw.close();
				br.close();
			} catch (IOException e) {
				log.error("关闭输入流失败！" + e.getLocalizedMessage());
			}
		}
	}
	
	/**
	 * @Description: 进行查询
	 * @return:void
	 */
	private static void search() {
		try {
			// 查询条件
			String queryStr = "students";
			// filed对应的名称
			String queryField = "contents";
			// File indexFile = new File(INDEX_FILE);
			// Directory directory = FSDirectory.open(new File(INDEX_FILE));
			
			// 索引文件存放路径
			IndexReader indexReader = DirectoryReader.open(directory);
			// 检索工具
			IndexSearcher indexSeacher = new IndexSearcher(indexReader);
			// 分词器
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
			
			/**************** 用了两种查询query 一个是QueryParser，一个是term ***********************/
			// 查询解析器
			QueryParser queryParser = new QueryParser(Version.LUCENE_40, queryField, analyzer);
			Query query = queryParser.parse(queryStr);
			
			// 根据trem去查询
			// Term term = new Term("fileName", "lucene1.txt");
			// Query query = new TermQuery(term);
			Filter filter = null;
			
			// 只取排名前一百的搜索结果,得到命中的文档
			TopDocs topDocs = indexSeacher.search(query, null, 100);
			
			ScoreDoc[] scores = topDocs.scoreDocs;
			
			for (ScoreDoc scoreDoc : scores) {
				// 获取命中的document的文档编号
				int docnumber = scoreDoc.doc;
				// 根据编号查找到文档
				Document document = indexSeacher.doc(docnumber);
				String path = document.get("path");
				String contents = document.get("contents");
				String modifiedtime = document.get("modifiField");
				String fileName = document.get("fileName");
				log.info("查询到数据path：" + path);
				log.info("查询到数据contents：" + contents);
				log.info("查询到数据modifiField：" + modifiedtime);
				log.info("查询到数据fileName：" + fileName);
				
				/********************** 下面的纯属个人乐趣 ****************************/
				// 高亮功能 对查出来的结果进行高亮
				Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
				Highlighter highlighter = new Highlighter(formatter, new QueryScorer(query));
				highlighter.setTextFragmenter(new SimpleFragmenter(Integer.MAX_VALUE));
				String contentsWithLight = highlighter.getBestFragment(analyzer, queryField, contents);
				log.info("带高亮的代码：" + contentsWithLight);
			}
			indexReader.close();
		} catch (Exception e) {
			log.error("lucene查询失败！" + e.getLocalizedMessage());
		}
	}
}
