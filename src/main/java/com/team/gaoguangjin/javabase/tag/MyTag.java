package com.team.gaoguangjin.javabase.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class MyTag implements Tag {
	private PageContext pageContext;
	private Tag parent;
	
	// 必须要提供set方法 这个name 是和attribute里面对应的
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void setPageContext(PageContext pc) {
		this.pageContext = pageContext;
		
	}
	
	@Override
	public void setParent(Tag t) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Tag getParent() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int doStartTag() throws JspException {
		String p = getName();
		
		boolean show = checkName(p);
		if (show) {
			return TagSupport.EVAL_BODY_INCLUDE;
		} else {
			return TagSupport.SKIP_BODY;
		}
	}
	
	private boolean checkName(String p) {
		return p.equals("true") ? true : false;
	}
	
	@Override
	public int doEndTag() throws JspException {
		// try {
		// pageContext.getOut().write("Hello World!");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		return this.EVAL_PAGE;
	}
	
	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}
	
}
