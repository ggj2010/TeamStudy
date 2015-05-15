package com.team.youdao.bean;

import lombok.Getter;
import lombok.Setter;

import com.team.youdao.base.BaseBean;

/**
 * @ClassName:NoteBookGroup.java
 * @Description: 笔记本组
 * @author gaoguangjin
 * @Date 2015-3-4 上午11:02:03
 */
@Getter
@Setter
public class NoteBookGroup extends BaseBean {
	private int noteBookGroupId;
	private String noteBookGroupName;
	private int textSum;// 统计该笔记本组下面有多少文本
	
}
