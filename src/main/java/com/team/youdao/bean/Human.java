package com.team.youdao.bean;

import lombok.Getter;
import lombok.Setter;

import com.team.youdao.base.BaseBean;

@Getter
@Setter
public class Human extends BaseBean {
	private int humanId;
	private String humanName;
	private String humanPassword;
}
