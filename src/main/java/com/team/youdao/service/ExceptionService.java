package com.team.youdao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.youdao.dao.ExceptionDao;

@Service
@Transactional
public class ExceptionService {
	@Autowired
	ExceptionDao exceptionDao;
	
	public void getData() /** throws Exception **/
	{
		exceptionDao.getData();
	}
	
	public void saveDataWithNull() {
		exceptionDao.saveDataWithNull();
	}
	
	public void saveData() {
		exceptionDao.saveData();
	}
	
	public void saveDataWihtUnCheckedException() {
		exceptionDao.saveDataWihtUnCheckedException();
	}
	
	public void saveDataWithcheckedExcepton() {
		exceptionDao.saveDataWithcheckedExcepton();
	}
	
}
