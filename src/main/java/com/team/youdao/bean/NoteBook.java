package com.team.youdao.bean;

import lombok.Getter;
import lombok.Setter;

import com.team.youdao.base.BaseBean;

/**
 * @ClassName:NoteBook.java
 * @Description: 笔记本
 * @author gaoguangjin
 * @Date 2015-3-4 上午11:26:58
 */
@Getter
@Setter
public class NoteBook extends BaseBean {
	private int noteBookId;
	private String noteBookName;
	private int textSum;// 统计该笔记本下面有多少文本
	private NoteBookGroup noteBookGroup;
}
