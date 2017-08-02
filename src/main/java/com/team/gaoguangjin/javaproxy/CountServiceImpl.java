package com.team.gaoguangjin.javaproxy;

/**
 * @author:gaoguangjin
 * @date 2017/6/30 13:55
 */
public class CountServiceImpl implements CountService {
	
	private int count = 0;
	
	public int count() {
		return count++;
	}
}
