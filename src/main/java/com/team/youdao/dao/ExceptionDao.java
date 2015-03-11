package com.team.youdao.dao;

public interface ExceptionDao {
	void getData() /** throws Exception **/
	;
	
	void saveDataWithcheckedExcepton();
	
	void saveDataWihtUnCheckedException();
	
	void saveData();
	
	void saveDataWithNull();
}
