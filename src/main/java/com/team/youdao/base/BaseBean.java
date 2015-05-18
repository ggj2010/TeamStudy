package com.team.youdao.base;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import com.team.gaoguangjin.缓存.redis.util.NeedSelect;

@Getter
@Setter
public class BaseBean {
	
	private Date createdate;
	private Date deletedate;
	private Date updatedate;
	@NeedSelect
	private Integer flag;
	
}
