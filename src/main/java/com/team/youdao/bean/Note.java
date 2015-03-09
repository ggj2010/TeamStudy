package com.team.youdao.bean;

import lombok.Getter;
import lombok.Setter;

import com.team.youdao.base.BaseBean;

/**
 * @ClassName:Note.java
 * @Description: 笔记实体类
 * @author gaoguangjin
 * @Date 2015-3-4 上午11:31:38
 */
@Getter
@Setter
public class Note extends BaseBean {
	private int noteId;
	private String noteName;// 笔记名称
	private String authorName;// 作者名称
	private String fromUrl;// 文本来源
	private String content;// 文本内容
	private NoteBook noteBook;// 笔记本id
	private NoteBookGroup noteBookGroup;// 笔记本组
}
