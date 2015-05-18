package com.team.youdao.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.sql.Clob;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import com.team.gaoguangjin.缓存.redis.util.NeedSelect;
import com.team.youdao.base.BaseBean;

/**
 * @ClassName:Note.java
 * @Description: 笔记实体类
 * @author gaoguangjin
 * @Date 2015-3-4 上午11:31:38
 */
@Getter
@Setter
@Slf4j
public class Note extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@NeedSelect
	private int noteId;
	private String noteName;// 笔记名称
	@NeedSelect
	private String authorName;// 作者名称
	@NeedSelect
	private String fromUrl;// 文本来源
	private Clob content;// 文本内容
	private String contents = "";// 文本内容
	private NoteBook noteBook;// 笔记本id
	private NoteBookGroup noteBookGroup;// 笔记本组
	@NeedSelect
	private Integer flag;// 放到BaseBean里面，反射获取不到field值
	private Date createdate;
	
	public String toString() {
		return "输出值==>id=" + noteId + " 笔记本名称：" + noteName + "   文本来源：" + fromUrl + "  作者名称:" + authorName;
	}
	
	public String ClobToString(Clob clob) throws IOException {
		if (null == clob)
			return null;
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
