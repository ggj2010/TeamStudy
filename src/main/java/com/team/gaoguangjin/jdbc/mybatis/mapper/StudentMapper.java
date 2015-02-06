package com.team.gaoguangjin.jdbc.mybatis.mapper;

import com.team.gaoguangjin.jdbc.mybatis.bean.ClassRoom;
import com.team.gaoguangjin.jdbc.mybatis.bean.Student;
import com.team.gaoguangjin.jdbc.mybatis.bean.Subject;

public interface StudentMapper {
	Student selectStudentById(String id);
	
	ClassRoom selectClassRoomById(String id);
	
	Subject selectSubjectById(String id);
}
