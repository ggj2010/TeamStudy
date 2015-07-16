package com.team.gaoguangjin.jdbc.mybatis.mapper;

import com.team.gaoguangjin.jdbc.mybatis.bean.ClassRoom;

public interface ClassRoomMapper {
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return ClassRoom
	 */
	public ClassRoom get1(String id);
	
	public ClassRoom get2(String id);
	
	public ClassRoom get3(String id);
	
	/**
	 * @Description:  插入
	 * @param classRoom     
	 * @return:void
	 */
	public void insert1(ClassRoom classRoom);
	
}
