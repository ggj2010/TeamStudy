package com.team.lucene;

import java.io.File;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * @ClassName:LuceneQuery.java
 * @Description: 搜索流程中的第二步就是构建一个Query，多种查询
 * @author gaoguangjin
 * @Date 2015-7-14 下午1:39:44
 */
@Slf4j
public class LuceneQuery {
	
	static IndexReader ir;
	static IndexWriter iw;
	static IndexSearcher is;
	private final static String INDEX_FILE = "d:/ftp/index";
	
	// 运行此例子需要先运行LuceneTest.java 创建好索引
	public static void main(String[] args) {
		try {
			// 按词条搜索—TermQuery
			termQuery();
			// 多关键字的搜索—PhraseQuery
			phraseQuery();
			
			/**以下略，用到时候再深入把**/
			// 与或搜索BooleanQuery
			// 在某一范围内搜索RangeQuery
			// 使用前缀搜索PrefixQuery
			// 使用短语缀搜索PhrasePrefixQuery
			// 相近词语的搜索FuzzyQuery
			// 使用通配符搜索WildcardQuery
			
		} catch (Exception e) {
			log.error("查询失败！" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * @Description: 用户在搜索引擎中进行搜索时，常常查找的并非是一个简单的单词，很有可能是几个不同的关键字
	 * @see:setSlop()方法来设定一个称之为“坡度”的变量来确定关键字之间是否允许、允许多少个无关词汇的存在
	 * @see:分词的时候用的是standardAnalyzer()分词器
	 * @see:代索内容是 Students should be allowed to go out with their friends, but not allowed to drink beer
	 * @see:索引里面忽略掉常用词语之后是 Students should  allowed  go out friends allowed  drink beer
	 * @return:void
	 * @throws IOException
	 */
	private static void phraseQuery() throws IOException {
		is = getIndexSercher();
		// 第一种情况，两个词本身紧密相连，先设置坡度为0，再设置坡度为2
		PhraseQuery pq = new PhraseQuery();
		Term term = new Term("contents", "students");
		pq.add(term);
		Term term2 = new Term("contents", "should");
		pq.add(term2);
		
		// 第二种情况，坡度为一个allowed长度3 go out friends allowed
		PhraseQuery pq2 = new PhraseQuery();
		Term term3 = new Term("contents", "go");
		Term term4 = new Term("contents", "allowed");
		pq2.add(term3);
		pq2.add(term4);
		// 两个短语之间最多不能超过三个单词
		pq2.setSlop(3);
		
		// 打印展现
		display(pq, "多关键字的搜索—PhraseQuery");
		display(pq2, "多关键字的搜索—PhraseQuery-带坡度 go out friends allowed");
		// 关闭流
		ir.close();
	}
	
	/**
	 * @Description: 最基本的搜索单位，从本质上来讲一个词条其实就是一个名/值对
	 * @throws IOException
	 * @return:void
	 */
	private static void termQuery() throws IOException {
		is = getIndexSercher();
		// 分词器
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
		// 在所有contents里面查询包含students的索引文档 存进去的都是小写的
		Term term = new Term("contents", "students");
		TermQuery query = new TermQuery(term);
		
		// 打印展现
		display(query, "按词条搜索—TermQuery");
		// 关闭流
		ir.close();
	}
	
	private static void display(Query query, String typeName) throws IOException {
		log.info("************************" + typeName + "******************************");
		// 命中topdocs
		TopDocs topdocs = is.search(query, 100);
		ScoreDoc[] scoreDocs = topdocs.scoreDocs;
		for (ScoreDoc scoreDoc : scoreDocs) {
			int docID = scoreDoc.doc;
			Document document = is.doc(docID);
			log.info("索引文档编号docID=" + docID + "======content:" + document.get("contents"));
		}
		System.out.println();
	}
	
	/**
	 * @Description:获取indexWriter
	 * @throws IOException
	 * @return:IndexWriter
	 */
	private static IndexWriter getIndexWriter() throws IOException {
		File indexFile = new File(INDEX_FILE);
		Directory directory = FSDirectory.open(new File(INDEX_FILE));
		// 分词器
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
		// 配置
		IndexWriterConfig indexwc = new IndexWriterConfig(Version.LUCENE_40, analyzer);
		// 创建新的索引文件时候 追加到已有的索引库
		indexwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
		return new IndexWriter(directory, indexwc);
	}
	
	/**
	 * @Description:获得搜索器
	 * @throws IOException
	 * @return:IndexSearcher
	 */
	private static IndexSearcher getIndexSercher() throws IOException {
		File indexFile = new File(INDEX_FILE);
		Directory directory = FSDirectory.open(new File(INDEX_FILE));
		// 分词器
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
		ir = DirectoryReader.open(directory);
		return new IndexSearcher(ir);
	}
}
