package com.team.lucene;

import java.io.StringReader;

import lombok.extern.slf4j.Slf4j;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * @ClassName:AnalyzerTest.java
 * @Description: 分词器学习
 * @author gaoguangjin
 * @Date 2015-7-14 上午9:49:40
 */
@Slf4j
public class AnalyzerTest {
	public static void main(String[] args) {
		String content = "gaoguangjin is handsome boy 刘德华没有高广金帅气 基于java语言开发的轻量级的中文分词工具包";
		// 标准分词器
		Analyzer analyzer1 = new StandardAnalyzer(Version.LUCENE_40);
		// 简单分词器
		Analyzer analyzer2 = new SimpleAnalyzer(Version.LUCENE_40);
		// 二元切分
		Analyzer analyzer3 = new CJKAnalyzer(Version.LUCENE_40);
		// 中文语意分词 需要自己引入IKAnalyzer架包
		Analyzer analyzer4 = new IKAnalyzer(true);
		
		display(analyzer1, "StandardAnalyzer", content);
		display(analyzer2, "SimpleAnalyzer", content);
		display(analyzer3, "CJKAnalyzer", content);
		display(analyzer4, "IKAnalyzer", content);
	}
	
	/**
	 * @Description: 展示
	 * @param analyzer
	 * @param analyzerName
	 * @return:void
	 */
	private static void display(Analyzer analyzer, String analyzerName, String content) {
		try {
			TokenStream tokenstream = analyzer.tokenStream("content", new StringReader(content));
			
			// 为token设置属性类
			CharTermAttribute termAttribute = tokenstream.addAttribute(CharTermAttribute.class);
			// 重新设置
			tokenstream.reset();
			log.info("*******************" + analyzerName + "开始分词********************");
			// 遍历得到token
			while (tokenstream.incrementToken()) {
				// log.info(termAttribute.toString());
				log.info(new String(termAttribute.buffer(), 0, termAttribute.length()) + "  ");
			}
		} catch (Exception e) {
			log.error(analyzerName + "分词失败！" + e.getLocalizedMessage());
		}
		
	}
}
