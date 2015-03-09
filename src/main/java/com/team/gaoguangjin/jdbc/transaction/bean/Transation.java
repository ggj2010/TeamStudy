package com.team.gaoguangjin.jdbc.transaction.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName:Transation.java
 * @Description: 事物实体类测试
 * @see:create table gao.tctransation( ts_id integer primary key, ts_name varchar2(20), ts_content varchar2(20), ts_date Date )
 * @see:create sequence gao.sq_transaction start with 1 increment by 1 //创建主键用到
 * @author gaoguangjin
 * @Date 2015-3-5 上午10:29:00
 */
@Getter
@Setter
public class Transation {
	private int id;
	private String name;
	private String content;
	private Date date;
}
