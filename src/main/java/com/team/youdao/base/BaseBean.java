package com.team.youdao.base;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseBean {
	private Date createdate;
	private Date deletedate;
	private Date updatedate;
	private int flag;
	
}
